package com.example.springmvcdemo.dev.controller.common;

import javax.servlet.http.HttpSession;

public class LoginState {
    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public LoginState() {
    }

    public boolean isLogin(HttpSession httpSession) {
        Object obj = httpSession.getAttribute("state");
        if (obj == null)
            return false;

        this.state = obj.toString();
        return state.matches("logged:username:([a-zA-Z0-9]{1,});role:Admin");
    }
}
