package com.sisp.controller;


import com.sisp.beans.HttpResponseEntity;
import com.sisp.common.utils.UUIDUtil;
import com.sisp.dao.entity.ProjectEntity;
import com.sisp.dao.entity.QuestionEntity;
import com.sisp.dao.entity.QuestionnaireEntity;
import com.sisp.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class QuestionnaireController {

    @Autowired
    private QuestionnaireService questionnaireService;


    /**
     * 添加问卷
     * @param questionnaireEntity
     * @return
     */
    @RequestMapping(value = "/addQuestionnaireInfo",method = RequestMethod.POST,headers = "Accept=application/json")
    public HttpResponseEntity addQuestionnaireInfo(@RequestBody QuestionnaireEntity questionnaireEntity){
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();

        try{
           questionnaireEntity.setId(UUIDUtil.getOneUUID());
            int result = questionnaireService.addQuestionInfo(questionnaireEntity);
            if(result!=0){
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(questionnaireEntity.getId());
                httpResponseEntity.setMessage("添加问卷成功");
            }else{
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(0);
                httpResponseEntity.setMessage("添加问卷失败");
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return httpResponseEntity;

    }


    /**
     * 查询问卷
     * @param questionnaireEntity
     * @return
     */
    @RequestMapping(value = "/queryQuestionnaireList",method = RequestMethod.POST,headers = "Accept=application/json")
    public HttpResponseEntity queryQuestionnaireList(@RequestBody QuestionnaireEntity questionnaireEntity){
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();

        try{
            List<QuestionnaireEntity> hasUser = questionnaireService.queryQuestionnaireList(questionnaireEntity);
            if(CollectionUtils.isEmpty(hasUser)){
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(0);
                httpResponseEntity.setMessage("无问卷信息");
            }else{
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(hasUser);
                httpResponseEntity.setMessage("查询问卷成功");
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return httpResponseEntity;

    }


    /**
     * 修改问卷
     * @param questionnaireEntity
     * @return
     */
    @RequestMapping(value = "/updateQuestionnaire",method = RequestMethod.POST,headers = "Accept=application/json")
    public HttpResponseEntity updateQuestionnaire(@RequestBody QuestionnaireEntity questionnaireEntity){
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();

        // 获取当前时间
        Date now = new Date();

        // 格式化时间
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = formatter.format(now);

        // 将格式化后的时间转换为Date类型
        Date date = null;
        try {
            date = formatter.parse(formattedDate);
            questionnaireEntity.setReleaseTime(date);

            int result = questionnaireService.updateQuestionnaire(questionnaireEntity);
            if(result!=0){
                httpResponseEntity.setCode("10");
                httpResponseEntity.setData(result);
                httpResponseEntity.setMessage("修改成功");
            }else{
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(0);
                httpResponseEntity.setMessage("修改失败");
            }

        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return httpResponseEntity;
    }


    /**
     * 删除问卷
     * @param questionnaireEntity
     * @return
     */
    @RequestMapping(value = "/deleteQuestionnaire",method = RequestMethod.POST,headers = "Accept=application/json")
    public HttpResponseEntity deleteQuestionnaire(@RequestBody QuestionnaireEntity questionnaireEntity){
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try{
            int result = questionnaireService.deleteQuestionnaire(questionnaireEntity);
            if(result!=0){
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(result);
                httpResponseEntity.setMessage("删除成功");
            }else{
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(0);
                httpResponseEntity.setMessage("删除失败");
            }

        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return httpResponseEntity;
    }
}
