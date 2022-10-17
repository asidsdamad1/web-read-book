package com.example.springmvcdemo.dev.repository;

import com.example.springmvcdemo.dev.model.Author;
import com.example.springmvcdemo.dev.model.Book;
import com.example.springmvcdemo.dev.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query("select e from Category e where e.categoryName=?1")
    Category getByName(String name);

    @Query("select distinct e.category from BookFeatured e where e.book.id=?1")
    List<Category> findAllByBookId(Integer bookId);

    @Query("select count(e) from Category e")
    int countCategories();

    @Query("select e from Category e where e.id = ?1")
    Category getById(int id);
}
