package com.ofd.ofd.view;

public class ViewLoginForm {
    private String login;
    private String password;

    public ViewLoginForm(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public ViewLoginForm() {
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

    @Override
    public String toString() {
        return "ViewUser [login=" + login + ", password=" + password + "]";
    }
}
