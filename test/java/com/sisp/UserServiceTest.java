package com.example.demo;

import com.example.demo.dao.UserEntityMapper;
import com.example.demo.dao.entity.UserEntity;
import com.example.demo.common.utils.UUIDUtil;
import com.example.demo.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

//@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserEntityMapper userEntityMapper;

    private UserEntity userEntity;

    @Before
    public void setUp() {
        userEntity = new UserEntity();
        userEntity.setId(UUIDUtil.getOneUUID());
        userEntity.setUsername("test");
        userEntity.setPassword("123456");
    }

    @Test
    public void testSelectUserInfo() {
        // 正常路径
        List<UserEntity> userList = new ArrayList<>();
        userList.add(userEntity);
        Mockito.when(userEntityMapper.selectUserInfo(userEntity)).thenReturn(userList);
        List<UserEntity> result = userService.selectUserInfo(userEntity);
        Assert.assertEquals(userList, result);

        // 异常路径
        Mockito.when(userEntityMapper.selectUserInfo(Mockito.any(UserEntity.class))).thenReturn(null);
        result = userService.selectUserInfo(userEntity);
        Assert.assertNull(result);
    }

    @Test
    public void testQueryUserList() {
        // 正常路径
        List<UserEntity> userList = new ArrayList<>();
        userList.add(userEntity);
        Mockito.when(userEntityMapper.queryUserList(userEntity)).thenReturn(userList);
        List<UserEntity> result = userService.queryUserList(userEntity);
        Assert.assertEquals(userList, result);

        // 异常路径
        Mockito.when(userEntityMapper.queryUserList(Mockito.any(UserEntity.class))).thenReturn(null);
        result = userService.queryUserList(userEntity);
        Assert.assertNull(result);
    }

    @Test
    public void testAddUserInfo() {
        // 正常路径
        Mockito.when(userEntityMapper.insert(userEntity)).thenReturn(1);
        int result = userService.addUserInfo(userEntity);
        Assert.assertEquals(3, result);

        // 异常路径
        Mockito.when(userEntityMapper.insert(userEntity)).thenReturn(0);
        result = userService.addUserInfo(userEntity);
        Assert.assertEquals(0, result);
    }

    @Test
    public void testModifyUserInfo() {
        // 正常路径
        Mockito.when(userEntityMapper.updateUserByPrimaryKeySelective(userEntity)).thenReturn(1);
        int result = userService.modifyUserInfo(userEntity);
        Assert.assertEquals(1, result);

        // 异常路径
        Mockito.when(userEntityMapper.updateUserByPrimaryKeySelective(userEntity)).thenReturn(0);
        result = userService.modifyUserInfo(userEntity);
        Assert.assertEquals(0, result);
    }

    @Test
    public void testDeleteUserById() {
        // 正常路径
        Mockito.when(userEntityMapper.deleteUserById(userEntity)).thenReturn(1);
        int result = userService.deleteUserById(userEntity);
        Assert.assertEquals(1, result);

        // 异常路径
        Mockito.when(userEntityMapper.deleteUserById(userEntity)).thenReturn(0);
        result = userService.deleteUserById(userEntity);
        Assert.assertEquals(0, result);
    }
}