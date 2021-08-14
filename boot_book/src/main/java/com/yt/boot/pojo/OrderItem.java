package com.yt.boot.pojo;

import java.math.BigDecimal;

/**
 * @author Mr.He
 * @description:
 * @create 2021-08-14 14:43
 */
public class OrderItem {
    private Integer itemId;
    private String itemName;
    private Integer itemNumber;
    private BigDecimal itemSinglePrice;
    private BigDecimal itemTotalPrice;
    private String orderId;

    public OrderItem() {
    }

    public OrderItem(Integer itemId, String itemName, Integer itemNumber, BigDecimal itemSinglePrice, BigDecimal itemTotalPrice, String orderId) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemNumber = itemNumber;
        this.itemSinglePrice = itemSinglePrice;
        this.itemTotalPrice = itemTotalPrice;
        this.orderId = orderId;
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

    @Override
    public String toString() {
        return "OrderItem{" +
                "itemId=" + itemId +
                ", itemName='" + itemName + '\'' +
                ", itemNumber=" + itemNumber +
                ", itemSinglePrice=" + itemSinglePrice +
                ", itemTotalPrice=" + itemTotalPrice +
                ", orderId=" + orderId +
                '}';
    }
}
