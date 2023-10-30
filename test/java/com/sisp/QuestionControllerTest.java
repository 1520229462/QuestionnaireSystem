package com.sisp;
//查询问题失败的getData和getMessage有问题

import com.sisp.beans.HttpResponseEntity;
import com.sisp.common.utils.UUIDUtil;
import com.sisp.controller.QuestionController;
import com.sisp.dao.entity.QuestionEntity;
import com.sisp.service.QuestionService;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(MockitoJUnitRunner.class)
public class QuestionControllerTest {
    @Mock
    private QuestionService questionService;
    @InjectMocks
    private QuestionController questionController;
    private QuestionEntity questionEntity;
    private List<QuestionEntity> questionList;
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        questionEntity = new QuestionEntity();
        questionEntity.setId(UUIDUtil.getOneUUID());
        questionEntity.setProblemName("问题1");
        questionEntity.setType("text");
        questionEntity.setQuestionnaireId(UUIDUtil.getOneUUID());
        questionList = new ArrayList<>();
        questionList.add(questionEntity);
    }
    @Test
    public void testAddQuestionInfoWithValidInput() {
        Mockito.when(questionService.addQuestionInfo(Mockito.any(QuestionEntity.class))).thenReturn(1);
        HttpResponseEntity response = questionController.addQuestionInfo(questionEntity);
        assertEquals("666", response.getCode());
        assertEquals("添加问题成功", response.getMessage());
        assertNotNull(response.getData());
    }
    @Test
    public void testAddQuestionInfoWithEmptyInput() {
        QuestionEntity emptyQuestion = new QuestionEntity();
        HttpResponseEntity response = questionController.addQuestionInfo(emptyQuestion);
        assertEquals("0", response.getCode());
        assertEquals("添加问题失败", response.getMessage());
        assertEquals(0, response.getData());
    }
    @Test
    public void testAddQuestionInfoWithDuplicateId() {
        Mockito.when(questionService.addQuestionInfo(Mockito.any(QuestionEntity.class))).thenReturn(0);
        questionEntity.setId("123");
        HttpResponseEntity response = questionController.addQuestionInfo(questionEntity);
        assertEquals("0", response.getCode());
        assertEquals("添加问题失败", response.getMessage());
        assertEquals(0, response.getData());
    }
    @Test
    public void testAddQuestionInfoWithInvalidInput() {
        questionEntity.setId(null);
        HttpResponseEntity response = questionController.addQuestionInfo(questionEntity);
        assertEquals("0", response.getCode());
        assertEquals("添加问题失败", response.getMessage());
        assertEquals(0, response.getData());
    }
    @Test
    public void testQueryQuestionListWithValidInput() {
        Mockito.when(questionService.queryQuestionList(Mockito.any(QuestionEntity.class))).thenReturn(questionList);
        HttpResponseEntity response = questionController.queryQuestionList(questionEntity);
        assertEquals("666", response.getCode());
        assertEquals("查询问题成功", response.getMessage());
        assertFalse(CollectionUtils.isEmpty((List<QuestionEntity>) response.getData()));
    }
    @Test
    public void testQueryQuestionListWithEmptyInput() {
        QuestionEntity emptyQuestion = new QuestionEntity();
        HttpResponseEntity response = questionController.queryQuestionList(emptyQuestion);
        assertEquals("0", response.getCode());
        assertEquals("无问题信息", response.getMessage());
        assertEquals(0, response.getData());
    }
    @Test
    public void testQueryQuestionListWithInvalidInput() {
        questionEntity.setId(null);
        HttpResponseEntity response = questionController.queryQuestionList(questionEntity);
        assertEquals("0", response.getCode());
        assertEquals("无问题信息", response.getMessage());
        assertEquals(0, response.getData());
    }
    @Test
    public void testQueryQuestionListWithNonExistentInput() {
        questionEntity.setId("123");
        HttpResponseEntity response = questionController.queryQuestionList(questionEntity);
        assertEquals("0", response.getCode());
        assertEquals("无问题信息", response.getMessage());
        assertEquals(0, response.getData());
    }

    Logger log = Logger.getLogger(QuestionControllerTest.class);
    @Test
    public void testAddQuestionInfoException() {
        QuestionEntity QuestionEntity = new QuestionEntity();
        QuestionController QuestionController = new QuestionController();
        HttpResponseEntity httpResponseEntity = QuestionController.addQuestionInfo(QuestionEntity);
        log.info("结果0");
    }
    @Test
    public void testQueryQuestionListException() {
        QuestionEntity QuestionEntity = new QuestionEntity();
        QuestionController QuestionController = new QuestionController();
        HttpResponseEntity httpResponseEntity = QuestionController.queryQuestionList(QuestionEntity);
        log.info("结果1");
    }
}