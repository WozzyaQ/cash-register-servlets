package com.epam.ua.wozzya.model.dao.entity;

import com.epam.ua.wozzya.model.dao.entity.enumeration.Role;

import java.util.List;
import java.util.Objects;

public class Worker extends AbstractEntity{
    private long id;
    private String login;
    private String name;
    private String surname;
    private String email;
    private String password;
    private List<Role> roles;

    private List<OrderProduct> createdOrders;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<OrderProduct> getCreatedOrders() {
        return createdOrders;
    }

    public void setCreatedOrders(List<OrderProduct> createdOrders) {
        this.createdOrders = createdOrders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Worker worker = (Worker) o;

        if (id != worker.id) return false;
        if (!Objects.equals(login, worker.login)) return false;
        if (!Objects.equals(name, worker.name)) return false;
        if (!Objects.equals(surname, worker.surname)) return false;
        if (!Objects.equals(email, worker.email)) return false;
        return Objects.equals(password, worker.password);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
