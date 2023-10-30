package com.sisp;
// 100%

import com.sisp.beans.HttpResponseEntity;
import com.sisp.controller.AnswerDetailController;
import com.sisp.dao.entity.AnswerDetailEntity;
import com.sisp.service.AnswerDetailService;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
@RunWith(MockitoJUnitRunner.class)
public class AnswerDetailControllerTest {
    @Mock
    private AnswerDetailService answerDetailService;
    @InjectMocks
    private AnswerDetailController answerDetailController;
    private AnswerDetailEntity answerDetailEntity;
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        answerDetailEntity = new AnswerDetailEntity();
        answerDetailEntity.setId("123");
        answerDetailEntity.setAnswerMessageId("456");
        answerDetailEntity.setOptionId("789");
        answerDetailEntity.setText("test");
    }
    @Test
    public void testQueryAnswerDetailListPositive() {
        when(answerDetailService.queryAnswerDetailList(answerDetailEntity)).thenReturn(Collections.singletonList(answerDetailEntity));
        HttpResponseEntity response = answerDetailController.queryAnswerDetailList(answerDetailEntity);
        assertNotNull(response.getData());
        assertEquals("666", response.getCode());
        assertEquals("查询成功", response.getMessage());
    }
    @Test
    public void testQueryAnswerDetailListNoData() {
        when(answerDetailService.queryAnswerDetailList(answerDetailEntity)).thenReturn(Collections.emptyList());
        HttpResponseEntity response = answerDetailController.queryAnswerDetailList(answerDetailEntity);
        assertEquals("0", response.getCode());
        assertEquals(0, response.getData());
        assertEquals("无答卷明细信息", response.getMessage());
    }
    @Test
    public void testQueryAnswerDetailListNegative() {
        answerDetailEntity.setAnswerMessageId(null);
        HttpResponseEntity response = answerDetailController.queryAnswerDetailList(answerDetailEntity);
        assertEquals("0", response.getCode());
        assertEquals(0, response.getData());
    }
    @Test
    public void testAddAnswerDetailPositive() {
        when(answerDetailService.addAnswerDetail(answerDetailEntity)).thenReturn(1);
        HttpResponseEntity response = answerDetailController.addAnswerDetail(answerDetailEntity);
        assertNotNull(response.getData());
        assertEquals("666", response.getCode());
        assertEquals("添加成功", response.getMessage());
    }
    @Test
    public void testAddAnswerDetailNoData() {
        when(answerDetailService.addAnswerDetail(answerDetailEntity)).thenReturn(0);
        HttpResponseEntity response = answerDetailController.addAnswerDetail(answerDetailEntity);
        assertEquals("0", response.getCode());
        assertEquals(0, response.getData());
        assertEquals("添加失败", response.getMessage());
    }
    @Test
    public void testAddAnswerDetailNegative() {
        answerDetailEntity.setAnswerMessageId(null);
        HttpResponseEntity response = answerDetailController.addAnswerDetail(answerDetailEntity);
        assertEquals("0", response.getCode());
        assertEquals(0, response.getData());
    }

    Logger log = Logger.getLogger(AnswerDetailControllerTest.class);
    @Test
    public void testAddAnswerDetailException() {
        AnswerDetailEntity AnswerDetailEntity = new AnswerDetailEntity();
        AnswerDetailController AnswerDetailController = new AnswerDetailController();
        HttpResponseEntity httpResponseEntity = AnswerDetailController.addAnswerDetail(AnswerDetailEntity);
        log.info("结果0");
    }
    @Test
    public void testQueryAnswerDetailListException() {
        AnswerDetailEntity AnswerDetailEntity = new AnswerDetailEntity();
        AnswerDetailController AnswerDetailController = new AnswerDetailController();
        HttpResponseEntity httpResponseEntity = AnswerDetailController.queryAnswerDetailList(AnswerDetailEntity);
        log.info("结果1");
    }
}