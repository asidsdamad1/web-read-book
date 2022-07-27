package com.example.springmvcdemo.dev.controller.user;

import com.example.springmvcdemo.dev.service.BookService;
import com.example.springmvcdemo.dev.service.CategoryService;
import com.example.springmvcdemo.dev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class HomeUserController {
    private final BookService bookService;

    private final CategoryService categoryService;

    private final UserService userService;

    public HomeUserController(BookService bookService, CategoryService categoryService, UserService userService) {
        this.bookService = bookService;
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @RequestMapping(value = {"/", "/trang-chu"}, method = RequestMethod.GET)
    public ModelAndView index(HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/index");
        Object obj = httpSession.getAttribute("loginState");
        boolean isLogged = false;
        boolean isAdmin = false;
        if(obj != null) {
            isLogged = true;
            String loginState = obj.toString();
            if(loginState.matches("logged:true;username:([a-zA-Z0-9]{1,});role:Admin"))
                isAdmin = true;
        }

        modelAndView.addObject("isLogged", isLogged);
        modelAndView.addObject("isAdmin", isAdmin);
        modelAndView.addObject("lastestBooks", bookService.getLastestBooks(10));
        modelAndView.addObject("booksByViews", bookService.getBookByViews(10));
        return modelAndView;
    }

    @RequestMapping(value = "/danh-sach-the-loai", method = RequestMethod.GET)
    public ModelAndView CategoryList(HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/category-list");
        Object obj = httpSession.getAttribute("loginState");
        boolean isLogged = false;
        if(obj != null)
            isLogged = true;

        modelAndView.addObject("isLogged", isLogged);
        modelAndView.addObject("categories", categoryService.getAll());
        modelAndView.addObject("bookNumber", bookService.countBook());
        return modelAndView;
    }
}
