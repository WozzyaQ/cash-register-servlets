package com.epam.ua.wozzya.model.dao.entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

public class Order{
    private long id;
    private Worker creator;
    private boolean closed;
    private Date dateCreated;
    private Date dateClosed;
    private String comment;
    private Date lastEdited;
    private String orderedBy;
    private BigDecimal total;

    private List<Product> products;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Worker getCreator() {
        return creator;
    }

    public void setCreator(Worker creator) {
        this.creator = creator;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateClosed() {
        return dateClosed;
    }

    public void setDateClosed(Date dateClosed) {
        this.dateClosed = dateClosed;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getLastEdited() {
        return lastEdited;
    }

    public void setLastEdited(Date lastEdited) {
        this.lastEdited = lastEdited;
    }

    public String getOrderedBy() {
        return orderedBy;
    }

    public void setOrderedBy(String orderedBy) {
        this.orderedBy = orderedBy;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != order.id) return false;
        if (closed != order.closed) return false;
        if (!Objects.equals(creator, order.creator)) return false;
        if (!Objects.equals(dateCreated, order.dateCreated)) return false;
        if (!Objects.equals(dateClosed, order.dateClosed)) return false;
        if (!Objects.equals(comment, order.comment)) return false;
        if (!Objects.equals(lastEdited, order.lastEdited)) return false;
        if (!Objects.equals(orderedBy, order.orderedBy)) return false;
        return Objects.equals(total, order.total);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (creator != null ? creator.hashCode() : 0);
        result = 31 * result + (closed ? 1 : 0);
        result = 31 * result + (dateCreated != null ? dateCreated.hashCode() : 0);
        result = 31 * result + (dateClosed != null ? dateClosed.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (lastEdited != null ? lastEdited.hashCode() : 0);
        result = 31 * result + (orderedBy != null ? orderedBy.hashCode() : 0);
        result = 31 * result + (total != null ? total.hashCode() : 0);
        return result;
    }
}
