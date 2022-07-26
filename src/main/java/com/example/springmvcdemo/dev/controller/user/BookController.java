package com.example.springmvcdemo.dev.controller.user;

import com.example.springmvcdemo.dev.dto.CategoryDto;
import com.example.springmvcdemo.dev.model.Category;
import com.example.springmvcdemo.dev.service.BookService;
import com.example.springmvcdemo.dev.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class BookController {
    @Autowired
    CategoryService categoryService;

    @Autowired
    BookService bookService;

    @RequestMapping(value = "/sach-theo-the-loai/{id}", method = RequestMethod.GET)
    public ModelAndView getBookByCatalog(HttpSession httpSession, @PathVariable int id) {
        if(id <= 0)
            return new ModelAndView("redirect:/");

        List<CategoryDto> categories = categoryService.getAll();

        CategoryDto category = null;
        for(CategoryDto item : categories) {
            if(item.getId() == id)
                category = item;
        }

        if(category == null)
            return new ModelAndView("redirect:/");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/books-by-category");
        Object obj = httpSession.getAttribute("loginState");
        boolean isLogged = obj != null;

        modelAndView.addObject("isLogged", isLogged);
        modelAndView.addObject("categories", categories);
        modelAndView.addObject("category", category);
        modelAndView.addObject("bookNumber", bookService.countBook());
        modelAndView.addObject("booksByCategory", bookService.getBookByCategory(id));
        return modelAndView;
    }
}
