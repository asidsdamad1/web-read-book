package com.example.springmvcdemo.dev.service.Impl;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

public class LoginServiceImpl {
    public static String loginState;
    public static ModelAndView login(HttpSession httpSession) {
        Object obj = httpSession.getAttribute("loginState");
        if (obj == null)
            return new ModelAndView("redirect:/tai-khoan/dang-nhap");

        loginState = obj.toString();
        if (!loginState.matches("logged:true;username:([a-zA-Z0-9]{1,});role:Admin"))
            return new ModelAndView("redirect:/tai-khoan/dang-nhap");

        return null;
    }
}
