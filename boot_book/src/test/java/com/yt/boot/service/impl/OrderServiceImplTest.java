package com.yt.boot.service.impl;

import com.yt.boot.dao.OrderItemMapper;
import com.yt.boot.pojo.CartItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Mr.He
 * @description:
 * @create 2021-08-14 15:16
 */
@SpringBootTest
class OrderServiceImplTest {

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Test
    void createOrderItems() {
        String orderId = "llljjj";
        ArrayList<CartItem> cartItems = new ArrayList<>();
        cartItems.add(new CartItem(1,1,"name1",1,new BigDecimal(1),new BigDecimal(1)));
        cartItems.add(new CartItem(2,1,"name2",2,new BigDecimal(1),new BigDecimal(2)));
        orderItemMapper.batchInsert(orderId, cartItems);
    }
}