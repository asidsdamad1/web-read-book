package com.example.springmvcdemo.dev.controller.admin;

import com.example.springmvcdemo.dev.controller.common.LoginState;
import com.example.springmvcdemo.dev.dto.AuthorDto;
import com.example.springmvcdemo.dev.model.Author;
import com.example.springmvcdemo.dev.service.AuthorService;
import com.example.springmvcdemo.dev.validator.AuthorValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/quan-tri")
public class ManageAuthorController {

    @Autowired
    private AuthorService authorService;


    @RequestMapping(value = {"/danh-sach-tac-gia", "/danh-sach-tac-gia/{message}"}, method = RequestMethod.GET)
    public ModelAndView authorList(HttpSession httpSession, @PathVariable(name = "message", required = false) String message) {
        Object obj = httpSession.getAttribute("loginState");
        if (obj == null)
            return new ModelAndView("redirect:/tai-khoan/dang-nhap");

        String loginState = obj.toString();
        if (!loginState.matches("logged:true;username:([a-zA-Z0-9]{1,});role:Admin"))
            return new ModelAndView("redirect:/tai-khoan/dang-nhap");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/book-author-list");
        String username = loginState.replace("logged:true;username:", "").replace(";role:Admin", "");

        modelAndView.addObject("username", username);
        modelAndView.addObject("authors", authorService.getAll());
        if (message != null) {
            switch (message) {
                case "delete-success":
                    modelAndView.addObject("state", "Xoá thành công");
                    break;
                case "delete-failed":
                    modelAndView.addObject("state", "Xoá thất bại");
                    break;
                default:
                    modelAndView.addObject("state", "Không xác định được nội dung thông báo");
                    break;
            }
        }
        return modelAndView;
    }

    @RequestMapping(value = {"/tao-moi-tac-gia", "/tao-moi-tac-gia/{message}"}, method = RequestMethod.GET)
    public ModelAndView create(HttpSession httpSession, @PathVariable(name = "message", required = false) String message) {
        Object obj = httpSession.getAttribute("loginState");
        if (obj == null)
            return new ModelAndView("redirect:/tai-khoan/dang-nhap");

        String loginState = obj.toString();
        if (!loginState.matches("logged:true;username:([a-zA-Z0-9]{1,});role:Admin"))
            return new ModelAndView("redirect:/tai-khoan/dang-nhap");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/create-book-author");
        String username = loginState.replace("logged:true;username:", "").replace(";role:Admin", "");

        modelAndView.addObject("username", username);
        modelAndView.addObject("author", new AuthorDto());
        if (message != null) {
            switch (message) {
                case "add-success":
                    modelAndView.addObject("state", "Thêm thành công");
                    break;
                case "add-failed":
                    modelAndView.addObject("state", "Thêm thất bại");
                    break;
                default:
                    modelAndView.addObject("state", "Không xác định được nội dung thông báo");
                    break;
            }
        }
        return modelAndView;
    }

    @RequestMapping(value = {"/tao-moi-tac-gia"}, method = RequestMethod.POST)
    public ModelAndView create(HttpSession httpSession, @ModelAttribute("author") AuthorDto author,
                               BindingResult bindingResult, AuthorValidator authorValidator) {
        Object obj = httpSession.getAttribute("loginState");
        if (obj == null)
            return new ModelAndView("redirect:/tai-khoan/dang-nhap");

        String loginState = obj.toString();
        if (!loginState.matches("logged:true;username:([a-zA-Z0-9]{1,});role:Admin"))
            return new ModelAndView("redirect:/tai-khoan/dang-nhap");

        authorValidator.validate(author, bindingResult);

        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("admin/create-book-author");
            String username = loginState.replace("logged:true;username;", "").replace(";role:Admin", "");
            modelAndView.addObject("username", username);
            modelAndView.addObject("author", author);
            return modelAndView;
        }

        if (authorService.saveOrUpdater(author, null) != null)
            return new ModelAndView("redirect:/quan-tri/tao-moi-tac-gia/add-success");

        return new ModelAndView("redirect:/quan-tri/tao-moi-tac-gia/add-failed");
    }

    @RequestMapping(value = {"/chinh-sua-tac-gia/{id}", "/chinh-sua-tac-gia/{id}/{message}"}, method = RequestMethod.GET)
    public ModelAndView update(HttpSession httpSession, @PathVariable("id") int id, @PathVariable(name = "message", required = false) String message) {
        Object obj = httpSession.getAttribute("loginState");
        if (obj == null)
            return new ModelAndView("redirect:/tai-khoan/dang-nhap");

        String loginState = obj.toString();
        if (!loginState.matches("logged:true;username:([a-zA-Z0-9]{1,});role:Admin"))
            return new ModelAndView("redirect:/tai-khoan/dang-nhap");

        if (id <= 0)
            return new ModelAndView("redirect:/quan-tri/danh-sach-tac-gia");

        AuthorDto author = authorService.getById(id);
        if(author == null)
            return new ModelAndView("redirect:/quan-tri/danh-sach-tac-gia");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/update-book-author");
        String username = loginState.replace("logged:true;username:", "").replace(";role:Admin", "");

        modelAndView.addObject("username", username);
        modelAndView.addObject("author", author);
        if (message != null) {
            switch (message) {
                case "edit-success":
                    modelAndView.addObject("state", "Thêm thành công");
                    break;
                case "edit-failed":
                    modelAndView.addObject("state", "Thêm thất bại");
                    break;
                default:
                    modelAndView.addObject("state", "Không xác định được nội dung thông báo");
                    break;
            }
        }
        return modelAndView;
    }

    @RequestMapping(value = {"/chinh-sua-tac-gia/{id}"}, method = RequestMethod.POST)
    public ModelAndView update(HttpSession httpSession, @ModelAttribute("author") AuthorDto author,
                               BindingResult bindingResult, AuthorValidator authorValidator) {
        Object obj = httpSession.getAttribute("loginState");
        if (obj == null)
            return new ModelAndView("redirect:/tai-khoan/dang-nhap");

        String loginState = obj.toString();
        if (!loginState.matches("logged:true;username:([a-zA-Z0-9]{1,});role:Admin"))
            return new ModelAndView("redirect:/tai-khoan/dang-nhap");

        authorValidator.validate(author, bindingResult);

        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("admin/update-book-author");
            String username = loginState.replace("logged:true;username;", "").replace(";role:Admin", "");
            modelAndView.addObject("username", username);
            modelAndView.addObject("author", author);
            return modelAndView;
        }

        if (authorService.saveOrUpdater(author, author.getId()) != null)
            return new ModelAndView("redirect:/quan-tri/chinh-sua-tac-gia/" +  author.getId() + "/edit-success");

        return new ModelAndView("redirect:/quan-tri/chinh-sua-tac-gia/" +  author.getId() + "/edit-failed");
    }

    @RequestMapping(value = "/xoa-tac-gia", method = RequestMethod.POST)
    public ModelAndView DeleteBookAuthor(HttpSession httpSession, @RequestParam(value = "id") int id) {
        Object obj = httpSession.getAttribute("loginState");
        if (obj == null)
            return new ModelAndView("redirect:/tai-khoan/dang-nhap");

        String loginState = obj.toString();
        if (!loginState.matches("logged:true;username:([a-zA-Z0-9]{1,});role:Admin"))
            return new ModelAndView("redirect:/tai-khoan/dang-nhap");

        if(id <= 0)
            return new ModelAndView("redirect:/quan-tri/danh-sach-tac-gia");

        if(authorService.delete(id))
            return new ModelAndView("redirect:/quan-tri/danh-sach-tac-gia/delete-success");

        return new ModelAndView("redirect:/quan-tri/danh-sach-tac-gia/delete-failed");
    }

    @RequestMapping(value = "chi-tiet-tac-gia/{id}", method = RequestMethod.GET)
    public ModelAndView authorDetail(HttpSession httpSession, @PathVariable int id) {
        Object obj = httpSession.getAttribute("loginState");
        if (obj == null)
            return new ModelAndView("redirect:/tai-khoan/dang-nhap");

        String loginState = obj.toString();
        if (!loginState.matches("logged:true;username:([a-zA-Z0-9]{1,});role:Admin"))
            return new ModelAndView("redirect:/tai-khoan/dang-nhap");

        if(id <= 0)
            return new ModelAndView("redirect:/quan-tri/danh-sach-tac-gia");

        AuthorDto authorDto = authorService.getById(id);
        if(authorDto == null)
            return new ModelAndView("redirect:/quan-tri/danh-sach-tac-gia");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/book-author-detail");
        String username = loginState.replace("logged:true;username:", "").replace(";role:Admin", "");
        modelAndView.addObject("username", username);
        modelAndView.addObject("author", authorDto);

        return modelAndView;
    }
}
