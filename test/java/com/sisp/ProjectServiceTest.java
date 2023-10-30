package com.example.demo;

import com.example.demo.dao.ProjectEntityMapper;
import com.example.demo.dao.entity.ProjectEntity;
import com.example.demo.common.utils.UUIDUtil;
import com.example.demo.service.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class ProjectServiceTest {
     @Mock
    private ProjectEntityMapper projectEntityMapper;
     @InjectMocks
    private ProjectService projectService;
     @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
     @Test
    public void testQueryProjectList() {
        // given
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setProjectName("test");
         List<ProjectEntity> projectList = new ArrayList<>();
        ProjectEntity project1 = new ProjectEntity();
        project1.setId(UUIDUtil.getOneUUID());
        project1.setProjectName("test1");
        projectList.add(project1);
         ProjectEntity project2 = new ProjectEntity();
        project2.setId(UUIDUtil.getOneUUID());
        project2.setProjectName("test2");
        projectList.add(project2);
         when(projectEntityMapper.queryProjectList(projectEntity)).thenReturn(projectList);
         // when
        List<ProjectEntity> result = projectService.queryProjectList(projectEntity);
         // then
        assertEquals(result.size(), 2);
        verify(projectEntityMapper, times(1)).queryProjectList(projectEntity);
    }
     @Test
    public void testAddProjectInfo() {
        // given
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setProjectName("test");
         when(projectEntityMapper.insert(any(ProjectEntity.class))).thenReturn(1);
         // when
        int result = projectService.addProjectInfo(projectEntity);
         // then
        assertEquals(1, result);
        verify(projectEntityMapper, times(1)).insert(projectEntity);
    }
     @Test
    public void testModifyProjectInfo() {
        // given
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId(UUIDUtil.getOneUUID());
        projectEntity.setProjectName("test");
         when(projectEntityMapper.updateByPrimaryKeySelective(any(ProjectEntity.class))).thenReturn(1);
         // when
        int result = projectService.modifyProjectInfo(projectEntity);
         // then
        assertEquals(result, 1);
        verify(projectEntityMapper, times(1)).updateByPrimaryKeySelective(projectEntity);
    }
     @Test
    public void testDeleteProjectById() {
        // given
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId(UUIDUtil.getOneUUID());
         when(projectEntityMapper.deleteProjectById(any(ProjectEntity.class))).thenReturn(1);
         // when
        int result = projectService.deleteProjectById(projectEntity);
         // then
        assertEquals(result, 1);
        verify(projectEntityMapper, times(1)).deleteProjectById(projectEntity);
    }
}
