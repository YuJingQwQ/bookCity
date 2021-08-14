package com.yt.boot.dao;

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
@Mapper
public interface OrderMapper {
    List<Order> getOrders();

    Integer getOrdersNumber();

    Order getOrder(String orderId);

    @Update("update t_order set status=#{status} where order_id = #{orderId}")
    Integer updateStatus(Order order);
}
