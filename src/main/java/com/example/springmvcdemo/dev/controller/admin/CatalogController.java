package com.example.springmvcdemo.dev.controller.admin;

import com.example.springmvcdemo.dev.dto.CategoryDto;
import com.example.springmvcdemo.dev.model.Category;
import com.example.springmvcdemo.dev.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/admin/catalogController")
@Transactional
public class CatalogController {
    @Autowired
    private CategoryService catService;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ModelAndView getALl() {
        ModelAndView mav = new ModelAndView("admin/category/categories");
        List<Category> listCat = catService.getAll();
        mav.addObject("listCat", listCat);
        return mav;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("category") CategoryDto dto) {
        catService.saveOrUpdate(dto, null);
        return "redirect:/admin/catalogController/getAll";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@ModelAttribute("category") CategoryDto dto, @RequestParam Long id) {
        catService.saveOrUpdate(dto, id);
        return "redirect:/admin/catalogController/getAll";
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public String update(@ModelAttribute("category") CategoryDto dto ) {
        catService.saveOrUpdate(dto, dto.getId());

        return "getAll";
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ModelAndView getById(@RequestParam int id) {
        ModelAndView mav = new ModelAndView("admin/category/editCategory");
        Category category = catService.getById(id);
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
}
