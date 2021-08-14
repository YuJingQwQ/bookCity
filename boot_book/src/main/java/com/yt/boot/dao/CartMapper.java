package com.yt.boot.dao;

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
@Mapper
public interface CartMapper {

    Cart getCartByUserId(Integer userId);

    Integer update(Cart cart);

    Integer addCart(Cart cart);

    @Select("select total_price from t_cart where user_id = #{userId}")
    BigDecimal getPriceByUserId(Integer userId);

    Integer createOrder(Order order);
}
