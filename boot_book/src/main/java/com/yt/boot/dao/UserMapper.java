package com.yt.boot.dao;

import com.yt.boot.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Mr.He
 * @description:
 * @create 2021-08-11 17:20
 */
@Mapper
public interface UserMapper {
    Integer addUser(User user);

    Integer userIsExisted(User user);

    Integer validUsernameAndPassword(User user);

    User commonUser(User user);
}
