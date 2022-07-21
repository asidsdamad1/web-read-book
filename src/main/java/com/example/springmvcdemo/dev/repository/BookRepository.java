package com.example.springmvcdemo.dev.repository;

import com.example.springmvcdemo.dev.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("select e from Book e where e.bookId = ?1")
    Book getById(Long bookId);

    @Query(value = "select * from Book order by Id desc limit 0,:count", nativeQuery = true)
    List<Book> getLastestBooks(int count);

    @Query(value = "select * from Book order by views  desc limit 0,:count", nativeQuery = true)
    List<Book> getBooksByViews(int count);

    @Query("select e from Book e where e.category.categoryId = ?1")
    List<Book> getBooksByCategory(long categoryId);

    @Query("select e from Book e where e.bookName like ?1")
    List<Book> getBooksByKeyword(String key);

    @Query("select count(e) from Book e")
    int countBooks();
}
