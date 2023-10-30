package com.sisp;

import com.sisp.dao.AnswerMessageEntityMapper;
import com.sisp.dao.entity.AnswerDetailEntity;
import com.sisp.dao.entity.AnswerMessageEntity;
import com.sisp.service.AnswerMessageService;
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
import static org.junit.Assert.assertNotNull;
@RunWith(MockitoJUnitRunner.class)
public class AnswerMessageServiceTest {
    @Mock
    private AnswerMessageEntityMapper answerMessageEntityMapper;
    @InjectMocks
    private AnswerMessageService answerMessageService;
    private AnswerMessageEntity answerMessageEntity;
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        answerMessageEntity = new AnswerMessageEntity();
        answerMessageEntity.setId("1");
        answerMessageEntity.setQuestionnaireId("111");
        List<AnswerMessageEntity> answerMessageList = new ArrayList<>();
        answerMessageList.add(answerMessageEntity);
        AnswerDetailEntity answerDetailEntity = new AnswerDetailEntity();
        answerDetailEntity.setId("2");
        answerDetailEntity.setAnswerMessageId("123");
        answerDetailEntity.setText("Answer");
        List<AnswerDetailEntity> answerDetailList = new ArrayList<>();
        answerDetailList.add(answerDetailEntity);
        answerMessageEntity.setAnswerDetailList(answerDetailList);
        Mockito.when(answerMessageEntityMapper.queryAnswerMessageList(answerMessageEntity)).thenReturn(answerMessageList);
        Mockito.when(answerMessageEntityMapper.pageQueryAnswerMessageList(answerMessageEntity)).thenReturn(answerMessageList);
        Mockito.when(answerMessageEntityMapper.insert(answerMessageEntity)).thenReturn(1);
    }
    @Test
    public void testQueryAnswerMessageList() {
        List<AnswerMessageEntity> result = answerMessageService.queryAnswerMessageList(answerMessageEntity);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(answerMessageEntity.getId(), result.get(0).getId());
    }
    @Test
    public void testPageQueryAnswerMessageList() {
        List<AnswerMessageEntity> result = answerMessageService.pageQueryAnswerMessageList(answerMessageEntity);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(answerMessageEntity.getId(), result.get(0).getId());
    }
    @Test
    public void testAddAnswerMessage() {
        int result = answerMessageService.addAnswerMessage(answerMessageEntity);
        assertEquals(1, result);
    }
    @Test
    public void testAddAnswerMessageWithNullAnswerMessageEntity() {
        int result = answerMessageService.addAnswerMessage(null);
        assertEquals(0, result);
    }
}