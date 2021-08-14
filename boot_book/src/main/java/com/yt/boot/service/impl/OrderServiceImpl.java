package com.yt.boot.service.impl;

import com.github.pagehelper.PageHelper;
import com.yt.boot.dao.CartMapper;
import com.yt.boot.dao.OrderItemMapper;
import com.yt.boot.dao.OrderMapper;
import com.yt.boot.pojo.CartItem;
import com.yt.boot.pojo.Order;
import com.yt.boot.pojo.OrderItem;
import com.yt.boot.service.OrderService;
import com.yt.boot.utils.MyWebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

/**
 * @author Mr.He
 * @description:
 * @create 2021-08-13 22:23
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private CartMapper cartMapper;

    @Override
    public List<Order> getOrders(Integer pn, Integer pageSize, Integer totalOrderNumber) {
        pn = MyWebUtils.validPageNumber(pn, totalOrderNumber, pageSize);
        PageHelper.startPage(pn, pageSize);
        return orderMapper.getOrders();
    }

    public Integer getTotalOrderNumber() {
        return orderMapper.getOrdersNumber();
    }

    @Override
    public Integer createOrderItems(String orderId, List<CartItem> cartItems) {
        return orderItemMapper.batchInsert(orderId, cartItems);
    }

    @Override
    public String createOrder(Integer userId) {
        //status=0 ,0表示未发货
        Integer status = 0;
        BigDecimal price = cartMapper.getPriceByUserId(userId);
        LocalDateTime localDateTime = LocalDateTime.now();
        long dateMilli = localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
        Order order = new Order();
        order.setOrderId("" + userId + dateMilli);
        order.setCreateDateTime(localDateTime);
        order.setPrice(price);
        order.setStatus(0);
        order.setUserId(userId);
        cartMapper.createOrder(order);
        return order.getOrderId();
    }

    @Override
    public Order getOrder(String orderId) {
        return orderMapper.getOrder(orderId);
    }

    @Override
    public List<OrderItem> getOrderItemsByOrderId(String orderId) {
        return orderItemMapper.getOrderItemsByOrderId(orderId);
    }

    @Override
    public Integer updateStatus(Order order) {
        return orderMapper.updateStatus(order);
    }
}
