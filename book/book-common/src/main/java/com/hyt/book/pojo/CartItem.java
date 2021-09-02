package com.hyt.book.pojo;

import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;

/**
 * @author Mr.He
 * @description:
 * @create 2021-08-12 20:05
 */
@TableName("t_cart_item")
public class CartItem {
    private Integer itemId;
    private Integer userId;
    private String name;
    private Integer number;
    private BigDecimal singlePrice;
    private BigDecimal totalPrice;

    public CartItem() {
    }

    public CartItem(Integer itemId, Integer userId, String name, Integer number, BigDecimal singlePrice, BigDecimal totalPrice) {
        this.itemId = itemId;
        this.userId = userId;
        this.name = name;
        this.number = number;
        this.singlePrice = singlePrice;
        this.totalPrice = totalPrice;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public BigDecimal getSinglePrice() {
        return singlePrice;
    }

    public void setSinglePrice(BigDecimal singlePrice) {
        this.singlePrice = singlePrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "itemId=" + itemId +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", singlePrice=" + singlePrice +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
