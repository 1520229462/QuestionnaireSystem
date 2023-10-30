package com.sisp;
//查询选项失败的getData和getMessage有问题

import com.sisp.beans.HttpResponseEntity;
import com.sisp.common.utils.UUIDUtil;
import com.sisp.controller.OptionController;
import com.sisp.dao.entity.OptionEntity;
import com.sisp.service.OptionService;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
public class OptionControllerTest {
    @Mock
    private OptionService optionService;
    @InjectMocks
    private OptionController optionController;
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testAddOptionInfoPositive() {
        OptionEntity optionEntity = new OptionEntity();
        optionEntity.setId(UUIDUtil.getOneUUID());
        optionEntity.setLeftTitle("Option A");
        optionEntity.setQuestionId(UUIDUtil.getOneUUID());
        when(optionService.addOptionInfo(optionEntity)).thenReturn(1);
        HttpResponseEntity response = optionController.addOptionInfo(optionEntity);
        assertEquals("666", response.getCode());
        assertEquals(1, response.getData());
        assertEquals("添加选项成功", response.getMessage());
    }
    @Test
    public void testAddOptionInfoNegative() {
        OptionEntity optionEntity = new OptionEntity();
        optionEntity.setId(UUIDUtil.getOneUUID());
        optionEntity.setLeftTitle("Option A");
        optionEntity.setQuestionId(UUIDUtil.getOneUUID());
        when(optionService.addOptionInfo(optionEntity)).thenReturn(0);
        HttpResponseEntity response = optionController.addOptionInfo(optionEntity);
        assertEquals("0", response.getCode());
        assertEquals(0, response.getData());
        assertEquals("添加选项失败", response.getMessage());
    }
    @Test
    public void testQueryOptionListPositive() {
        OptionEntity optionEntity = new OptionEntity();
        optionEntity.setQuestionId(UUIDUtil.getOneUUID());
        List<OptionEntity> optionList = new ArrayList<>();
        OptionEntity option = new OptionEntity();
        option.setId(UUIDUtil.getOneUUID());
        option.setLeftTitle("Option A");
        option.setQuestionId(optionEntity.getQuestionId());
        optionList.add(option);
        when(optionService.queryOptionList(optionEntity)).thenReturn(optionList);
        HttpResponseEntity response = optionController.queryOptionList(optionEntity);
        assertEquals("666", response.getCode());
        assertEquals(optionList, response.getData());
        assertEquals("查询选项成功", response.getMessage());
    }
    @Test
    public void testQueryOptionListNegative() {
        OptionEntity optionEntity = new OptionEntity();
        optionEntity.setQuestionId(UUIDUtil.getOneUUID());
        List<OptionEntity> optionList = new ArrayList<>();
        when(optionService.queryOptionList(optionEntity)).thenReturn(optionList);
        HttpResponseEntity response = optionController.queryOptionList(optionEntity);
        assertEquals("0", response.getCode());
        assertEquals(0, response.getData());
        assertEquals("无选项信息", response.getMessage());
    }

    Logger log = Logger.getLogger(OptionControllerTest.class);
    @Test
    public void testAddOptionInfoException() {
        OptionEntity OptionEntity = new OptionEntity();
        OptionController OptionController = new OptionController();
        HttpResponseEntity httpResponseEntity = OptionController.addOptionInfo(OptionEntity);
        log.info("结果0");
    }
    @Test
    public void testQueryOptionListException() {
        OptionEntity OptionEntity = new OptionEntity();
        OptionController OptionController = new OptionController();
        HttpResponseEntity httpResponseEntity = OptionController.queryOptionList(OptionEntity);
        log.info("结果1");
    }
}
