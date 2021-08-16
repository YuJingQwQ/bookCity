package com.yt.boot.service;

import com.yt.boot.pojo.User;

/**
 * @author Mr.He
 * @description:
 * @create 2021-08-11 17:19
 */
public interface UserService {
    Integer addUser(User user);

//    Boolean userIsExisted(User user);

//    Boolean validUserLogin(User user);

    User getUserByUsername(String username);
}
