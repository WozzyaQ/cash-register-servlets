package com.epam.ua.wozzya.model.dao.entity;

import java.math.BigDecimal;
import java.sql.Date;

public class Order extends AbstractEntity{
    private long id;
    private long creator;
    private boolean closed;
    private Date dateCreated;
    private Date dateClosed;
    private String comment;
    private Date lastEdited;
    private String orderedBy;
    private BigDecimal total;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCreatorId() {
        return creator;
    }

    public void setCreator(long creator) {
        this.creator = creator;
    }

    public boolean getClosed() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != order.id) return false;
        if (creator != order.creator) return false;
        if (closed != order.closed) return false;
        if (dateCreated != null ? !dateCreated.equals(order.dateCreated) : order.dateCreated != null) return false;
        if (dateClosed != null ? !dateClosed.equals(order.dateClosed) : order.dateClosed != null) return false;
        if (comment != null ? !comment.equals(order.comment) : order.comment != null) return false;
        if (lastEdited != null ? !lastEdited.equals(order.lastEdited) : order.lastEdited != null) return false;
        if (orderedBy != null ? !orderedBy.equals(order.orderedBy) : order.orderedBy != null) return false;
        return total != null ? total.equals(order.total) : order.total == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (creator ^ (creator >>> 32));
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
