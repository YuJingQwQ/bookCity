package com.hyt.book.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hyt.book.mapper.CartItemMapper;
import com.hyt.book.mapper.CartMapper;
import com.hyt.book.pojo.Book;
import com.hyt.book.pojo.Cart;
import com.hyt.book.pojo.CartItem;
import com.hyt.book.service.CartService;
import com.hyt.book.utils.MyWebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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
        return cartItemMapper.selectCount(new QueryWrapper<CartItem>().eq("user_id", userId));
    }

    public List<CartItem> getCartItems(Integer userId, Integer pageNumber, Integer pageSize, Integer totalItemNumber) {
        pageNumber = MyWebUtils.validPageNumber(pageNumber, totalItemNumber, pageSize);
        Page<CartItem> cartItemPage = new Page<>(pageNumber, pageSize);
        QueryWrapper<CartItem> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        Page<CartItem> page = cartItemMapper.selectPage(cartItemPage, wrapper);
        return page.getRecords();
    }

    public Cart getCart(Integer userId) {
        return cartMapper.selectById(userId);
    }

    @Transactional
    @Override
    public Integer add(Integer userId, Book book) {
        Cart cart = cartMapper.selectById(userId);
        cart.setTotalPrice(cart.getTotalPrice().add(book.getPrice()));
        cart.setTotalItemNumber(cart.getTotalItemNumber() + 1);
        cart.setLastItemName(book.getName());
        cartMapper.updateById(cart);

        QueryWrapper<CartItem> wrapper = new QueryWrapper<>();
        wrapper.eq("item_id", book.getId()).eq("user_id", userId);
        CartItem cartItem = cartItemMapper.selectOne(wrapper);
        if (cartItem != null) {
            cartItem.setNumber(cartItem.getNumber() + 1);
            cartItem.setTotalPrice(cartItem.getTotalPrice().add(book.getPrice()));
            cartItemMapper.update(cartItem, wrapper);
        } else {
            cartItem = new CartItem(book.getId(), userId, book.getName(), 1, book.getPrice(), book.getPrice());
            cartItemMapper.insert(cartItem);
        }
        return 1;
    }

    @Override
    public Integer deleteCartItem(CartItem cartItem) {
        QueryWrapper<CartItem> wrapper = new QueryWrapper<>();
        wrapper.eq("item_id", cartItem.getItemId()).eq("user_id", cartItem.getUserId());
        return cartItemMapper.delete(wrapper);
    }

    @Override
    public CartItem getCartItemByUserIdAndItemId(CartItem cartItem) {
        QueryWrapper<CartItem> wrapper = new QueryWrapper<>();
        wrapper.eq("item_id", cartItem.getItemId()).eq("user_id", cartItem.getUserId());
        return cartItemMapper.selectOne(wrapper);
    }

    @Override
    public Integer updateCart(Cart cart) {
        return cartMapper.updateById(cart);
    }

    @Override
    public Integer clearCartItems(Integer userId) {
        QueryWrapper<CartItem> wrapper = new QueryWrapper<CartItem>().eq("user_id", userId);
        return cartItemMapper.delete(wrapper);
    }

    @Override
    public List<CartItem> getCartItemsByUserId(Integer userId) {
        return cartItemMapper.selectList(new QueryWrapper<CartItem>().eq("user_id", userId));
    }

    @Override
    public Integer clearCart(Integer userId) {
        Cart cart = new Cart(userId, "", 0, new BigDecimal(0));
        return cartMapper.updateById(cart);
    }

    @Override
    public Integer createCart(Cart cart) {
        return cartMapper.insert(cart);
    }


}
