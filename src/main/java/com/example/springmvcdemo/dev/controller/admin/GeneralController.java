package com.example.springmvcdemo.dev.controller.admin;

import com.example.springmvcdemo.dev.service.AnalystService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/quan-tri")
public class GeneralController {
    @Autowired
    private AnalystService analystService;

    @RequestMapping(value = "/tong-quan", method = RequestMethod.GET)
    public ModelAndView overview(HttpSession httpSession) {
        Object obj = httpSession.getAttribute("loginState");
        if(obj  == null)
            return new ModelAndView("redirect:/tai-khoan/dang-nhap");

        String loginState = obj.toString();
        if(!loginState.matches("logged:true;username:([a-zA-Z0-9]{1,});role:Admin"))
            return new ModelAndView("redirect:/tai-khoan/dang-nhap");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/overview");
        String username = loginState.replace("logged:true;username;", "").replace(";role:Admin",  "");
        modelAndView.addObject("username", username);
        modelAndView.addObject("analyst", analystService.getAnalyst());
        return modelAndView;
    }
}
