package com.example.springmvcdemo.dev.controller.admin;

import com.example.springmvcdemo.dev.dto.AuthorDto;
import com.example.springmvcdemo.dev.dto.BookDto;
import com.example.springmvcdemo.dev.dto.BookFeaturedDto;
import com.example.springmvcdemo.dev.dto.CategoryDto;
import com.example.springmvcdemo.dev.model.Category;
import com.example.springmvcdemo.dev.service.*;
import com.example.springmvcdemo.dev.service.Impl.LoginServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

import static com.example.springmvcdemo.dev.service.Impl.LoginServiceImpl.loginState;


@Controller
@RequiredArgsConstructor
@RequestMapping("/quan-tri")
@Transactional
public class ManageCatalogController {
    private  final BookService bookService;
    private  final CategoryService catService;
    private  final AuthorService authorService;
    private  final PublishingHouseService publishingHouseService;
    private  final BookFeaturedService bookFeaturedService;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ModelAndView getALl() {
        ModelAndView mav = new ModelAndView("admin/category/categories");
        List<CategoryDto> listCat = catService.getAll();
        mav.addObject("listCat", listCat);
        return mav;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("category") CategoryDto dto) {
        catService.saveOrUpdate(dto, null);
        return "redirect:/admin/catalogController/getAll";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@ModelAttribute("category") CategoryDto dto, @RequestParam Integer id) {
        catService.saveOrUpdate(dto, id);
        return "redirect:/admin/catalogController/getAll";
    }

//    @RequestMapping(value = "/update", method = RequestMethod.PUT)
//    public String update(@ModelAttribute("category") CategoryDto dto ) {
//        catService.saveOrUpdate(dto, dto.getId());
//
//        return "getAll";
//    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ModelAndView getById(@RequestParam int id) {
        ModelAndView mav = new ModelAndView("admin/category/editCategory");
        CategoryDto category = catService.getById(id);
        mav.addObject("category", category);
        return mav;
    }

    @RequestMapping(value = "/new")
    public ModelAndView newCat(Map<String, Object> model) {
        model.put("category", new CategoryDto());
        return new ModelAndView("admin/category/newCategory");
    }


    @RequestMapping(value = "/user.htm", method = RequestMethod.GET)
    public ModelAndView user() {

        return new ModelAndView("user/index");
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView admin() {
        return new ModelAndView("admin/widget");
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list() {

        return new ModelAndView("admin/book/listBook");
    }

    @RequestMapping(value = {"/the-loai-cua-sach/{id}", "/the-loai-cua-sach/{id}/{message}"}, method = RequestMethod.GET)
    public ModelAndView editBookCategory(HttpSession httpSession, @PathVariable int id, @PathVariable(required = false) String message) {
        if (LoginServiceImpl.login(httpSession) != null)
            return LoginServiceImpl.login(httpSession);

        if (id <= 0)
            return new ModelAndView("redirect:/quan-tri/danh-sach-sach");

        BookDto bookInfo = bookService.getById(id);
        if (bookInfo == null)
            return new ModelAndView("redirect:/quan-tri/danh-sach-sach");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/edit-book-category");
        String username = loginState.replace("logged:true;username:", "").replace(";role:Admin", "");
        modelAndView.addObject("username", username);
        modelAndView.addObject("book", bookInfo);
        modelAndView.addObject("categoryContribute", new BookFeaturedDto());
        modelAndView.addObject("category", catService.getAll());
        if (message != null) {
            if (message.equals("add-success"))
                modelAndView.addObject("state", "Thêm thành công");
            else if (message.equals("add-failed"))
                modelAndView.addObject("state", "Thêm thất bại");
            else if (message.equals("delete-success"))
                modelAndView.addObject("state", "Xóa thành công");
            else if (message.equals("delete-failed"))
                modelAndView.addObject("state", "Xóa thất bại");
            else
                modelAndView.addObject("state", "Không xác định được nội dung thông báo");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/them-the-loai-cua-sach", method = RequestMethod.POST)
    public ModelAndView addBookCategory(HttpSession httpSession, @ModelAttribute("categoryContribute") BookFeaturedDto bookFeaturedDto) {
        if (LoginServiceImpl.login(httpSession) != null)
            return LoginServiceImpl.login(httpSession);

        BookDto bookDto = bookFeaturedService.addBookCategory(bookFeaturedDto);
        if (bookDto != null)
            return new ModelAndView("redirect:/quan-tri/the-loai-cua-sach/" + bookDto.getId() + "/add-success");

        return new ModelAndView("redirect:/quan-tri/the-loai-cua-sach/" + bookFeaturedDto.getBookId() + "/add-failed");
    }

    @RequestMapping(value = "/xoa-the-loai-cua-sach", method = RequestMethod.POST)
    public ModelAndView deleteCategory(HttpSession httpSession, @RequestParam(value = "id") Integer id,
                                       @RequestParam(value = "bookId") Integer bookId) {
        Object obj = httpSession.getAttribute("loginState");
        if (obj == null)
            return new ModelAndView("redirect:/tai-khoan/dang-nhap");

        String loginState = obj.toString();
        if (!loginState.matches("logged:true;username:([a-zA-Z0-9]{1,});role:Admin"))
            return new ModelAndView("redirect:/tai-khoan/dang-nhap");

        if (id <= 0)
            return new ModelAndView("redirect:/quan-tri/danh-sach-sach");

        CategoryDto dto = catService.getById(id);
        if (bookFeaturedService.deleteByCategoryId(id))
            return new ModelAndView("redirect:/quan-tri/the-loai-cua-sach/" + bookId + "/delete-success");

        return new ModelAndView("redirect:/quan-tri/the-loai-cua-sach/" + bookId + "/delete-failed");
    }
}
