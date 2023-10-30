package com.example.demo;

import com.example.demo.beans.HttpResponseEntity;
import com.example.demo.controller.UserController;
import com.example.demo.dao.entity.UserEntity;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;


//@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
    /**
     * UserController部分测试
     */
    @InjectMocks
    private UserController userController;
    @Mock
    private UserService userService;
    private UserEntity userEntity;
    @BeforeEach
    public void setUp() {
        userEntity = new UserEntity();
        userEntity.setId("1");
        userEntity.setUsername("testUser");
        userEntity.setPassword("testPassword");
    }
     @Test
    public void testUserLoginSuccess() {
        when(userService.selectUserInfo(userEntity)).thenReturn(Arrays.asList(userEntity));
         HttpResponseEntity response = userController.userLogin(userEntity);
         assertEquals("666", response.getCode());
        assertEquals(Arrays.asList(userEntity), response.getData());
        assertEquals("登录成功", response.getMessage());
         verify(userService, times(1)).selectUserInfo(userEntity);
    }
     @Test
    public void testUserLoginFailure() {
        when(userService.selectUserInfo(userEntity)).thenReturn(Collections.emptyList());
         HttpResponseEntity response = userController.userLogin(userEntity);
         assertEquals("0", response.getCode());
        assertEquals(null, response.getData());
        assertEquals("用户名或密码错误", response.getMessage());
         verify(userService, times(1)).selectUserInfo(userEntity);
    }
     @Test
    public void testAddUserInfoSuccess() {
        when(userService.addUserInfo(userEntity)).thenReturn(1);
         HttpResponseEntity response = userController.addUser(userEntity);
         assertEquals("666", response.getCode());
        assertEquals(1, response.getData());
        assertEquals("创建成功", response.getMessage());
         verify(userService, times(1)).addUserInfo(userEntity);
    }
     @Test
    public void testAddUserInfoFailure() {
        when(userService.addUserInfo(userEntity)).thenReturn(0);
         HttpResponseEntity response = userController.addUser(userEntity);
         assertEquals("0", response.getCode());
        assertEquals(0, response.getData());
        assertEquals("创建失败", response.getMessage());
         verify(userService, times(1)).addUserInfo(userEntity);
    }
     @Test
    public void testModifyUserInfoSuccess() {
        when(userService.modifyUserInfo(userEntity)).thenReturn(1);
         HttpResponseEntity response = userController.modifyUser(userEntity);
         assertEquals("666", response.getCode());
        assertEquals(1, response.getData());
        assertEquals("修改成功", response.getMessage());
         verify(userService, times(1)).modifyUserInfo(userEntity);
    }
     @Test
    public void testModifyUserInfoFailure() {
        when(userService.modifyUserInfo(userEntity)).thenReturn(0);
         HttpResponseEntity response = userController.modifyUser(userEntity);
         assertEquals("0", response.getCode());
        assertEquals(0, response.getData());
        assertEquals("修改失败", response.getMessage());
         verify(userService, times(1)).modifyUserInfo(userEntity);
    }
     @Test
    public void testDeleteUserSuccess() {
        when(userService.deleteUserById(userEntity)).thenReturn(1);
         HttpResponseEntity response = userController.deleteUser(userEntity);
         assertEquals("666", response.getCode());
        assertEquals(1, response.getData());
        assertEquals("删除成功", response.getMessage());
         verify(userService, times(1)).deleteUserById(userEntity);
    }
     @Test
    public void testDeleteUserFailure() {
        when(userService.deleteUserById(userEntity)).thenReturn(0);
         HttpResponseEntity response = userController.deleteUser(userEntity);
         assertEquals("0", response.getCode());
        assertEquals(0, response.getData());
        assertEquals("删除失败", response.getMessage());
         verify(userService, times(1)).deleteUserById(userEntity);
    }
     @Test
    public void testQueryUserListSuccess() {
        List<UserEntity> userList = Arrays.asList(userEntity);
        when(userService.queryUserList(userEntity)).thenReturn(userList);
         HttpResponseEntity response = userController.queryUserList(userEntity);
         assertEquals("666", response.getCode());
        assertEquals(userList, response.getData());
        assertEquals("查询成功", response.getMessage());
         verify(userService, times(1)).queryUserList(userEntity);
    }
     @Test
    public void testQueryUserListFailure() {
        when(userService.queryUserList(userEntity)).thenReturn(Collections.emptyList());
        HttpResponseEntity response = userController.queryUserList(userEntity);
        assertEquals("0", response.getCode());
        assertEquals(null, response.getData());
        assertEquals("无用户信息", response.getMessage());
        verify(userService, times(1)).queryUserList(userEntity);
    }
}
