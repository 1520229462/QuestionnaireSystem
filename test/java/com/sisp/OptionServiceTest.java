package com.sisp;

import com.sisp.dao.OptionEntityMapper;
import com.sisp.dao.entity.OptionEntity;
import com.sisp.service.OptionService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
@RunWith(MockitoJUnitRunner.class)
public class OptionServiceTest {
    @Mock
    private OptionEntityMapper optionEntityMapper;
    @InjectMocks
    private OptionService optionService;
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testAddOptionInfo() {
        OptionEntity optionEntity = new OptionEntity();
        optionEntity.setId("1");
        optionEntity.setLeftTitle("Option1");
        when(optionEntityMapper.insert(optionEntity)).thenReturn(1);
        int result = optionService.addOptionInfo(optionEntity);
        Assert.assertEquals(1, result);
    }
    @Test
    public void testQueryOptionList() {
        OptionEntity optionEntity = new OptionEntity();
        optionEntity.setLeftTitle("Option1");
        List<OptionEntity> optionList = new ArrayList<>();
        optionList.add(new OptionEntity());
        optionList.add(new OptionEntity());
        when(optionEntityMapper.queryOptionList(optionEntity)).thenReturn(optionList);
        List<OptionEntity> result = optionService.queryOptionList(optionEntity);
        Assert.assertEquals(2, result.size());
    }
}