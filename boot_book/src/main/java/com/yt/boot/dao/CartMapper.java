package com.yt.boot.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yt.boot.pojo.Cart;
import com.yt.boot.pojo.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;

/**
 * @author Mr.He
 * @description:
 * @create 2021-08-12 20:10
 */
public interface CartMapper extends BaseMapper<Cart> {
}
