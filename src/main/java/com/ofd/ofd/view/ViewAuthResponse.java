package com.ofd.ofd.view;

public class ViewAuthResponse {
    private String token;
    private String id;

    public ViewAuthResponse(String token, String id) {
        this.token = token;
        this.id = id;
    }

    public ViewAuthResponse() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }    
}
