package com.yt.boot.dao;

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
@Mapper
public interface OrderItemMapper {

    public Integer batchInsert(@Param("orderId") String orderId, @Param("cartItems") List<CartItem> cartItems);

    @ResultMap("myResultMap")
    @Select("select * from t_order_item where order_id = #{orderId}")
    List<OrderItem> getOrderItemsByOrderId(String orderId);
}
