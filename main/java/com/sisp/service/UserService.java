package com.sisp.service;

import com.sisp.common.utils.UUIDUtil;
import com.sisp.dao.UserEntityMapper;
import com.sisp.dao.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserEntityMapper userEntityMapper;

    /**
     * 登录
     */
    public List<UserEntity> selectUserInfo(UserEntity userEntity){
        List<UserEntity> result = userEntityMapper.selectUserInfo(userEntity);
        return result;
    }


    /**
     * 查询用户列表
     */
    public List<UserEntity> queryUserList(UserEntity userEntity){
        List<UserEntity> result = userEntityMapper.queryUserList(userEntity);
        return result;
    }

    /**
     * 创建用户
     */
    public int addUserInfo(UserEntity userEntity){
        userEntity.setId(UUIDUtil.getOneUUID());
        userEntity.setStatus("1");
        int userResult = userEntityMapper.insert(userEntity);
        if(userResult != 0){
            return 3; //3代表用户存在
        } else {
            return userResult;
        }
    }

    /**
     * 修改用户信息
     */
    public int modifyUserInfo(UserEntity userEntity){
        int userResult = userEntityMapper.updateUserByPrimaryKeySelective(userEntity);
        return userResult;
    }

    /**
     * 删除用户信息
     */
    public int deleteUserById(UserEntity userEntity){
        int userResult = userEntityMapper.deleteUserById(userEntity);
        return userResult;
    }



    //serviceUserList mvn neu.lab:codeCoverage:start



}
