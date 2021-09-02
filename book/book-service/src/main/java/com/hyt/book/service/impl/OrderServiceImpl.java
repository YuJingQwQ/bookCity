package com.hyt.book.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hyt.book.mapper.CartMapper;
import com.hyt.book.mapper.OrderItemMapper;
import com.hyt.book.mapper.OrderMapper;
import com.hyt.book.pojo.CartItem;
import com.hyt.book.pojo.Order;
import com.hyt.book.pojo.OrderItem;
import com.hyt.book.service.OrderService;
import com.hyt.book.utils.MyWebUtils;
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
        Page<Order> orderPage = new Page<>(pn, pageSize);
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        return orderMapper.selectPage(orderPage, wrapper).getRecords();
    }

    public Integer getTotalOrderNumber() {
        return orderMapper.selectCount(null);
    }

    @Override
    public Integer createOrderItems(String orderId, List<CartItem> cartItems) {
        return orderItemMapper.batchInsert(orderId, cartItems);
    }

    @Override
    public String createOrder(Integer userId) {
        //status=0 ,0表示未发货
        Integer status = 0;
        BigDecimal price = cartMapper.selectById(userId).getTotalPrice();
        LocalDateTime localDateTime = LocalDateTime.now();
        long dateMilli = localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
        Order order = new Order();
        order.setOrderId("" + userId + dateMilli);
        order.setCreateDateTime(localDateTime);
        order.setPrice(price);
        order.setStatus(0);
        order.setUserId(userId);
        orderMapper.insert(order);
        return order.getOrderId();
    }

    @Override
    public Order getOrder(String orderId) {
        return orderMapper.selectById(orderId);
    }

    @Override
    public List<OrderItem> getOrderItemsByOrderId(String orderId) {
        return orderItemMapper.selectList(new QueryWrapper<OrderItem>().eq("order_id", orderId));
    }

    @Override
    public Integer updateStatus(Order order) {
        UpdateWrapper<Order> wrapper = new UpdateWrapper<Order>()
                .set("status", order.getStatus())
                .eq("order_id", order.getOrderId());
        return orderMapper.update(null, wrapper);
    }

    @Override
    public Integer updateOrderItemStatus(String orderId, Integer itemId) {
        UpdateWrapper<OrderItem> wrapper = new UpdateWrapper<>();
        wrapper.set("status", 1).eq("order_id", orderId).eq("item_id", itemId);
        return orderItemMapper.update(null, wrapper);
    }
}
