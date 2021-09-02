package com.hyt.book.service;


import com.hyt.book.pojo.Book;
import com.hyt.book.pojo.Cart;
import com.hyt.book.pojo.CartItem;

import java.util.List;

/**
 * @author Mr.He
 * @description:
 * @create 2021-08-12 20:12
 */
public interface CartService {
    Integer getTotalItemNumber(Integer userId);

    List<CartItem> getCartItems(Integer userId, Integer pageNumber, Integer pageSize, Integer totalItemNumber);

    Cart getCart(Integer userId);

    Integer add(Integer userId, Book book);

    Integer deleteCartItem(CartItem cartItem);

    CartItem getCartItemByUserIdAndItemId(CartItem cartItem);

    Integer updateCart(Cart cart);

    Integer clearCartItems(Integer userId);

    List<CartItem> getCartItemsByUserId(Integer userId);

    Integer clearCart(Integer userId);

    Integer createCart(Cart cart);
}
