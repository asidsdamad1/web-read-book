package com.example.springmvcdemo.dev.service.Impl;

import java.util.ArrayList;
import java.util.List;

import com.example.springmvcdemo.dev.dto.BookDto;
import com.example.springmvcdemo.dev.dto.CategoryDto;
import com.example.springmvcdemo.dev.model.Book;
import com.example.springmvcdemo.dev.model.Category;
import com.example.springmvcdemo.dev.repository.BookRepository;
import com.example.springmvcdemo.dev.repository.CategoryRepository;
import com.example.springmvcdemo.dev.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository catRepo;
    @Autowired
    private BookRepository bookRepo;


    @Override
    public CategoryDto saveOrUpdate(CategoryDto dto, Long id) {
        if(dto != null) {
            Category entity = null;

            if(id != null) {
                entity = catRepo.getById(id);
            }
            if(entity == null) {
                entity = new Category();
            }



            List<Book> setBookList = new ArrayList<>();
            if(dto.getListBook() != null && dto.getListBook().size() > 0) {
                for(BookDto itemDto: dto.getListBook()) {
                    Book book = null;
                    if(itemDto.getId() != null) {
                        book = bookRepo.getById(itemDto.getId());
                    } else {
                        book = new Book();
                    }

                    setBookList.add(book);
                }
            }
            if(entity.getListBook() == null) {
                entity.setListBook(setBookList);
            } else {

                entity.getListBook().addAll(setBookList);
            }

            entity = catRepo.save(entity);

            if(entity != null ) {
                return new CategoryDto(entity, true);
            }
        }
        return null;
    }

    @Override
    public List<Category> getAll() {
        return (List<Category>) catRepo.findAll();
    }

    @Override
    public Category getById(long catalogId) {
        return catRepo.findById(catalogId).get();
    }

    @Override
    public boolean delete(long catalogId) {
        try {
            catRepo.deleteById(catalogId);
            return true;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return false;
    }
}
