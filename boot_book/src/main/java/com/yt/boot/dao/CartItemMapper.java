package com.yt.boot.dao;

import com.yt.boot.pojo.CartItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Mr.He
 * @description:
 * @create 2021-08-12 20:13
 */
@Mapper
public interface CartItemMapper {
    Integer getTotalItemNumber(Integer userId);

    List<CartItem> getCartItems(Integer userId);

    CartItem queryCartItem(CartItem cartItem);

    Integer update(CartItem cartItem);

    Integer add(CartItem cartItem);

    Integer delete(CartItem cartItem);

    CartItem getCartItemByUserIdAndItemId(CartItem cartItem);

    Integer clearCartItems(Integer userId);
}
