package com.example.springmvcdemo.dev.service.Impl;

import com.example.springmvcdemo.dev.dto.BookAuthorDto;
import com.example.springmvcdemo.dev.dto.BookDto;
import com.example.springmvcdemo.dev.dto.RatingDto;
import com.example.springmvcdemo.dev.model.*;
import com.example.springmvcdemo.dev.repository.*;
import com.example.springmvcdemo.dev.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private PublishingHouseRepository publishingRepository;
    @Autowired
    private BookAuthorRepository bookAuthorRepository;

    @Override
    public List<BookDto> getAll() {
        List<Book> books = bookRepository.findAll();
        List<BookDto> dtos = new ArrayList<>();
        for (Book book : books) {
            if (book.getImg() == null)
                book.setImg("book-default.png");

            if (book.getImg().trim().length() == 0)
                book.setImg("book-default.png");
            dtos.add(new BookDto(book, true));
        }
        return dtos;
    }

    @Override
    public BookDto saveOrUpdate(BookDto dto, Integer id) {
        if (dto != null) {
            Book entity = null;
            if (id != null)
                entity = bookRepository.findById(id).orElse(null);
            if (entity == null)
                entity = new Book();

            entity.setBookName(dto.getName());
            entity.setDescription(dto.getDescription());
            entity.setViews(dto.getViews());
            entity.setUpvote(dto.getUpvote());
            entity.setDownvote(dto.getDownvote());
            entity.setImg(dto.getImg());
            entity.setPdf(dto.getPdf());
            if (entity.getImg() == null)
                entity.setImg("book-default.png");

            if (entity.getImg().trim().length() == 0)
                entity.setImg("book-default.png");
            Category category = null;
            if (dto.getCategory() != null && dto.getCategory().getId() != null) {
                category = categoryRepository.getById(dto.getCategory().getId());
            }
            entity.setCategory(category);

            PublishingHouse publishingHouse = null;
            if (dto.getCategory() != null && dto.getCategory().getId() != null) {
                publishingHouse = publishingRepository.getById(dto.getPublishingHouse().getId());
            }
            entity.setPublishingHouse(publishingHouse);

            List<BookAuthor> bookAuthors = new ArrayList<>();
            if (dto.getBookAuthorDtos() != null && dto.getBookAuthorDtos().size() > 0) {
                for (BookAuthorDto item : dto.getBookAuthorDtos()) {
                    BookAuthor bookAuthor = null;
                    if (item.getId() != null) {
                        bookAuthor = bookAuthorRepository.getById(item.getId());
                    }
                    if (bookAuthor == null) {
                        bookAuthor = new BookAuthor();
                    }
                    if (item.getAuthor() != null && item.getAuthor().getId() != null) {
                        Author author = authorRepository.getById(item.getAuthor().getId());
                        if (author != null) {
                            bookAuthor.setAuthor(author);
                            bookAuthor.setBook(entity);

                            bookAuthors.add(bookAuthor);
                        }
                    }

                }
            }
            if (bookAuthors != null && bookAuthors.size() > 0) {
                if (entity.getBookAuthors() == null) {
                    entity.setBookAuthors(bookAuthors);
                } else {
                    entity.getBookAuthors().clear();
                    entity.getBookAuthors().addAll(bookAuthors);
                }
            }

            entity = bookRepository.save(entity);
            return new BookDto(entity, true);
        }
        return null;
    }

    @Override
    public BookDto getById(Integer bookId) {
        if (bookId != null) {
            Book entity = bookRepository.getById(bookId);
            if (entity != null)
                return new BookDto(entity, true);
        }
        return null;
    }

    @Override
    public boolean delete(Integer bookId) {
        if (bookId != null) {
            bookRepository.deleteById(bookId);
            return true;
        }
        return false;
    }

    @Override
    public List<BookDto> getLastestBooks(int count) {
        List<Book> books = new ArrayList<>();
        List<Book> getAllBooks = bookRepository.getLastestBooks();
        int size = 0;
        if (getAllBooks.size() < count) size = getAllBooks.size();
        for (int i = 0; i < count; i++) {
            if (i == size) break;
            books.add(getAllBooks.get(i));
        }
        List<BookDto> bookDtos = new ArrayList<>();
        for (Book book : books) {
            if (book.getImg() == null)
                book.setImg("book-default.png");

            if (book.getImg().trim().length() == 0)
                book.setImg("book-default.png");

            RatingDto ratingDto = new RatingDto(book.getUpvote(), book.getDownvote());
            bookDtos.add(new BookDto(book, ratingDto, true));
        }

        return bookDtos;
    }

    @Override
    public List<BookDto> getBookByViews(int count) {
        List<Book> books = new ArrayList<>();
        List<Book> getAllBooks = bookRepository.getBooksByViews();
        int size = 0;
        if (getAllBooks.size() < count) size = getAllBooks.size();
        for (int i = 0; i < count; i++) {
            if (i == size) break;
            books.add(getAllBooks.get(i));
        }
        List<BookDto> bookDtos = new ArrayList<>();
        for (Book book : books) {
            if (book.getImg() == null)
                book.setImg("book-default.png");

            if (book.getImg().trim().length() == 0)
                book.setImg("book-default.png");

            RatingDto ratingDto = new RatingDto(book.getUpvote(), book.getDownvote());
            bookDtos.add(new BookDto(book, ratingDto, true));
        }

        return bookDtos;
    }

    @Override
    public List<BookDto> getBookByCategory(int id) {
        List<Book> books = bookRepository.getBooksByCategory(id);
        List<BookDto> bookDtos = new ArrayList<>();
        for (Book book : books) {
            if (book.getImg() == null)
                book.setImg("book-default.png");

            if (book.getImg().trim().length() == 0)
                book.setImg("book-default.png");

            RatingDto ratingDto = new RatingDto(book.getUpvote(), book.getDownvote());
            bookDtos.add(new BookDto(book, ratingDto, true));
        }

        return bookDtos;
    }

    @Override
    public List<BookDto> GetBooksByKeyword(String keyword) {
        List<Book> books = bookRepository.getBooksByKeyword(keyword);
        List<BookDto> bookDtos = new ArrayList<>();
        for (Book book : books) {
            if (book.getImg() == null)
                book.setImg("book-default.png");

            if (book.getImg().trim().length() == 0)
                book.setImg("book-default.png");

            RatingDto ratingDto = new RatingDto(book.getUpvote(), book.getDownvote());
            bookDtos.add(new BookDto(book, ratingDto, true));
        }

        return bookDtos;
    }

    @Override
    public boolean addImage(int id, String filepath) {
        Book entity = bookRepository.getById(id);
        if (entity != null) {
            entity.setImg(filepath);
            bookRepository.save(entity);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteImage(int id) {
        Book entity = bookRepository.getById(id);
        if (entity != null) {
            if (entity.getImg() == null)
                return false;
            if (entity.getImg().trim().length() == 0)
                return false;

            entity.setImg(null);
            bookRepository.save(entity);
            return true;
        }
        return false;
    }

    @Override
    public boolean addPdf(int id, String filepath) {
        Book entity = bookRepository.getById(id);
        if (entity != null) {
            entity.setPdf(filepath);
            bookRepository.save(entity);
            return true;
        }
        return false;
    }

    @Override
    public boolean deletePdf(int id) {
        Book entity = bookRepository.getById(id);
        if (entity != null) {
            if (entity.getPdf() == null)
                return false;
            if (entity.getPdf().trim().length() == 0)
                return false;

            entity.setImg(null);
            bookRepository.save(entity);
            return true;
        }
        return false;
    }

    @Override
    public boolean IncreaseView(int id) {
        Book entity = bookRepository.getById(id);
        if (entity != null) {
            entity.setViews(entity.getViews() + 1);
            bookRepository.save(entity);
            return true;
        }
        return false;
    }

    @Override
    public boolean Upvote(int id) {
        Book entity = bookRepository.getById(id);
        if (entity != null) {
            entity.setUpvote(entity.getUpvote() + 1);
            bookRepository.save(entity);
            return true;
        }
        return false;
    }

    @Override
    public boolean Downvote(int id) {
        Book entity = bookRepository.getById(id);
        if (entity != null) {
            entity.setDownvote(entity.getDownvote() + 1);
            bookRepository.save(entity);
            return true;
        }
        return false;
    }

    @Override
    public int countBook() {
        return bookRepository.countBooks();
    }
}
