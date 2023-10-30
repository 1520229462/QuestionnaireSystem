package com.sisp.controller;


import com.sisp.beans.HttpResponseEntity;
import com.sisp.common.utils.UUIDUtil;
import com.sisp.dao.entity.OptionEntity;
import com.sisp.dao.entity.QuestionEntity;
import com.sisp.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OptionController {

    @Autowired
    private OptionService optionService;

    /**
     * 添加选项
     * @param optionEntity
     * @return
     */
    @RequestMapping(value = "/addOptionInfo",method = RequestMethod.POST,headers = "Accept=application/json")
    public HttpResponseEntity addOptionInfo(@RequestBody OptionEntity optionEntity){

        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();

        try{
            optionEntity.setId(UUIDUtil.getOneUUID());
            int result = optionService.addOptionInfo(optionEntity);
            if(result!=0){
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(result);
                httpResponseEntity.setMessage("添加选项成功");
            }else{
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(0);
                httpResponseEntity.setMessage("添加选项失败");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return httpResponseEntity;

    }


    /**
     * 查询选项
     * @param optionEntity
     * @return
     */
    @RequestMapping(value = "/queryOptionList",method = RequestMethod.POST,headers = "Accept=application/json")
    public HttpResponseEntity queryOptionList(@RequestBody OptionEntity optionEntity){

        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();

        try{
            List<OptionEntity> hasUser = optionService.queryOptionList(optionEntity);

            if(CollectionUtils.isEmpty(hasUser)){
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(0);
                httpResponseEntity.setMessage("无选项信息");
            }else{
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(hasUser);
                httpResponseEntity.setMessage("查询选项成功");
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return httpResponseEntity;

    }
}
