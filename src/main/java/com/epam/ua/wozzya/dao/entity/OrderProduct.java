package com.epam.ua.wozzya.dao.entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

public class OrderProduct {
    private long orderId;
    private long productId;
    private int quantity;
    private BigDecimal price;
    private Date lastEdited;


    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getLastEdited() {
        return lastEdited;
    }

    public void setLastEdited(Date lastEdited) {
        this.lastEdited = lastEdited;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderProduct that = (OrderProduct) o;

        if (orderId != that.orderId) return false;
        if (productId != that.productId) return false;
        if (quantity != that.quantity) return false;
        if (!Objects.equals(price, that.price)) return false;
        return Objects.equals(lastEdited, that.lastEdited);
    }

    @Override
    public int hashCode() {
        int result = (int) (orderId ^ (orderId >>> 32));
        result = 31 * result + (int) (productId ^ (productId >>> 32));
        result = 31 * result + quantity;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (lastEdited != null ? lastEdited.hashCode() : 0);
        return result;
    }
}
