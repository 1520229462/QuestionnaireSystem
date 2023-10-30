package com.sisp;
//修改问卷的时间Date的catch还没覆盖到

import com.sisp.beans.HttpResponseEntity;
import com.sisp.controller.QuestionnaireController;
import com.sisp.dao.entity.QuestionnaireEntity;
import com.sisp.service.QuestionnaireService;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
@ExtendWith(MockitoExtension.class)
public class QuestionnaireControllerTest {
    @InjectMocks
    private QuestionnaireController questionnaireController;
    @Mock
    private QuestionnaireService questionnaireService;
    private QuestionnaireEntity questionnaireEntity;
    @BeforeEach
    public void setUp() {
        questionnaireEntity = new QuestionnaireEntity();
        questionnaireEntity.setId("1");
        questionnaireEntity.setSurveyName("测试问卷");
    }
    @Test
    public void testAddQuestionnaireInfo_success() {
        Mockito.when(questionnaireService.addQuestionInfo(questionnaireEntity)).thenReturn(1);
        HttpResponseEntity response = questionnaireController.addQuestionnaireInfo(questionnaireEntity);
        assertEquals("666", response.getCode());
        assertEquals(questionnaireEntity.getId(), response.getData());
        assertEquals("添加问卷成功", response.getMessage());
    }
    @Test
    public void testAddQuestionnaireInfo_fail() {
        Mockito.when(questionnaireService.addQuestionInfo(questionnaireEntity)).thenReturn(0);
        HttpResponseEntity response = questionnaireController.addQuestionnaireInfo(questionnaireEntity);
        assertEquals("0", response.getCode());
        assertEquals(0, response.getData());
        assertEquals("添加问卷失败", response.getMessage());
    }
    @Test
    public void testQueryQuestionnaireList_success() {
        Mockito.when(questionnaireService.queryQuestionnaireList(questionnaireEntity)).thenReturn(Arrays.asList(questionnaireEntity));
        HttpResponseEntity response = questionnaireController.queryQuestionnaireList(questionnaireEntity);
        assertEquals("666", response.getCode());
        assertEquals(Arrays.asList(questionnaireEntity), response.getData());
        assertEquals("查询问卷成功", response.getMessage());
    }
    @Test
    public void testQueryQuestionnaireList_fail() {
        Mockito.when(questionnaireService.queryQuestionnaireList(questionnaireEntity)).thenReturn(Collections.emptyList());
        HttpResponseEntity response = questionnaireController.queryQuestionnaireList(questionnaireEntity);
        assertEquals("0", response.getCode());
        assertEquals(0, response.getData());
        assertEquals("无问卷信息", response.getMessage());
    }
    @Test
    public void testUpdateQuestionnaire_success() {
        Mockito.when(questionnaireService.updateQuestionnaire(questionnaireEntity)).thenReturn(1);
        HttpResponseEntity response = questionnaireController.updateQuestionnaire(questionnaireEntity);
        assertEquals("10", response.getCode());
        assertEquals(1, response.getData());
        assertEquals("修改成功", response.getMessage());
    }
    @Test
    public void testUpdateQuestionnaire_fail() {
        Mockito.when(questionnaireService.updateQuestionnaire(questionnaireEntity)).thenReturn(0);
        HttpResponseEntity response = questionnaireController.updateQuestionnaire(questionnaireEntity);
        assertEquals("0", response.getCode());
        assertEquals(0, response.getData());
        assertEquals("修改失败", response.getMessage());
    }
    @Test
    public void testDeleteQuestionnaire_Success() {
        QuestionnaireEntity questionnaireEntity = new QuestionnaireEntity();
        Mockito.when(questionnaireService.deleteQuestionnaire(questionnaireEntity)).thenReturn(1);
        HttpResponseEntity response = questionnaireController.deleteQuestionnaire(questionnaireEntity);
        assertEquals("666", response.getCode());
        assertEquals(1, response.getData());
        assertEquals("删除成功", response.getMessage());
    }
    @Test
    public void testDeleteQuestionnaire_Failure() {
        QuestionnaireEntity questionnaireEntity = new QuestionnaireEntity();
        Mockito.when(questionnaireService.deleteQuestionnaire(questionnaireEntity)).thenReturn(0);
        HttpResponseEntity response = questionnaireController.deleteQuestionnaire(questionnaireEntity);
        assertEquals("0", response.getCode());
        assertEquals(0, response.getData());
        assertEquals("删除失败", response.getMessage());
    }

    Logger log = Logger.getLogger(QuestionnaireControllerTest.class);
    @Test
    public void testAddQuestionnaireInfoException() {
        QuestionnaireEntity QuestionnaireEntity = new QuestionnaireEntity();
        QuestionnaireController QuestionnaireController = new QuestionnaireController();
        HttpResponseEntity httpResponseEntity = QuestionnaireController.addQuestionnaireInfo(QuestionnaireEntity);
        log.info("结果0");
    }
    @Test
    public void testQueryQuestionnaireListException() {
        QuestionnaireEntity QuestionnaireEntity = new QuestionnaireEntity();
        QuestionnaireController QuestionnaireController = new QuestionnaireController();
        HttpResponseEntity httpResponseEntity = QuestionnaireController.queryQuestionnaireList(QuestionnaireEntity);
        log.info("结果1");
    }

}
    @Test
    public void testUpdateQuestionnaireException() {
        QuestionnaireEntity QuestionnaireEntity = new QuestionnaireEntity();
        QuestionnaireController QuestionnaireController = new QuestionnaireController();
        HttpResponseEntity httpResponseEntity = QuestionnaireController.updateQuestionnaire(QuestionnaireEntity);
        log.info("结果2");
    }
    @Test
    public void testDeleteQuestionnaireException() {
        QuestionnaireEntity QuestionnaireEntity = new QuestionnaireEntity();
        QuestionnaireController QuestionnaireController = new QuestionnaireController();
        HttpResponseEntity httpResponseEntity = QuestionnaireController.deleteQuestionnaire(QuestionnaireEntity);
        log.info("结果3");
    }