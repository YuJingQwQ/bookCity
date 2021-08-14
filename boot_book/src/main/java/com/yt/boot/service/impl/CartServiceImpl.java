package com.yt.boot.service.impl;

import com.github.pagehelper.PageHelper;
import com.yt.boot.dao.CartItemMapper;
import com.yt.boot.dao.CartMapper;
import com.yt.boot.pojo.Book;
import com.yt.boot.pojo.Cart;
import com.yt.boot.pojo.CartItem;
import com.yt.boot.pojo.Order;
import com.yt.boot.service.CartService;
import com.yt.boot.utils.MyWebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

/**
 * @author Mr.He
 * @description:
 * @create 2021-08-12 20:13
 */
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartItemMapper cartItemMapper;

    @Autowired
    private CartMapper cartMapper;

    @Override
    public Integer getTotalItemNumber(Integer userId) {
        return cartItemMapper.getTotalItemNumber(userId);
    }

    public List<CartItem> getCartItems(Integer userId, Integer pageNumber, Integer pageSize,Integer totalItemNumber) {
        pageNumber = MyWebUtils.validPageNumber(pageNumber, totalItemNumber, pageSize);
        PageHelper.startPage(pageNumber, pageSize);
        return cartItemMapper.getCartItems(userId);
    }

    public Cart getCart(Integer userId) {
        Cart cart = cartMapper.getCartByUserId(userId);
        if (cart == null) {
            return null;
        }

//        List<CartItem> cartItems = getCartItems(userId, pageNumber, pageSize);
//        cart.setCartItems(cartItems);
        return cart;
    }

    @Transactional
    @Override
    public Integer add(Integer userId, Book book) {
        Cart cart = cartMapper.getCartByUserId(userId);

        if (cart == null) {
            cart = new Cart();
            cart.setUserId(userId);
            cart.setTotalPrice(new BigDecimal(0));
            cart.setTotalItemNumber(0);
            cart.setLastItemName(book.getName());
            cartMapper.addCart(cart);
        } else {
            cart.setTotalPrice(cart.getTotalPrice().add(book.getPrice()));
            cart.setTotalItemNumber(cart.getTotalItemNumber() + 1);
            cart.setLastItemName(book.getName());
            cartMapper.update(cart);
        }

        CartItem cartItem = new CartItem();
        cartItem.setUserId(userId);
        cartItem.setItemId(book.getId());
        CartItem tempCartItem = cartItemMapper.queryCartItem(cartItem);
        if (tempCartItem != null) {
            cartItem = tempCartItem;
            cartItem.setNumber(cartItem.getNumber() + 1);
            cartItem.setTotalPrice(cartItem.getTotalPrice().add(book.getPrice()));
            cartItemMapper.update(cartItem);
        } else {
            cartItem.setName(book.getName());
            cartItem.setNumber(1);
            cartItem.setSinglePrice(book.getPrice());
            cartItem.setTotalPrice(book.getPrice());
            cartItemMapper.add(cartItem);
        }
        return 1;
    }

    @Override
    public Integer deleteCartItem(CartItem cartItem) {
        return cartItemMapper.delete(cartItem);
    }

    @Override
    public CartItem getCartItemByUserIdAndItemId(CartItem cartItem) {
        return cartItemMapper.getCartItemByUserIdAndItemId(cartItem);
    }

    @Override
    public Integer updateCart(Cart cart) {
        return cartMapper.update(cart);
    }

    @Override
    public Integer clearCartItems(Integer userId) {
        return cartItemMapper.clearCartItems(userId);
    }

    @Override
    public List<CartItem> getCartItemsByUserId(Integer userId) {
        return cartItemMapper.getCartItems(userId);
    }

    @Override
    public Integer clearCart(Integer userId) {
        Cart cart = new Cart(userId, "", 0, new BigDecimal(0));
        return cartMapper.update(cart);
    }

    @Override
    public Integer createCart(Cart cart) {
        return cartMapper.addCart(cart);
    }


}
