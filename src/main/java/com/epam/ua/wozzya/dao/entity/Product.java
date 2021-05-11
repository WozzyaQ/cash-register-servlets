package com.epam.ua.wozzya.dao.entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

public class Product {
    private long id;
    private String name;
    private String code;
    private int stockAmount;
    private String description;
    private BigDecimal price;
    private Date lastEdited;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getStockAmount() {
        return stockAmount;
    }

    public void setStockAmount(int stockAmount) {
        this.stockAmount = stockAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

        Product product = (Product) o;

        if (id != product.id) return false;
        if (stockAmount != product.stockAmount) return false;
        if (!Objects.equals(name, product.name)) return false;
        if (!Objects.equals(code, product.code)) return false;
        if (!Objects.equals(description, product.description)) return false;
        if (!Objects.equals(price, product.price)) return false;
        return Objects.equals(lastEdited, product.lastEdited);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + stockAmount;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (lastEdited != null ? lastEdited.hashCode() : 0);
        return result;
    }
}
