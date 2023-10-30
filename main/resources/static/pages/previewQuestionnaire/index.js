onload =  async () => {

    let questionnaireId = $util.getPageParam('questionnaireId');

    let questionnaire = await queryQuestionnaire(questionnaireId);

    let question = await queryQuestion(questionnaire.id);



    addQuestion(question);

    for(let i=0;i<question.length;i++){
        let option = await queryOption(question[i].id);
        addOption(option,question[i].type);
    }

    addOtherMessage(questionnaireId);



    $('.btn-div').append(`
        <button type="button" class="btn-primary" onclick="submit('${questionnaire.surveyName}','${questionnaire.id}')">提 交</button>
    `);


};


const queryQuestion = (questionnaireId) => {
    return new Promise((resolve, reject) => {
        let params={
            questionnaireId:questionnaireId
        }
        $.ajax({
            url: API_BASE_URL + '/queryQuestionList',
            type: "POST",
            data: JSON.stringify(params),
            dataType: "json",
            contentType: "application/json",
            success(res) {
                let data=res.data;
                resolve(data);
            },
            error(err) {
                reject(err);
            }
        });
    });
};

const queryOption = (questionId) => {
    return new Promise((resolve, reject) => {
        let params={
            questionId:questionId
        }
        $.ajax({
            url: API_BASE_URL + '/queryOptionList',
            type: "POST",
            data: JSON.stringify(params),
            dataType: "json",
            contentType: "application/json",
            success(res) {
                let data=res.data;
                resolve(data);
            },
            error(err) {
                reject(err);
            }
        });
    });
};


const addQuestion = (question) =>{
    for (let i = 0; i < question.length; i++) {


        $('#problem').append(`
    <div class="question" id=${question[i].id} data-type=${question[i].type} data-problemIndex="1">
      
    </div>
  `)

        let questionDiv =  $('#' + question[i].id);
        questionDiv.append(`
      <div class="top">
      </div>    
    `)

        let topDiv = questionDiv.find(".top");

        switch (question[i].type) {
            case "1":
                topDiv.append(`
      <span class="question-title" id="questionTitle">${i + 1}.${question[i].problemName}</span>
    `);
                break;
            case "2":
                topDiv.append(`
      <span class="question-title" id="questionTitle">${i + 1}.${question[i].problemName}</span>
    `);
                break;
            case "3":
                topDiv.append(`
      <span class="question-title" id="questionTitle">${i + 1}.${question[i].problemName}</span>
    `);
                break;
            case "4":
                topDiv.append(`
      <span class="question-title" id="questionTitle">${i + 1}.${question[i].problemName}</span>
    `);
                break;
            case "5":
                topDiv.append(`
      <span class="question-title" id="questionTitle">${i + 1}.${question[i].problemName}</span>
    `);
                break;
            default:
                break;
        }

        if(question[i].mustAnswer=="true"){
            topDiv.append(`
      <span class="must-answer" id="mustAnswer">必答题</span>
    `)
        }else {
            topDiv.append(`
      <span class="must-answer" id="mustAnswer">非必答题</span>
    `)
        }
    }

};


const addOption = (option,type) =>{
    let questionDiv =  $('#' + option[0].questionId);
    questionDiv.append(`
       <div class="bottom">
      </div>  
    `)
    let bottomDiv = questionDiv.find(".bottom");

    switch (type) {
        case "1":
            for(let i=0;i<option.length;i++){
                bottomDiv.append(`
        <div style="display: flex; align-items: center; margin-bottom: 3px;">
          <label class="radio-inline">
            <input type="radio" name="chooseTerm" id=${option[i].id}>${option[i].chooseTerm}
          </label>
        </div>
          `)
            }
            break;
        case "2":
            for(let i=0;i<option.length;i++){
                bottomDiv.append(`
        <div style="display: flex; align-items: center; margin-bottom: 3px;">
          <label class="checkbox-inline">
            <input type="checkbox" name="chooseTerm" id=${option[i].id}>${option[i].chooseTerm}
          </label>
        </div>
        `)
            }
            break;
        case "3":
            bottomDiv.append(`
        <textarea class="form-control"  rows="4" style="width: 70%;"  id=${option[0].id}></textarea>
      `)
            break;
        case "4":
            let leftTitles=[];
            for(let i=0;i<option.length;i++){
                leftTitles.push(option[i].leftTitle);
            }
            let uniqueLeftTitles = [...new Set(leftTitles)];


            let chooseTerms=[];
            for(let i=0;i<option.length;i++){
                chooseTerms.push(option[i].chooseTerm);
            }
            let uniqueChooseTerm = [...new Set(chooseTerms)];


            bottomDiv.append(`
       <table class="table">
        </table>
      `);

            let tableDiv=bottomDiv.find(".table");

            tableDiv.append(`
          <thead>
            <tr>
              <th></th>
            </tr>
          </thead>
          <tbody>
          </tbody>
        `);

            for(let i=0;i<uniqueChooseTerm.length;i++){
                $('thead tr').append(`<th>${uniqueChooseTerm[i]}</th>`);
            }

            for(let i=0;i<uniqueLeftTitles.length;i++){
                $('tbody').append(`
        <tr id="${uniqueLeftTitles[i]}">
        <td>${uniqueLeftTitles[i]}</td>
        </tr>
        `);


                for(let h=0;h<uniqueChooseTerm.length;h++){
                    for(let j=0;j<option.length;j++){
                        if(option[j].chooseTerm==uniqueChooseTerm[h]&&option[j].leftTitle==uniqueLeftTitles[i]){
                            $('#' + uniqueLeftTitles[i]).append(`     
        <td><input type="radio" id="${option[j].id}" name="${option[j].leftTitle}" /></td>
        `);

                        }
                    }
                }

            }


            break;
        case "5":
            bottomDiv.css({
                'display': 'flex',
                'align-items': 'center',
                'justify-content': 'space-between'
            });
            bottomDiv.append(`
      <div>${option[0].chooseTerm}</div>
      `)
            for(let i=0;i<option.length;i++){
                bottomDiv.append(`
        <div>
          <label class="radio-inline">
            <input type="radio" id=${option[i].id} name="fraction"/>${option[i].fraction}
          </label>
        </div>
          `)
            }
            bottomDiv.append(`
      <div>${option[option.length-1].chooseTerm}</div>
      `)

            break;
        default:
            break;
    }

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

const addOtherMessage = (questionnaireId) =>{

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
            let data=res.data[0];
            $('.questionnaire-title').text(`${data.surveyName}`);
            $('.questionnaire-description').text(`${data.surveyDescription}`);

        }
    });


};



const addAnswerMessage =(questionnaireName) =>{
    return new Promise((resolve, reject) => {
        let params = {
            questionnaireId: $util.getPageParam('questionnaireId'),
            questionnaireName: questionnaireName,
            projectId: $util.getPageParam('projectId'),
            answererName: $util.getItem('userInfo').username,
            answererId: $util.getItem('userInfo').id
        };
        $.ajax({
            url: API_BASE_URL + '/addAnswerMessage',
            type: "POST",
            data: JSON.stringify(params),
            dataType: "json",
            contentType: "application/json",
            success(res) {
                let data=res.data;
                resolve(data);
            },
            error(err) {
                reject(err);
            }
        });
    });
};

const addAnswerDetail =(answerMessageId,option,type) =>{

    switch (type) {
        case "3":
            if($(`#${option[0].id}`).prop("value")!=""){
                let params = {
                    answerMessageId: answerMessageId,
                    optionId: option[0].id,
                    text: $(`#${option[0].id}`).prop("value")
                }
                $.ajax({
                    url: API_BASE_URL + '/addAnswerDetail',
                    type: "POST",
                    data: JSON.stringify(params),
                    dataType: "json",
                    contentType: "application/json",
                });
            }
            break;

        case "2":
            for(let i=0;i<option.length;i++){
                if($(`#${option[i].id}`).prop("checked")){
                    let params = {
                        answerMessageId: answerMessageId,
                        optionId: option[i].id,
                    }
                    $.ajax({
                        url: API_BASE_URL + '/addAnswerDetail',
                        type: "POST",
                        data: JSON.stringify(params),
                        dataType: "json",
                        contentType: "application/json",
                    });
                }
            }
            break;
        default:
            for(let i=0;i<option.length;i++){
                if($(`#${option[i].id}`).is(':checked')){
                    let params = {
                        answerMessageId: answerMessageId,
                        optionId: option[i].id,
                    }
                    $.ajax({
                        url: API_BASE_URL + '/addAnswerDetail',
                        type: "POST",
                        data: JSON.stringify(params),
                        dataType: "json",
                        contentType: "application/json",
                    });
                }
            }
            break;

    }

};

const testAnswer = async (mustQuestions) =>{


    let flag=false;

    for(let i=0;i<mustQuestions.length;i++){

        let option =  await queryOption(mustQuestions[i].id);

        switch (mustQuestions[i].type) {
            case "3":
                if($(`#${option[0].id}`).prop("value")==""){
                    flag=true;
                    break;
                }
                if(flag){
                    alert("请完成必答题的作答");
                    return false;

                }
                break;
            case "2":
                for(let j=0;j<option.length;j++){

                    if($(`#${option[j].id}`).prop("checked")){
                        flag=false;
                        break;
                    }else {
                        flag = true;
                    }
                }
                if(flag){
                    alert("请完成必答题的作答");
                    return false;

                }
                break;
            case "4":
                let leftTitles=[];
                for(let j=0;j<option.length;j++){
                    leftTitles.push(option[j].leftTitle);
                }
                let num = [...new Set(leftTitles)].length;

                for(let j=0;j<option.length;j++){

                    if($(`#${option[j].id}`).is(':checked')){
                        num--;
                    }
                }

                if(num!=0){
                    alert("请完成必答题的作答");
                    return false;
                }
                break;
            default:
                for(let j=0;j<option.length;j++){

                    if($(`#${option[j].id}`).is(':checked')){
                        flag=false;
                        break;
                    }else {
                        flag=true;
                    }
                }

                if(flag){
                    alert("请完成必答题的作答");
                    return false;
                }

                break;

        }
    }

    return true;



};

const submit = async (questionnaireName,questionnaireId) =>{
    let question = await queryQuestion(questionnaireId);
    let  mustQuestions=question.filter(item => item.mustAnswer === "true");





    try {
        let result = await testAnswer(mustQuestions);
        if(result){
            let answerMessageId= await addAnswerMessage(questionnaireName);

            for(let i=0;i<question.length;i++){
                let option=await queryOption(question[i].id)
                addAnswerDetail(answerMessageId,option,question[i].type);
            }

            alert("提交成功");
            location.href = "/pages/seeProject/index.html";

        }
    } catch (error) {
        console.log(error);
    }

};

