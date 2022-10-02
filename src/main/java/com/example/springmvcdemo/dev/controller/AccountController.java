package com.example.springmvcdemo.dev.controller;

import com.example.springmvcdemo.dev.dto.UserDto;
import com.example.springmvcdemo.dev.service.UserService;
import com.example.springmvcdemo.dev.validator.UserLoginValidator;
import com.example.springmvcdemo.dev.validator.UserRegisterValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class AccountController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "tai-khoan/dang-nhap", method = RequestMethod.GET)
    public ModelAndView login(HttpSession httpSession) {
        Object obj = httpSession.getAttribute("loginState");
        if (obj != null)
            return new ModelAndView("redirect:/trang-chu");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("account/login");
        modelAndView.addObject("userLogin", new UserDto());
        return modelAndView;
    }

    @RequestMapping(value = "tai-khoan/dang-ky", method = RequestMethod.GET)
    public ModelAndView register(HttpSession httpSession) {
        Object obj = httpSession.getAttribute("loginState");
        if (obj != null)
            return new ModelAndView("redirect:/trang-chu");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("account/register");
        modelAndView.addObject("userRegister", new UserDto());
        return modelAndView;
    }

    @RequestMapping(value = "tai-khoan/dang-nhap", method = RequestMethod.POST)
    public ModelAndView login(HttpSession httpSession, @ModelAttribute("userLogin") UserDto userLogin, BindingResult bindingResult,
                              UserLoginValidator userLoginValidator) {
        Object obj = httpSession.getAttribute("loginState");
        if (obj != null)
            return new ModelAndView("redirect:/");

        userLoginValidator.validate(userLogin, bindingResult);
        if (bindingResult.hasErrors())
            return new ModelAndView("account/login", "userLogin", userLogin);

        if (userService.login(userLogin)) {
            UserDto userDto = userService.getUser(userLogin.getUsername());
            httpSession.setAttribute("loginState", "logged:true;username:" + userLogin.getUsername() + ";role:" + userDto.getRole().getName());
            if (userDto.getRole().getName().equals("Admin"))
                return new ModelAndView("redirect:/quan-tri/tong-quan");
            return new ModelAndView("redirect:/");
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("account/login");
        modelAndView.addObject("userLogin", new UserDto());
        modelAndView.addObject("state", "Đăng nhập không thành công, có thể dữ liệu chưa hợp lệ hoặc không tồn tại tài khoản này");
        return modelAndView;
    }

    @RequestMapping(value = "tai-khoan/dang-ky", method = RequestMethod.POST)
    public ModelAndView register(HttpSession httpSession, @ModelAttribute("userRegister") UserDto userRegister, BindingResult bindingResult,
                                 UserRegisterValidator userRegisterValidator) {
        Object obj = httpSession.getAttribute("loginState");
        if (obj != null)
            return new ModelAndView("redirect:/trang-chu");

        userRegisterValidator.validate(userRegister, bindingResult);
        if (bindingResult.hasErrors())
            return new ModelAndView("account/register", "userRegister", userRegister);

        if(userService.register(userRegister) != null)
            return new ModelAndView("redirect:/trang-chu");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("account/register");
        modelAndView.addObject("userRegister", new UserDto());
        modelAndView.addObject("state", "Đăng ký không thành công, có thể dữ liệu không hợp lệ hoặc đã tồn tại tài khoản này");
        return modelAndView;
    }

    @RequestMapping(value = "tai-khoan/dang-xuat", method = RequestMethod.GET)
    public ModelAndView Logout(HttpSession httpSession) {
        Object obj = httpSession.getAttribute("loginState");
        if(obj == null)
            return new ModelAndView("redirect:/trang-chu");

        httpSession.removeAttribute("loginState");
        return new ModelAndView("redirect:/trang-chu");
    }
}
