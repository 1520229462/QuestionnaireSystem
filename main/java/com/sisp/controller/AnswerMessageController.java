package com.sisp.controller;


import com.sisp.beans.HttpResponseEntity;
import com.sisp.beans.Page;
import com.sisp.common.utils.UUIDUtil;
import com.sisp.dao.entity.AnswerMessageEntity;
import com.sisp.dao.entity.UserEntity;
import com.sisp.service.AnswerMessageService;
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
public class AnswerMessageController {

    @Autowired
    private AnswerMessageService answerMessageService;


    /**
     * 查询答卷信息
     * @param answerMessageEntity
     * @return
     */
    @RequestMapping(value = "/queryAnswerMessageList",method = RequestMethod.POST,headers = "Accept=application/json")
    public HttpResponseEntity queryAnswerMessageList(@RequestBody AnswerMessageEntity answerMessageEntity){
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();

        try{
           List<AnswerMessageEntity> hasUser = answerMessageService.queryAnswerMessageList(answerMessageEntity);
            if(CollectionUtils.isEmpty(hasUser)){
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(0);
                httpResponseEntity.setMessage("无答卷信息");
            }else{
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(hasUser);
                httpResponseEntity.setMessage("查询成功");
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return httpResponseEntity;
    }


    /**
     * 分页查询答卷信息
     * @param answerMessageEntity
     * @return
     */
    @RequestMapping(value = "/pageQueryAnswerMessageList",method = RequestMethod.POST,headers = "Accept=application/json")
    public HttpResponseEntity pageQueryAnswerMessageList(@RequestBody AnswerMessageEntity answerMessageEntity){
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();


        try{
            List<AnswerMessageEntity> hasUser = answerMessageService.pageQueryAnswerMessageList(answerMessageEntity);
            List<AnswerMessageEntity>list=answerMessageService.queryAnswerMessageList(answerMessageEntity);
            System.out.println(list.size());
            int totalPageNum=(list.size()+answerMessageEntity.getPageSize()-1)/answerMessageEntity.getPageSize();

            Page page =new Page();
            page.setList(hasUser);
            page.setTotalPageNum(totalPageNum);

            if(CollectionUtils.isEmpty(hasUser)){
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(page);
                httpResponseEntity.setMessage("无答卷信息");
            }else{
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(page);
                httpResponseEntity.setMessage("查询成功");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return httpResponseEntity;
    }



    /**
     * 添加
     * @param answerMessageEntity
     * @return
     */
    @RequestMapping(value = "/addAnswerMessage",method = RequestMethod.POST,headers = "Accept=application/json")
    public HttpResponseEntity addAnswerMessage(@RequestBody AnswerMessageEntity answerMessageEntity){
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

            answerMessageEntity.setAnswerTime(date);
            answerMessageEntity.setId(UUIDUtil.getOneUUID());
            int result = answerMessageService.addAnswerMessage(answerMessageEntity);
            if(result!=0){
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(answerMessageEntity.getId());
                httpResponseEntity.setMessage("添加成功");
            }else{
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(0);
                httpResponseEntity.setMessage("添加失败");
            }

        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return httpResponseEntity;
    }
}
