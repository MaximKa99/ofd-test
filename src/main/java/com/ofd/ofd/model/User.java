package com.ofd.ofd.model;

public class User {
    private String id;
    private String login;
    private String password;
    private Integer balance;

    public User(String login, String password, Integer balance) {
        this.login = login;
        this.password = password;
        this.balance = balance;
    }

    public User() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getBalance() {
        return balance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }
}
