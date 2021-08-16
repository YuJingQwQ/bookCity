package com.yt.boot.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yt.boot.pojo.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author Mr.He
 * @description:
 * @create 2021-08-13 22:24
 */
public interface OrderMapper extends BaseMapper<Order> {
}
