package com.example.demo;

import com.example.demo.beans.HttpResponseEntity;
import com.example.demo.controller.ProjectController;
import com.example.demo.controller.UserController;
import com.example.demo.dao.UserEntityMapper;
import com.example.demo.dao.entity.ProjectEntity;
import com.example.demo.dao.entity.UserEntity;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;

import java.io.InputStream;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ControllerErrorTest {
    Logger log = Logger.getLogger(ControllerErrorTest.class);
    @Test
    public void testQueryUserListException() {
        UserEntity userEntity = new UserEntity();
        UserController userController = new UserController();
        HttpResponseEntity httpResponseEntity = userController.queryUserList(userEntity);
        log.info("结果0");
    }
    @Test
    public void testAddUserInfoException(){
        UserEntity userEntity = new UserEntity();
        UserController userController = new UserController();
        HttpResponseEntity httpResponseEntity = userController.addUser(userEntity);
        log.info("结果1");
    }
    @Test
    public void testModifyUserInfoException(){
        UserEntity userEntity = new UserEntity();
        UserController userController = new UserController();
        HttpResponseEntity httpResponseEntity = userController.modifyUser(userEntity);
        log.info("结果2");
    }
    @Test
    public void testDeleteUserException(){
        UserEntity userEntity = new UserEntity();
        UserController userController = new UserController();
        HttpResponseEntity httpResponseEntity = userController.deleteUser(userEntity);
        log.info("结果3");
    }
    @Test
    public void testLoginException(){
        UserEntity userEntity = new UserEntity();
        UserController userController = new UserController();
        HttpResponseEntity httpResponseEntity = userController.userLogin(userEntity);
        log.info("结果4");
    }
    @Test
    public void testQueryProjectListException(){
        ProjectEntity projectEntity = new ProjectEntity();
        ProjectController projectController = new ProjectController();
        HttpResponseEntity httpResponseEntity = projectController.queryProjectList(projectEntity);
        log.info("结果5");
    }
    @Test
    public void testAddProjectInfoException(){
        ProjectEntity projectEntity = new ProjectEntity();
        ProjectController projectController = new ProjectController();
        HttpResponseEntity httpResponseEntity = projectController.addProjectInfo(projectEntity);
        log.info("结果6");
    }
    @Test
    public void testModifyProjectInfoException(){
        ProjectEntity projectEntity = new ProjectEntity();
        ProjectController projectController = new ProjectController();
        HttpResponseEntity httpResponseEntity = projectController.modifyProjectInfo(projectEntity);
        log.info("结果7");
    }
    @Test
    public void testDeleteProjectByIdException(){
        ProjectEntity projectEntity = new ProjectEntity();
        ProjectController projectController = new ProjectController();
        HttpResponseEntity httpResponseEntity = projectController.deleteProjectById(projectEntity);
        log.info("结果8");
    }
}
