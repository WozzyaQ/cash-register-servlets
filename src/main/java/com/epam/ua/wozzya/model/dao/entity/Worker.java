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
    private Role role;



    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Worker worker = (Worker) o;

        if (id != worker.id) return false;
        if (login != null ? !login.equals(worker.login) : worker.login != null) return false;
        if (name != null ? !name.equals(worker.name) : worker.name != null) return false;
        if (surname != null ? !surname.equals(worker.surname) : worker.surname != null) return false;
        if (email != null ? !email.equals(worker.email) : worker.email != null) return false;
        if (password != null ? !password.equals(worker.password) : worker.password != null) return false;
        return role == worker.role;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }


}
