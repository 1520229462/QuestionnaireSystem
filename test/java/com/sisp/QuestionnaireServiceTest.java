package com.sisp;

import com.sisp.dao.QuestionnaireEntityMapper;
import com.sisp.dao.entity.QuestionnaireEntity;
import com.sisp.service.QuestionnaireService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
@RunWith(MockitoJUnitRunner.class)
public class QuestionnaireServiceTest {
    @Mock
    private QuestionnaireEntityMapper questionnaireEntityMapper;
    @InjectMocks
    private QuestionnaireService questionnaireService;
    private QuestionnaireEntity questionnaireEntity;
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        questionnaireEntity = new QuestionnaireEntity();
        questionnaireEntity.setId("1");
        questionnaireEntity.setSurveyName("Test Questionnaire");
        questionnaireEntity.setSurveyDescription("This is a test questionnaire.");
    }
    @Test
    public void testAddQuestionInfo() {
        when(questionnaireEntityMapper.insert(questionnaireEntity)).thenReturn(1);
        int result = questionnaireService.addQuestionInfo(questionnaireEntity);
        assertEquals(1, result);
    }
    @Test
    public void testQueryQuestionnaireList() {
        List<QuestionnaireEntity> questionnaireList = new ArrayList<>();
        questionnaireList.add(questionnaireEntity);
        when(questionnaireEntityMapper.queryQuestionnaireList(questionnaireEntity)).thenReturn(questionnaireList);
        List<QuestionnaireEntity> result = questionnaireService.queryQuestionnaireList(questionnaireEntity);
        assertEquals(questionnaireList, result);
    }
    @Test
    public void testUpdateQuestionnaire() {
        when(questionnaireEntityMapper.updateQuestionnaire(questionnaireEntity)).thenReturn(1);
        int result = questionnaireService.updateQuestionnaire(questionnaireEntity);
        assertEquals(1, result);
    }
    @Test
    public void testAddQuestionInfoWithNullInput() {
        int result = questionnaireService.addQuestionInfo(null);
        assertEquals(0, result);
    }
    @Test
    public void testQueryQuestionnaireListWithNullInput() {
        List<QuestionnaireEntity> list = new ArrayList<QuestionnaireEntity>();
        List<QuestionnaireEntity> result = questionnaireService.queryQuestionnaireList(null);
        assertEquals(list, result);
    }
    @Test
    public void testUpdateQuestionnaireWithNullInput() {
        int result = questionnaireService.updateQuestionnaire(null);
        assertEquals(0, result);
    }
    @Test
    public void testDeleteQuestionnaire() {
        Mockito.when(questionnaireEntityMapper.deleteQuestionnaire(Mockito.any(QuestionnaireEntity.class))).thenReturn(1);
        int result = questionnaireService.deleteQuestionnaire(questionnaireEntity);
        assertEquals(1, result);
    }
    @Test
    public void testDeleteQuestionnaireWithNull() {
        int result = questionnaireService.deleteQuestionnaire(null);
        assertEquals(0, result);
    }
}