package com.sisp;
//查询答卷失败的getData和getMessage有问题
//添加里面的Date的catch没覆盖

import com.sisp.beans.HttpResponseEntity;
import com.sisp.beans.Page;
import com.sisp.controller.AnswerMessageController;
import com.sisp.dao.entity.AnswerMessageEntity;
import com.sisp.service.AnswerMessageService;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class AnswerMessageControllerTest {
    @InjectMocks
    private AnswerMessageController answerMessageController;
    @Mock
    private AnswerMessageService answerMessageService;
    private AnswerMessageEntity answerMessageEntity;
    @BeforeEach
    public void setUp() {
        answerMessageEntity = new AnswerMessageEntity();
        answerMessageEntity.setId("test-id");
    }
    @Test
    public void testQueryAnswerMessageList_positive() {
        when(answerMessageService.queryAnswerMessageList(any())).thenReturn(Arrays.asList(answerMessageEntity));
        HttpResponseEntity response = answerMessageController.queryAnswerMessageList(answerMessageEntity);
        assertEquals("666", response.getCode());
        assertEquals(answerMessageEntity, ((List) response.getData()).get(0));
        assertEquals("查询成功", response.getMessage());
    }
    @Test
    public void testQueryAnswerMessageList_negative() {
        AnswerMessageEntity answerMessageEntity = new AnswerMessageEntity();
        when(answerMessageService.queryAnswerMessageList(answerMessageEntity)).thenReturn(Collections.emptyList());
        HttpResponseEntity httpResponseEntity = answerMessageController.queryAnswerMessageList(answerMessageEntity);
        assertEquals("0", httpResponseEntity.getCode());
        assertEquals(0, httpResponseEntity.getData());
        assertEquals("无答卷信息", httpResponseEntity.getMessage());
    }
    @Test
    public void testPageQueryAnswerMessageList_positive() {
        AnswerMessageEntity answerMessageEntity = new AnswerMessageEntity();
        answerMessageEntity.setPageSize(10);
        when(answerMessageService.pageQueryAnswerMessageList(answerMessageEntity)).thenReturn(Collections.singletonList(answerMessageEntity));
        when(answerMessageService.queryAnswerMessageList(answerMessageEntity)).thenReturn(Collections.singletonList(answerMessageEntity));
        HttpResponseEntity httpResponseEntity = answerMessageController.pageQueryAnswerMessageList(answerMessageEntity);
        assertEquals("666", httpResponseEntity.getCode());
        Page page = (Page) httpResponseEntity.getData();
        assertEquals(1, page.getTotalPageNum());
        assertEquals(Collections.singletonList(answerMessageEntity), page.getList());
        assertEquals("查询成功", httpResponseEntity.getMessage());
    }
    @Test
    public void testPageQueryAnswerMessageList_negative() {
        AnswerMessageEntity answerMessageEntity = new AnswerMessageEntity();
        answerMessageEntity.setPageSize(10);
        when(answerMessageService.pageQueryAnswerMessageList(answerMessageEntity)).thenReturn(Collections.emptyList());
        when(answerMessageService.queryAnswerMessageList(answerMessageEntity)).thenReturn(Collections.emptyList());
        HttpResponseEntity httpResponseEntity = answerMessageController.pageQueryAnswerMessageList(answerMessageEntity);
        assertEquals("0", httpResponseEntity.getCode());
        Page page = (Page) httpResponseEntity.getData();
        assertEquals(0, page.getTotalPageNum());
        assertEquals(Collections.emptyList(), page.getList());
        assertEquals("无答卷信息", httpResponseEntity.getMessage());
    }
    @Test
    public void testAddAnswerMessage_positive() {
        AnswerMessageEntity answerMessageEntity = new AnswerMessageEntity();
        when(answerMessageService.addAnswerMessage(answerMessageEntity)).thenReturn(1);
        HttpResponseEntity httpResponseEntity = answerMessageController.addAnswerMessage(answerMessageEntity);
        assertEquals("666", httpResponseEntity.getCode());
        assertEquals(answerMessageEntity.getId(), httpResponseEntity.getData());
        assertEquals("添加成功", httpResponseEntity.getMessage());
    }
    @Test
    public void testAddAnswerMessage_negative() {
        when(answerMessageService.addAnswerMessage(any())).thenReturn(0);
        HttpResponseEntity response = answerMessageController.addAnswerMessage(answerMessageEntity);
        assertEquals("0", response.getCode());
        assertEquals(0, response.getData());
        assertEquals("添加失败", response.getMessage());
    }

    Logger log = Logger.getLogger(AnswerMessageControllerTest.class);
    @Test
    public void testAddAnswerMessageException() {
        AnswerMessageEntity AnswerMessageEntity = new AnswerMessageEntity();
        AnswerMessageController AnswerMessageController = new AnswerMessageController();
        HttpResponseEntity httpResponseEntity = AnswerMessageController.addAnswerMessage(AnswerMessageEntity);
        log.info("结果0");
    }
    @Test
    public void testPageQueryAnswerMessageListException() {
        AnswerMessageEntity AnswerMessageEntity = new AnswerMessageEntity();
        AnswerMessageController AnswerMessageController = new AnswerMessageController();
        HttpResponseEntity httpResponseEntity = AnswerMessageController.pageQueryAnswerMessageList(AnswerMessageEntity);
        log.info("结果1");
    }
    @Test
    public void testQueryAnswerMessageListException() {
        AnswerMessageEntity AnswerMessageEntity = new AnswerMessageEntity();
        AnswerMessageController AnswerMessageController = new AnswerMessageController();
        HttpResponseEntity httpResponseEntity = AnswerMessageController.queryAnswerMessageList(AnswerMessageEntity);
        log.info("结果2");
    }
}