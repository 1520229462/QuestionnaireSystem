package com.sisp;

import com.sisp.dao.AnswerDetailEntityMapper;
import com.sisp.dao.entity.AnswerDetailEntity;
import com.sisp.service.AnswerDetailService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
@RunWith(MockitoJUnitRunner.class)
public class AnswerDetailServiceTest {
    @Mock
    private AnswerDetailEntityMapper answerDetailEntityMapperMock;
    @InjectMocks
    private AnswerDetailService answerDetailService;
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testQueryAnswerDetailListWithValidInput() {
        AnswerDetailEntity answerDetailEntity = new AnswerDetailEntity();
        List<AnswerDetailEntity> expected = new ArrayList<>();
        expected.add(new AnswerDetailEntity());
        when(answerDetailEntityMapperMock.queryAnswerDetailList(answerDetailEntity)).thenReturn(expected);
        List<AnswerDetailEntity> result = answerDetailService.queryAnswerDetailList(answerDetailEntity);
        assertEquals(expected, result);
    }
    @Test
    public void testAddAnswerDetailWithValidInput() {
        AnswerDetailEntity answerDetailEntity = new AnswerDetailEntity();
        when(answerDetailEntityMapperMock.insert(answerDetailEntity)).thenReturn(1);
        int result = answerDetailService.addAnswerDetail(answerDetailEntity);
        assertEquals(1, result);
    }
    @Test
    public void testQueryAnswerDetailListWithInvalidInput() {
        AnswerDetailEntity answerDetailEntity = new AnswerDetailEntity();
        List<AnswerDetailEntity> expected = new ArrayList<>();
        when(answerDetailEntityMapperMock.queryAnswerDetailList(answerDetailEntity)).thenReturn(expected);
        List<AnswerDetailEntity> result = answerDetailService.queryAnswerDetailList(answerDetailEntity);
        assertEquals(expected, result);
    }
    @Test(expected = Exception.class)
    public void testAddAnswerDetailWithInvalidInput() {
        AnswerDetailEntity answerDetailEntity = new AnswerDetailEntity();
        when(answerDetailEntityMapperMock.insert(answerDetailEntity)).thenThrow(new Exception());
        answerDetailService.addAnswerDetail(answerDetailEntity);
    }
}
