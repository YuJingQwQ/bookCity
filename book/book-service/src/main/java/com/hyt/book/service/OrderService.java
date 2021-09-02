package com.hyt.book.service;


import com.hyt.book.pojo.CartItem;
import com.hyt.book.pojo.Order;
import com.hyt.book.pojo.OrderItem;

import java.util.List;

/**
 * @author Mr.He
 * @description:
 * @create 2021-08-13 22:23
 */
public interface OrderService {
    List<Order> getOrders(Integer pn, Integer pageSize, Integer totalOrderNumber);

    Integer getTotalOrderNumber();

    Integer createOrderItems(String orderId, List<CartItem> cartItems);

    String createOrder(Integer userId);

    Order getOrder(String orderId);

    List<OrderItem> getOrderItemsByOrderId(String orderId);

    Integer updateStatus(Order order);

    Integer updateOrderItemStatus(String orderId,Integer itemId);
}
