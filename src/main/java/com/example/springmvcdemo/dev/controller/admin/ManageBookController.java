package com.example.springmvcdemo.dev.controller.admin;

import com.example.springmvcdemo.dev.dto.*;
import com.example.springmvcdemo.dev.service.*;
import com.example.springmvcdemo.dev.service.Impl.LoginServiceImpl;
import com.example.springmvcdemo.dev.validator.BookValidator;
import com.example.springmvcdemo.dev.validator.ImageUploadValidator;
import com.example.springmvcdemo.dev.validator.PdfUploadValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

import static com.example.springmvcdemo.dev.service.Impl.LoginServiceImpl.loginState;

@Controller
@RequestMapping("/quan-tri")
public class ManageBookController {
    @Autowired
    BookService bookService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    AuthorService authorService;

    @Autowired
    PublishingHouseService publishingHouseService;

    @Autowired
    BookAuthorService bookAuthorService;


    @RequestMapping(value = {"/danh-sach-sach", "/danh-sach-sach/{message}"}, method = RequestMethod.GET)
    public ModelAndView getAll(HttpSession httpSession, @PathVariable(required = false) String message) {
        if (LoginServiceImpl.login(httpSession) != null)
            return LoginServiceImpl.login(httpSession);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/book-list");
        String username = loginState.replace("logged:true;username:", "").replace(";role:Admin", "");
        modelAndView.addObject("username", username);
        modelAndView.addObject("books", bookService.getAll());
        if (message != null) {
            if (message.equals("delete-success"))
                modelAndView.addObject("state", "Xoá thành công");
            else if (message.equals("delete-failed"))
                modelAndView.addObject("state", "Xoá thất bại");
            else
                modelAndView.addObject("state", "Không xác định được nội dung thông báo");
        }
        return modelAndView;
    }

    @RequestMapping(value = {"/chi-tiet-sach/{id}"}, method = RequestMethod.GET)
    public ModelAndView bookDetail(HttpSession httpSession, @PathVariable int id) {
        if( LoginServiceImpl.login(httpSession) !=  null)
            return LoginServiceImpl.login(httpSession);

        if (id <= 0)
            return new ModelAndView("redirect:/quan-tri/danh-sach-sach");

        BookDto bookDto = bookService.getById(id);
        if (bookDto == null)
            return new ModelAndView("redirect:/quan-tri/danh-sach-sach");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/book-detail");
        String username = loginState.replace("logged:true;username:", "").replace(";role:Admin", "");
        modelAndView.addObject("username", username);
        modelAndView.addObject("bookInfo", bookDto);
        return modelAndView;
    }

    @RequestMapping(value = {"/tao-moi-sach", "/tao-moi-sach/{message}"}, method = RequestMethod.GET)
    public ModelAndView create(HttpSession httpSession, @PathVariable(required = false) String message) {
        if (LoginServiceImpl.login(httpSession) != null)
            return LoginServiceImpl.login(httpSession);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/create-book");
        String username = loginState.replace("logged:true;username:", "").replace(";role:Admin", "");
        modelAndView.addObject("username", username);
        modelAndView.addObject("book", new BookDto());
        modelAndView.addObject("categories", categoryService.getAll());
        modelAndView.addObject("authors", authorService.getAll());
        modelAndView.addObject("publishingHouses", publishingHouseService.getAll());
        if (message != null) {
            if (message.equals("add-success"))
                modelAndView.addObject("state", "Thêm thành công");
            else if (message.equals("add-failed"))
                modelAndView.addObject("state", "Thêm thất bại");
            else
                modelAndView.addObject("state", "Không xác định được nội dung thông báo");
        }
        return modelAndView;
    }

    @RequestMapping(value = {"/tao-moi-sach"}, method = RequestMethod.POST)
    public ModelAndView create(HttpSession httpSession, @ModelAttribute("book") BookDto book,
                               BindingResult bindingResult, BookValidator bookValidator) {
        if (LoginServiceImpl.login(httpSession) != null)
            return LoginServiceImpl.login(httpSession);

        bookValidator.validate(book, bindingResult);

        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("admin/create-book");
            String username = loginState.replace("logged:true;username;", "").replace(";role:Admin", "");
            modelAndView.addObject("username", username);
            modelAndView.addObject("book", book);
            modelAndView.addObject("categories", categoryService.getAll());
            modelAndView.addObject("authors", authorService.getAll());
            modelAndView.addObject("publishingHouses", publishingHouseService.getAll());
            return modelAndView;
        }

        if (bookService.saveOrUpdate(book, null) != null)
            return new ModelAndView("redirect:/quan-tri/tao-moi-sach/add-success");

        return new ModelAndView("redirect:/quan-tri/tao-moi-sach/add-failed");
    }

    @RequestMapping(value = {"/chinh-sua-sach/{id}", "/chinh-sua-sach/{id}/{message}"}, method = RequestMethod.GET)
    public ModelAndView update(HttpSession httpSession, @PathVariable int id, @PathVariable(required = false) String message) {
        if (LoginServiceImpl.login(httpSession) != null)
            return LoginServiceImpl.login(httpSession);

        if (id <= 0)
            return new ModelAndView("redirect:/quan-tri/danh-sach-sach");

        BookDto bookDto = bookService.getById(id);
        if (bookDto == null)
            return new ModelAndView("redirect:/quan-tri/danh-sach-sach");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/update-book");
        String username = loginState.replace("logged:true;username:", "").replace(";role:Admin", "");
        modelAndView.addObject("username", username);
        modelAndView.addObject("book", bookDto);
        modelAndView.addObject("categories", categoryService.getAll());
        modelAndView.addObject("publishingHouses", publishingHouseService.getAll());
        if (message != null) {
            if (message.equals("edit-success"))
                modelAndView.addObject("state", "Chỉnh sửa thành công");
            else if (message.equals("edit-failed"))
                modelAndView.addObject("state", "Chỉnh sửa thất bại");
            else
                modelAndView.addObject("state", "Không xác định được nội dung thông báo");
        }
        return modelAndView;
    }

    @RequestMapping(value = {"/chinh-sua-sach/{id}"}, method = RequestMethod.POST)
    public ModelAndView bookDetail(HttpSession httpSession, @ModelAttribute("book") BookDto book,
                                   BindingResult bindingResult, BookValidator bookValidator) {
        if (LoginServiceImpl.login(httpSession) != null)
            return LoginServiceImpl.login(httpSession);

        bookValidator.validate(book, bindingResult);

        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("admin/update-book");
            String username = loginState.replace("logged:true;username;", "").replace(";role:Admin", "");
            modelAndView.addObject("username", username);
            modelAndView.addObject("book", book);
            modelAndView.addObject("categories", categoryService.getAll());
            modelAndView.addObject("publishingHouses", publishingHouseService.getAll());
            return modelAndView;
        }

        if (bookService.saveOrUpdate(book, book.getId()) != null)
            return new ModelAndView("redirect:/quan-tri/chinh-sua-sach/{id}/edit-success");

        return new ModelAndView("redirect:/quan-tri/chinh-sua-sach/{id}/edit-failed");
    }

    @RequestMapping(value = "/xoa-sach", method = RequestMethod.POST)
    public ModelAndView delete(HttpSession httpSession, @RequestParam(value = "id") int id) {
        if (LoginServiceImpl.login(httpSession) != null)
            return LoginServiceImpl.login(httpSession);

        if (id <= 0)
            return new ModelAndView("redirect:/quan-tri/danh-sach-sach");

        if (bookService.delete(id))
            return new ModelAndView("redirect:/quan-tri/danh-sach-sach/delete-success");

        return new ModelAndView("redirect:/quan-tri/danh-sach-sach/delete-failed");
    }

    @RequestMapping(value = {"/hinh-anh-cua-sach/{id}", "/hinh-anh-cua-sach/{id}/{message}"}, method = RequestMethod.GET)
    public ModelAndView editImg(HttpSession httpSession, @PathVariable int id, @PathVariable(required = false) String message) {
        if (LoginServiceImpl.login(httpSession) != null)
            return LoginServiceImpl.login(httpSession);

        BookDto book = bookService.getById(id);
        if (book == null)
            return new ModelAndView("redirect:/tai-khoan/dang-nhap");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/edit-image");
        String username = loginState.replace("logged:true;username:", "").replace(";role:Admin", "");
        modelAndView.addObject("username", username);
        modelAndView.addObject("book", book);
        modelAndView.addObject("imgUpload", new ImageUpload(id, null));
        if (message != null) {
            switch (message) {
                case "add-success":
                    modelAndView.addObject("state", "Thêm thành công");
                    break;
                case "add-failed":
                    modelAndView.addObject("state", "Thêm thất bại");
                    break;
                case "delete-success":
                    modelAndView.addObject("state", "Xóa thành công");
                    break;
                case "delete-failed":
                    modelAndView.addObject("state", "Xóa thất bại");
                    break;
                default:
                    modelAndView.addObject("state", "Không xác định được nội dung thông báo");
                    break;
            }
        }
        return modelAndView;
    }

    @RequestMapping(value = "/them-hinh-anh-cua-sach", method = RequestMethod.POST)
    public ModelAndView AddImage(HttpSession httpSession,
                                 @ModelAttribute("imgUpload") ImageUpload imageUpload,
                                 BindingResult bindingResult,
                                 ImageUploadValidator imageUploadValidator) {

        if (LoginServiceImpl.login(httpSession) != null)
            return LoginServiceImpl.login(httpSession);


        BookDto book = bookService.getById(imageUpload.getBookId());
        if (book == null)
            return new ModelAndView("redirect:/danh-sach-sach");

        imageUploadValidator.validate(imageUpload, bindingResult);
        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("admin/edit-image");
            String username = loginState.replace("logged:true;username:", "").replace(";role:Admin", "");
            modelAndView.addObject("username", username);
            modelAndView.addObject("book", book);
            modelAndView.addObject("imgUpload", imageUpload);
            return modelAndView;
        }

        CommonsMultipartFile commonsMultipartFile = imageUpload.getCommonsMultipartFile();
        if (bookService.addImage(imageUpload.getBookId(), commonsMultipartFile.getOriginalFilename())) {
            String dir = httpSession.getServletContext().getRealPath("/file-upload/images");
            File file = new File(dir + File.separator + commonsMultipartFile.getOriginalFilename());
            if (file.exists())
                return new ModelAndView("redirect:/quan-tri/hinh-anh-cua-sach/" + imageUpload.getBookId() + "/add-failed");

            try {
                commonsMultipartFile.transferTo(file);
                return new ModelAndView("redirect:/quan-tri/hinh-anh-cua-sach/" + imageUpload.getBookId() + "/add-success");
            } catch (IllegalStateException | IOException e) {
                return new ModelAndView("redirect:/quan-tri/hinh-anh-cua-sach/" + imageUpload.getBookId() + "/add-failed");
            }
        }

        return new ModelAndView("redirect:/quan-tri/hinh-anh-cua-sach/" + imageUpload.getBookId() + "/add-failed");
    }

    @RequestMapping(value = "/xoa-hinh-anh-cua-sach", method = RequestMethod.POST)
    public ModelAndView deleteImg(HttpSession httpSession, @RequestParam(value = "id") int id) {

        if (LoginServiceImpl.login(httpSession) != null)
            return LoginServiceImpl.login(httpSession);

        if (id <= 0)
            return new ModelAndView("redirect:/tai-khoan/dang-nhap");

        BookDto book = bookService.getById(id);
        if (book == null)
            return new ModelAndView("redirect:/quan-tri/danh-sach-sach");

        if (book.getImg().equals("book-default.png"))
            return new ModelAndView("redirect:/quan-tri/hinh-anh-cua-sach/" + id + "/delete-failed");

        if (bookService.deleteImage(id)) {
            String dir = httpSession.getServletContext().getRealPath("/file-upload/images");
            File file = new File(dir + File.separator + book.getImg());
            if (file.exists())
                file.delete();
            return new ModelAndView("redirect:/quan-tri/hinh-anh-cua-sach/" + id + "/delete-success");
        }

        return new ModelAndView("redirect:/quan-tri/hinh-anh-cua-sach/" + id + "/delete-failed");
    }

    @RequestMapping(value = {"/pdf-cua-sach/{id}", "/pdf-cua-sach/{id}/{message}"}, method = RequestMethod.GET)
    public ModelAndView editPdf(HttpSession httpSession, @PathVariable int id, @PathVariable(required = false) String message) {
        if (LoginServiceImpl.login(httpSession) != null)
            return LoginServiceImpl.login(httpSession);

        if (id <= 0)
            return new ModelAndView("redirect:/quan-tri/danh-sach-sach");

        BookDto book = bookService.getById(id);
        if (book == null)
            return new ModelAndView("redirect:/tai-khoan/dang-nhap");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/edit-pdf");
        String username = loginState.replace("logged:true;username:", "").replace(";role:Admin", "");
        modelAndView.addObject("username", username);
        modelAndView.addObject("book", book);
        modelAndView.addObject("pdfUpload", new PdfUpload(id, null));
        if (message != null) {
            switch (message) {
                case "add-success":
                    modelAndView.addObject("state", "Thêm thành công");
                    break;
                case "add-failed":
                    modelAndView.addObject("state", "Thêm thất bại");
                    break;
                case "delete-success":
                    modelAndView.addObject("state", "Xóa thành công");
                    break;
                case "delete-failed":
                    modelAndView.addObject("state", "Xóa thất bại");
                    break;
                default:
                    modelAndView.addObject("state", "Không xác định được nội dung thông báo");
                    break;
            }
        }
        return modelAndView;
    }

    @RequestMapping(value = "/them-pdf-cua-sach", method = RequestMethod.POST)
    public ModelAndView addPdf(HttpSession httpSession,
                               @ModelAttribute("pdfUpload") PdfUpload pdfUpload,
                               BindingResult bindingResult,
                               PdfUploadValidator pdfUploadValidator) {

        if (LoginServiceImpl.login(httpSession) != null)
            return LoginServiceImpl.login(httpSession);


        BookDto book = bookService.getById(pdfUpload.getBookId());
        if (book == null)
            return new ModelAndView("redirect:/danh-sach-sach");

        pdfUploadValidator.validate(pdfUpload, bindingResult);
        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("admin/edit-pdf");
            String username = loginState.replace("logged:true;username:", "").replace(";role:Admin", "");
            modelAndView.addObject("username", username);
            modelAndView.addObject("book", book);
            modelAndView.addObject("pdfUpload", pdfUpload);
            return modelAndView;
        }

        CommonsMultipartFile commonsMultipartFile = pdfUpload.getCommonsMultipartFile();
        if (bookService.addPdf(pdfUpload.getBookId(), commonsMultipartFile.getOriginalFilename())) {
            String dir = httpSession.getServletContext().getRealPath("/file-upload/pdfs");
            File file = new File(dir + File.separator + commonsMultipartFile.getOriginalFilename());
            if (file.exists())
                return new ModelAndView("redirect:/quan-tri/pdf-cua-sach/" + pdfUpload.getBookId() + "/add-failed");

            try {
                commonsMultipartFile.transferTo(file);
                return new ModelAndView("redirect:/quan-tri/pdf-cua-sach/" + pdfUpload.getBookId() + "/add-success");
            } catch (IllegalStateException | IOException e) {
                return new ModelAndView("redirect:/quan-tri/pdf-cua-sach/" + pdfUpload.getBookId() + "/add-failed");
            }
        }

        return new ModelAndView("redirect:/quan-tri/pdf-cua-sach/" + pdfUpload.getBookId() + "/add-failed");
    }

    @RequestMapping(value = "/xoa-pdf-cua-sach", method = RequestMethod.POST)
    public ModelAndView deletePdf(HttpSession httpSession, @RequestParam(value = "id") int id) {
        if (LoginServiceImpl.login(httpSession) != null)
            return LoginServiceImpl.login(httpSession);

        if (id <= 0)
            return new ModelAndView("redirect:/tai-khoan/dang-nhap");

        BookDto book = bookService.getById(id);
        if (book == null)
            return new ModelAndView("redirect:/quan-tri/danh-sach-sach");

        if (book.getPdf() == null)
            return new ModelAndView("redirect:/quan-tri/pdf-cua-sach/" + id + "/delete-failed");

        if (bookService.deletePdf(id)) {
            String dir = httpSession.getServletContext().getRealPath("/file-upload/images");
            File file = new File(dir + File.separator + book.getPdf());
            if (file.exists())
                file.delete();
            return new ModelAndView("redirect:/quan-tri/pdf-cua-sach/" + id + "/delete-success");
        }

        return new ModelAndView("redirect:/quan-tri/pdf-cua-sach/" + id + "/delete-failed");
    }

    @RequestMapping(value = {"/tac-gia-cua-sach/{id}", "/tac-gia-cua-sach/{id}/{message}"}, method = RequestMethod.GET)
    public ModelAndView EditBookAuthor(HttpSession httpSession, @PathVariable int id, @PathVariable(required = false) String message) {
        if (LoginServiceImpl.login(httpSession) != null)
            return LoginServiceImpl.login(httpSession);

        if (id <= 0)
            return new ModelAndView("redirect:/quan-tri/danh-sach-sach");

        BookDto bookInfo = bookService.getById(id);
        if (bookInfo == null)
            return new ModelAndView("redirect:/quan-tri/danh-sach-sach");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/edit-book-author");
        String username = loginState.replace("logged:true;username:", "").replace(";role:Admin", "");
        modelAndView.addObject("username", username);
        modelAndView.addObject("book", bookInfo);
        modelAndView.addObject("authorContribute", new BookAuthorDto());
        modelAndView.addObject("author", authorService.getAll());
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

    @RequestMapping(value = "/them-tac-gia-cua-sach", method = RequestMethod.POST)
    public ModelAndView addBookAuthor(HttpSession httpSession, @ModelAttribute("authorContribute") BookAuthorDto bookAuthorDto) {
        if (LoginServiceImpl.login(httpSession) != null)
            return LoginServiceImpl.login(httpSession);

        BookDto bookDto = bookAuthorService.addBookAuthor(bookAuthorDto);
        if (bookDto != null)
            return new ModelAndView("redirect:/quan-tri/tac-gia-cua-sach/" + bookDto.getId() + "/add-success");

        return new ModelAndView("redirect:/quan-tri/tac-gia-cua-sach/" + bookAuthorDto.getBookId() + "/add-failed");
    }

    @RequestMapping(value = "/xoa-tac-gia-cua-sach", method = RequestMethod.POST)
    public ModelAndView deleteAuthor(HttpSession httpSession, @RequestParam(value = "id") Integer id) {
        Object obj = httpSession.getAttribute("loginState");
        if (obj == null)
            return new ModelAndView("redirect:/tai-khoan/dang-nhap");

        String loginState = obj.toString();
        if (!loginState.matches("logged:true;username:([a-zA-Z0-9]{1,});role:Admin"))
            return new ModelAndView("redirect:/tai-khoan/dang-nhap");

        if (id <= 0)
            return new ModelAndView("redirect:/quan-tri/danh-sach-sach");

        AuthorDto dto = authorService.getById(id);
        BookDto book = bookAuthorService.getBookByAuthor(dto);
        if (bookAuthorService.deleteByAuthorId(id))
            return new ModelAndView("redirect:/quan-tri/tac-gia-cua-sach/" + book.getId() + "/delete-success");

        return new ModelAndView("redirect:/quan-tri/tac-gia-cua-sach/" + book.getId() + "/delete-failed");
    }
}
