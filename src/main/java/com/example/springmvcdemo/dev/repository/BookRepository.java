package com.example.springmvcdemo.dev.repository;

import com.example.springmvcdemo.dev.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer> {
    @Query("select e from Book e where e.id = ?1")
    Book getById(int bookId);

    @Query("select distinct e from Book e where e.id = ?1")
    Optional<Book> findByIdDistinct(int bookId);

    @Query("select e from Book e order by e.id desc")
    List<Book> getLastestBooks();

    @Query("select e from Book e order by e.views desc ")
    List<Book> getBooksByViews();

    @Query("select distinct e.book from BookFeatured e where e.author.id = ?1 and e.book.id=?2")
    Book getBookByAuthorId(Integer id, Integer bookId);

    @Query("select distinct e.book from BookFeatured e where e.category.id = ?1 and e.book.id = ?2")
    Book getBookByCategoryId(Integer id, Integer bookId);
//    @Query("select e from Book e where e.category.id = ?1")
//    List<Book> getBooksByCategory(int categoryId);

    @Query("select e from Book e where e.name like ?1")
    List<Book> getBooksByKeyword(String key);

    @Query("select count(e) from Book e")
    int countBooks();
}
