package com.ofd.ofd.view;

public class ViewAuthRequest {
    private String login;
    private String password;

    public ViewAuthRequest(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public ViewAuthRequest() {
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

    
}
