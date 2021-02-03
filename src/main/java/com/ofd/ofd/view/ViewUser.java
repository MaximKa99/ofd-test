package com.ofd.ofd.view;

public class ViewUser {
    private String login;
    private Integer balance;
    private final int status = 0;

    public ViewUser(String login, Integer balance) {
        this.login = login;
        this.balance = balance;
    }

    public ViewUser() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }
}
