package com.yt.boot.pojo;

import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;

/**
 * @author Mr.He
 * @description:
 * @create 2021-08-14 14:43
 */
@TableName("t_order_item")
public class OrderItem {
    private Integer itemId;
    private String itemName;
    private Integer itemNumber;
    private BigDecimal itemSinglePrice;
    private BigDecimal itemTotalPrice;
    private String orderId;
    private Integer status = 0;

    public OrderItem() {
    }

    public OrderItem(Integer itemId, String itemName, Integer itemNumber, BigDecimal itemSinglePrice, BigDecimal itemTotalPrice, String orderId, Integer status) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemNumber = itemNumber;
        this.itemSinglePrice = itemSinglePrice;
        this.itemTotalPrice = itemTotalPrice;
        this.orderId = orderId;
        this.status = status;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(Integer itemNumber) {
        this.itemNumber = itemNumber;
    }

    public BigDecimal getItemSinglePrice() {
        return itemSinglePrice;
    }

    public void setItemSinglePrice(BigDecimal itemSinglePrice) {
        this.itemSinglePrice = itemSinglePrice;
    }

    public BigDecimal getItemTotalPrice() {
        return itemTotalPrice;
    }

    public void setItemTotalPrice(BigDecimal itemTotalPrice) {
        this.itemTotalPrice = itemTotalPrice;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "itemId=" + itemId +
                ", itemName='" + itemName + '\'' +
                ", itemNumber=" + itemNumber +
                ", itemSinglePrice=" + itemSinglePrice +
                ", itemTotalPrice=" + itemTotalPrice +
                ", orderId='" + orderId + '\'' +
                ", status=" + status +
                '}';
    }
}
