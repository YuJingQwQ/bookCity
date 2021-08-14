package com.yt.boot.service.impl;

import com.yt.boot.dao.UserMapper;
import com.yt.boot.pojo.User;
import com.yt.boot.service.UserService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Mr.He
 * @description:
 * @create 2021-08-11 17:19
 */
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;

    @Override
    public Integer addUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public Boolean userIsExisted(User user) {
        Integer isExisted = userMapper.userIsExisted(user);
        if (isExisted != 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean validUserLogin(User user) {
        User tempUser = userMapper.commonUser(user);
        if (tempUser == null) {
            return false;
        }
        return true;
    }

    @Override
    public User commonUser(User user) {
        return userMapper.commonUser(user);
    }
}
