package com.yt.boot.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yt.boot.pojo.CartItem;
import com.yt.boot.pojo.OrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Mr.He
 * @description:
 * @create 2021-08-14 14:45
 */
public interface OrderItemMapper extends BaseMapper<OrderItem> {

    public Integer batchInsert(@Param("orderId") String orderId, @Param("cartItems") List<CartItem> cartItems);

}
