package com.yt.boot.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import javax.swing.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author Mr.He
 * @description:
 * @create 2021-08-12 20:05
 */
@TableName("t_cart")
public class Cart {
    @TableId
    private Integer userId;
    @TableField
    private String lastItemName;

    private Integer totalItemNumber;

    private BigDecimal totalPrice;

    @TableField(exist = false)
    private List<CartItem> cartItems;

    public Cart() {
    }

    public Cart( Integer userId, String lastItemName, Integer totalItemNumber, BigDecimal totalPrice) {
        this.userId = userId;
        this.lastItemName = lastItemName;
        this.totalItemNumber = totalItemNumber;
        this.totalPrice = totalPrice;
    }


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getLastItemName() {
        return lastItemName;
    }

    public void setLastItemName(String lastItemName) {
        this.lastItemName = lastItemName;
    }

    public Integer getTotalItemNumber() {
        return totalItemNumber;
    }

    public void setTotalItemNumber(Integer totalItemNumber) {
        this.totalItemNumber = totalItemNumber;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "userId=" + userId +
                ", lastItemName='" + lastItemName + '\'' +
                ", totalItemNumber=" + totalItemNumber +
                ", totalPrice=" + totalPrice +
                ", cartItems=" + cartItems +
                '}';
    }
}
