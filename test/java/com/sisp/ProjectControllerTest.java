package com.example.demo;

import com.example.demo.beans.HttpResponseEntity;
import com.example.demo.controller.ProjectController;
import com.example.demo.dao.entity.ProjectEntity;
import com.example.demo.service.ProjectService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

//@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectControllerTest {
    /**
     * ProjectController测试
     */
    @InjectMocks
    private ProjectController projectController;
     @Mock
    private ProjectService projectService;
     @Test
    public void testQueryProjectListPositive() {
        List<ProjectEntity> projectList = new ArrayList<>();
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId("1");
        projectEntity.setProjectName("项目1");
        projectList.add(projectEntity);
         when(projectService.queryProjectList(any(ProjectEntity.class))).thenReturn(projectList);
         HttpResponseEntity httpResponseEntity = projectController.queryProjectList(new ProjectEntity());
        assertEquals("666", httpResponseEntity.getCode());
        assertEquals("查询成功", httpResponseEntity.getMessage());
    }
     @Test
    public void testQueryProjectListNegative() {
        when(projectService.queryProjectList(any(ProjectEntity.class))).thenReturn(new ArrayList<>());
         HttpResponseEntity httpResponseEntity = projectController.queryProjectList(new ProjectEntity());
        assertEquals("0", httpResponseEntity.getCode());
        assertEquals("无项目信息", httpResponseEntity.getMessage());
    }
     @Test
    public void testAddProjectInfoPositive() {
        when(projectService.addProjectInfo(any(ProjectEntity.class))).thenReturn(1);
         HttpResponseEntity httpResponseEntity = projectController.addProjectInfo(new ProjectEntity());
        assertEquals("666", httpResponseEntity.getCode());
        assertEquals("创建成功", httpResponseEntity.getMessage());
    }
     @Test
    public void testAddProjectInfoNegative() {
        when(projectService.addProjectInfo(any(ProjectEntity.class))).thenReturn(0);
         HttpResponseEntity httpResponseEntity = projectController.addProjectInfo(new ProjectEntity());
        assertEquals("0", httpResponseEntity.getCode());
        assertEquals("创建失败", httpResponseEntity.getMessage());
    }
     @Test
    public void testModifyProjectInfoPositive() {
        when(projectService.modifyProjectInfo(any(ProjectEntity.class))).thenReturn(1);
         HttpResponseEntity httpResponseEntity = projectController.modifyProjectInfo(new ProjectEntity());
        assertEquals("10", httpResponseEntity.getCode());
        assertEquals("修改成功", httpResponseEntity.getMessage());
    }
     @Test
    public void testModifyProjectInfoNegative() {
        when(projectService.modifyProjectInfo(any(ProjectEntity.class))).thenReturn(0);
         HttpResponseEntity httpResponseEntity = projectController.modifyProjectInfo(new ProjectEntity());
        assertEquals("0", httpResponseEntity.getCode());
        assertEquals("修改失败", httpResponseEntity.getMessage());
    }
     @Test
    public void testDeleteProjectByIdPositive() {
        when(projectService.deleteProjectById(any(ProjectEntity.class))).thenReturn(1);
         HttpResponseEntity httpResponseEntity = projectController.deleteProjectById(new ProjectEntity());
        assertEquals("666", httpResponseEntity.getCode());
        assertEquals("删除成功", httpResponseEntity.getMessage());
    }
     @Test
    public void testDeleteProjectByIdNegative() {
        when(projectService.deleteProjectById(any(ProjectEntity.class))).thenReturn(0);
         HttpResponseEntity httpResponseEntity = projectController.deleteProjectById(new ProjectEntity());
        assertEquals("0", httpResponseEntity.getCode());
        assertEquals("删除失败", httpResponseEntity.getMessage());
    }
}
