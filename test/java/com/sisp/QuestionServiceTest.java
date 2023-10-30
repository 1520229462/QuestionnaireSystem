package com.sisp;
// 100%

import com.sisp.dao.QuestionEntityMapper;
import com.sisp.dao.entity.QuestionEntity;
import com.sisp.service.QuestionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
public class QuestionServiceTest {
    @Mock
    private QuestionEntityMapper questionEntityMapper;
    @InjectMocks
    private QuestionService questionService;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testAddQuestionInfo() {
        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setProblemName("问题1");
        when(questionEntityMapper.insert(any(QuestionEntity.class))).thenReturn(1);
        int result = questionService.addQuestionInfo(questionEntity);
        Assertions.assertEquals(1, result);
    }
    @Test
    public void testQueryQuestionList() {
        List<QuestionEntity> expectedList = new ArrayList<>();
        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setProblemName("问题0");
        expectedList.add(questionEntity);
        when(questionEntityMapper.queryQuestionList(any(QuestionEntity.class))).thenReturn(expectedList);
        List<QuestionEntity> resultList = questionService.queryQuestionList(questionEntity);
        Assertions.assertEquals(expectedList, resultList);
    }
}