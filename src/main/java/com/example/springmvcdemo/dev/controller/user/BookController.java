package com.example.springmvcdemo.dev.controller.user;

import com.example.springmvcdemo.dev.dto.BookDto;
import com.example.springmvcdemo.dev.dto.CategoryDto;
import com.example.springmvcdemo.dev.service.BookService;
import com.example.springmvcdemo.dev.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
        if (id <= 0)
            return new ModelAndView("redirect:/");

        List<CategoryDto> categories = categoryService.getAll();

        CategoryDto category = null;
        for (CategoryDto item : categories) {
            if (item.getId() == id)
                category = item;
        }

        if (category == null)
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

    @RequestMapping(value = "/chi-tiet-sach/{id}")
    public ModelAndView bookDetail(HttpSession httpSession, @PathVariable int id) {
        if (id <= 0)
            return new ModelAndView("redirect:/");

        Object obj = httpSession.getAttribute("loginState");
        boolean isLoggin = obj != null;

        BookDto bookDto = bookService.getById(id);
        if (bookDto == null)
            return new ModelAndView("redirect:/");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/book-detail");
        modelAndView.addObject("isLoggin", isLoggin);
        modelAndView.addObject("bookInfo", bookDto);
        modelAndView.addObject("booksByCategory", bookService.getBookByCategory(bookDto.getCategory().getId()));
        return modelAndView;
    }

    @RequestMapping(value = "/tang-luot-xem", method = RequestMethod.POST)
    public ResponseEntity<String> increaseView(@RequestParam(value = "id") int id) {
        if (id <= 0)
            return new ResponseEntity<String>("Invalid", HttpStatus.BAD_REQUEST);

        if (bookService.increaseView(id))
            return new ResponseEntity<String>("Success", HttpStatus.OK);

        return new ResponseEntity<String>("Failed", HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/thich-sach", method = RequestMethod.POST)
    public ResponseEntity<String> upvote(@RequestParam(value = "id") int id) {
        if (id <= 0)
            return new ResponseEntity<String>("Invalid", HttpStatus.BAD_REQUEST);

        if (bookService.upvote(id))
            return new ResponseEntity<String>("Success", HttpStatus.OK);

        return new ResponseEntity<String>("Failed", HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/khong-thich-sach", method = RequestMethod.POST)
    public ResponseEntity<String> downvote(@RequestParam(value = "id") int id) {
        if (id <= 0)
            return new ResponseEntity<String>("Invalid", HttpStatus.BAD_REQUEST);

        if (bookService.downvote(id))
            return new ResponseEntity<String>("Success", HttpStatus.OK);

        return new ResponseEntity<String>("Failed", HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/xem-sach-online/{id}", method = RequestMethod.GET)
    public ModelAndView ReadBook(HttpSession httpSession, @PathVariable int id) {
        if(id <= 0)
            return new ModelAndView("redirect:/");

        BookDto book = bookService.getById(id);
        if(book == null)
            return new ModelAndView("redirect:/");

        if(book.getPdf() == null)
            return new ModelAndView("redirect:/");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/read-book");
        Object obj = httpSession.getAttribute("loginState");
        boolean isLogged = obj != null;

        modelAndView.addObject("isLogged", isLogged);
        modelAndView.addObject("book", book);
        return modelAndView;
    }
}
