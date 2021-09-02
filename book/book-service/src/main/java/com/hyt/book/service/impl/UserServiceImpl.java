package com.hyt.book.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hyt.book.mapper.UserMapper;
import com.hyt.book.pojo.User;
import com.hyt.book.service.UserService;
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
        return userMapper.insert(user);
    }

    @Override
    public User getUserByUsername(String username) {
        return userMapper.selectOne(new QueryWrapper<User>().eq("username",username));
    }
}
