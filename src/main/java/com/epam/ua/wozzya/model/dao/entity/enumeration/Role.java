package com.epam.ua.wozzya.model.dao.entity.enumeration;

public enum Role {
    CASHIER, COMMODITY_EXPERT, SENIOR_CASHIER, ON_HOLD, ADMIN;

    public String getName() {
        return name().toLowerCase();
    }
}
