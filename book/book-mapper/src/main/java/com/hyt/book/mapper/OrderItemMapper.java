package com.hyt.book.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hyt.book.pojo.CartItem;
import com.hyt.book.pojo.OrderItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Mr.He
 * @description:
 * @create 2021-08-14 14:45
 */
public interface OrderItemMapper extends BaseMapper<OrderItem> {

    public Integer batchInsert(@Param("orderId") String orderId, @Param("cartItems") List<CartItem> cartItems);

}
