onload = () => {
  $('#headerDivB').text('项目详情');

  let projectId = $util.getPageParam('seeProject');
  console.log(projectId, 'projectId');
  fetchProjectInfo(projectId);
  fetchQuestionnaireInfo(projectId);
}

const fetchProjectInfo = (id) => {
  let params = {
    id,
    creationDate: $('#creationDate').val(),
    createdBy: $('#createdBy').val(),
    projectContent: $('#projectDescribe').val()
  }
  $.ajax({
    url: API_BASE_URL + '/queryProjectList',
    type: "POST",
    data: JSON.stringify(params),
    dataType: "json",
    contentType: "application/json",
    success(res) {
      let info = res.data[0]
      console.log(info, 'res')
      $('#projectName').text(info.projectName)
      $('#createTime').text(info.creationDate)
      $('#personInCharge').text(info.createdBy)
      $('#projectDescription').text(info.projectContent)
    }
  })
}

const fetchQuestionnaireInfo = (projectId) =>{
  let params = {
    projectId:projectId,
    isDelete:"false"
  }
  $.ajax({
    url: API_BASE_URL + '/queryQuestionnaireList',
    type: "POST",
    data: JSON.stringify(params),
    dataType: "json",
    contentType: "application/json",
    success(res) {
      let info = res.data;

      for(let i=0;i<info.length;i++){
        $('tbody').append(`
        <tr>
        <td>${(i+1).toString()}</td>
        <td>${info[i].surveyName}</td>
        <td>${info[i].releaseTime || ""}</td>
        <td>
            <button type="button" class="btn btn-link" onclick="previewQuestionnaire('${info[i].id}')">预览</button>
            <button type="button" class="btn btn-link" onclick="releaseQuestionnaire('${info[i].id}')">发布</button>
            <button type="button" class="btn btn-link btn-red" onclick="deleteQuestionnaire('${info[i].id}')">删除</button>
            <button type="button" class="btn btn-link btn-red" onclick="analyseQuestionnaire('${info[i].id}')">统计</button>
          </td>
        </tr>
        `)

      }
    }
  })

};

const previewQuestionnaire =(questionnaireId) =>{
  $util.setPageParam('questionnaireId', questionnaireId);
  $util.setPageParam('projectId', $util.getPageParam('seeProject'));
  location.href = "/pages/previewQuestionnaire/index.html";
};

const releaseQuestionnaire =async (questionnaireId) =>{
  // 获取当前网页链接
  const url = new URL("http://127.0.0.1:8085/pages/AnswerQuestionnaire/index.html");

// 添加参数
  url.searchParams.set('questionnaireId', questionnaireId);
  url.searchParams.set('projectId', $util.getPageParam('seeProject'));


  //判断问卷是否已经发布
  let questionnaire=await queryQuestionnaire(questionnaireId);
  let isRelease=questionnaire.isRelease;

  if(isRelease=="true"){
    copyToClipboard(url);
    alert("问卷已经被发布过了！访问链接已复制到剪切板");
  } else{
    let params = {
      id:questionnaireId,
      isRelease:"true"
    };
    $.ajax({
      url: API_BASE_URL + '/updateQuestionnaire',
      type: "POST",
      data: JSON.stringify(params),
      dataType: "json",
      contentType: "application/json",
      success(res) {
        copyToClipboard(url);
        alert("问卷发布成功！访问链接已复制到剪切板");
        location.href = "/pages/seeProject/index.html";
      }
    })

  };

};

const queryQuestionnaire = (questionnaireId) => {
  return new Promise((resolve, reject) => {
    let params={
      id:questionnaireId
    }
    $.ajax({
      url: API_BASE_URL + '/queryQuestionnaireList',
      type: "POST",
      data: JSON.stringify(params),
      dataType: "json",
      contentType: "application/json",
      success(res) {
        let data=res.data;
        resolve(data[0]);
      },
      error(err) {
        reject(err);
      }
    });
  });
};

const deleteQuestionnaire=async (questionnaireId) =>{
    let questionnaire=await queryQuestionnaire(questionnaireId);


  let now = new Date();
  let other = questionnaire.endTime;

  console.log(now);
  console.log(other);

// 比较两个时间的先后关系
  if (now.getTime() > new Date(other).getTime()) {
    let params = {
      id:questionnaireId,
      isDelete: "true"
    };
    $.ajax({
      url: API_BASE_URL + '/updateQuestionnaire',
      type: "POST",
      data: JSON.stringify(params),
      dataType: "json",
      contentType: "application/json",
      success(res) {
        alert("删除问卷成功");
        location.href = "/pages/seeProject/index.html";
      }
    })
  }else {
    alert("问卷正在进行中，无法删除！");
  }

};

const analyseQuestionnaire =(questionnaireId)=>{
  $util.setPageParam('questionnaireId', questionnaireId);
  location.href = "/pages/statistics/index.html";
}


const copyToClipboard =(text) => {
  let textarea = document.createElement('textarea');
  textarea.value = text;
  document.body.appendChild(textarea);
  textarea.select();
  document.execCommand('copy');
  document.body.removeChild(textarea);
};

