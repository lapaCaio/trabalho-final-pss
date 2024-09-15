package lp.caio.model;

import lp.caio.message.Message;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    private long id;
    private String name;
    private String password; 
    private boolean notUser;
    private boolean authorized;
    private LocalDate dtCreation;
    private Type type;

    public enum Type {
        USER, ADMIN
    }

    public User(String name, String password, boolean notUser, LocalDate dtCreation, Type type) {
        this.name = name;
        this.password = password;
        this.notUser = notUser;
        this.dtCreation = dtCreation;
        this.type = type;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isNotUser() {
        return notUser;
    }

    public void setNotUser(boolean notUser) {
        this.notUser = notUser;
    }

    public boolean isAuthorized() {
        return authorized;
    }

    public void setAuthorized(boolean autorized) {
        this.authorized = autorized;
    }

    public LocalDate getDtCreation() {
        return dtCreation;
    }

    public void setDtCreation(LocalDate dtCreation) {
        this.dtCreation = dtCreation;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + 
               ", Password: " + password + ", Not User: " + notUser +
               ", Date of Creation: " + dtCreation + ", Type: " + type;
    }
}
