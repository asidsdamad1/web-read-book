package com.example.springmvcdemo.dev.service.Impl;

import com.example.springmvcdemo.dev.dto.BookDto;
import com.example.springmvcdemo.dev.dto.RatingDto;
import com.example.springmvcdemo.dev.model.*;
import com.example.springmvcdemo.dev.repository.*;
import com.example.springmvcdemo.dev.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;
    private final PublishingHouseRepository publishingRepository;
    private final BookAuthorRepository bookAuthorRepository;
    private final BookFeaturedRepository bookFeaturedRepository;

    @Override
    public List<BookDto> getAll() {
        List<Book> books = bookRepository.findAll();
        List<BookDto> dtos = new ArrayList<>();
        for (Book book : books) {
            if (book.getImg() == null)
                book.setImg("book-default.png");

            if (book.getImg().trim().length() == 0)
                book.setImg("book-default.png");
            dtos.add(new BookDto(book, false));
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

            entity.setName(dto.getName());
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


            PublishingHouse publishingHouse = null;
            if (dto.getPublishingHouse() != null && dto.getPublishingHouse().getId() != null) {
                publishingHouse = publishingRepository.findById(dto.getPublishingHouse().getId()).get();
            }
            entity.setPublishingHouse(publishingHouse);

            List<BookFeatured> bookFeatures = new ArrayList<>();
            if (dto.getCategoryIds() != null && dto.getAuthorIds() != null) {


                // add category follow to author in book featured
                for (Integer i : dto.getAuthorIds()) {
                    Author author = authorRepository.findById(i).orElseThrow(null);

                    for (Integer j : dto.getCategoryIds()) {
                        BookFeatured bookFeatured = new BookFeatured();
                        Category category1 = categoryRepository.findById(j).orElseThrow(null);

                        bookFeatured.setAuthor(author);
                        bookFeatured.setCategory(category1);

                        bookFeatures.add(bookFeatured);
                    }
                }

            }
            if (bookFeatures != null && bookFeatures.size() > 0) {
                if (entity.getBookFeatureds() == null) {
                    entity.setBookFeatureds(bookFeatures);
                } else {
                    entity.getBookFeatureds().clear();
                    entity.getBookFeatureds().addAll(bookFeatures);
                }
            }

            entity = bookRepository.save(entity);
            for (BookFeatured bookFeatured : bookFeatures) {
                bookFeatured.setBook(entity);
            }

            bookFeaturedRepository.saveAll(bookFeatures);
            return new BookDto(entity);
        }
        return null;
    }


    @Override
    public BookDto getById(Integer bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(null);
        List<BookFeatured> bookFeatureds = new ArrayList<>();
        List<Author> authors = authorRepository.findAllByBookId(bookId);
        List<Category> categories = categoryRepository.findAllByBookId(bookId);
        for (Author author : authors) {
            BookFeatured featured = new BookFeatured();
            featured.setBook(book);
            featured.setAuthor(author);
            featured.setCategory(null);
            bookFeatureds.add(featured);
        }

        for (Category category : categories) {
            BookFeatured featured = new BookFeatured();
            featured.setBook(book);
            featured.setAuthor(null);
            featured.setCategory(category);
            bookFeatureds.add(featured);
        }

        book.setBookFeatureds(bookFeatureds);
        return new BookDto(book, true);
    }

    @Override
    public BookDto getByIdAndType(Integer bookId, int type) {
        Book book = bookRepository.findById(bookId).orElseThrow(null);
        List<Author> authors = authorRepository.findAllByBookId(bookId);
        List<Category> categories = categoryRepository.findAllByBookId(bookId);
        List<BookFeatured> bookFeatureds = new ArrayList<>();

        if (type == 1) {
            for (Author author : authors) {
                BookFeatured featured = new BookFeatured();
                featured.setBook(book);
                featured.setAuthor(author);
                featured.setCategory(null);
                bookFeatureds.add(featured);
            }
        }

        if (type == 2) {
            for (Category category : categories) {
                BookFeatured featured = new BookFeatured();
                featured.setBook(book);
                featured.setAuthor(null);
                featured.setCategory(category);
                bookFeatureds.add(featured);
            }
        }
        book.setBookFeatureds(bookFeatureds);
        return new BookDto(book, true);
    }

    @Override
    public boolean delete(Integer bookId) {
        if (bookId != null) {
            Book book = bookRepository.findById(bookId).orElse(null);
            if (book != null) {
                bookRepository.delete(book);
                return true;
            }
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
        List<BookFeatured> bookFeatureds = bookFeaturedRepository.getBooksByCategory(id);
        List<Book> books = new ArrayList<>();
        for (BookFeatured bookFeatured : bookFeatureds) {
            Book book = bookRepository.getById(bookFeatured.getId());
            books.add(book);
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

            entity.setPdf(null);
            bookRepository.save(entity);
            return true;
        }
        return false;
    }

    @Override
    public boolean increaseView(int id) {
        Book entity = bookRepository.getById(id);
        if (entity != null) {
            entity.setViews(entity.getViews() + 1);
            bookRepository.save(entity);
            return true;
        }
        return false;
    }

    @Override
    public boolean upvote(int id) {
        Book entity = bookRepository.getById(id);
        if (entity != null) {
            entity.setUpvote(entity.getUpvote() + 1);
            bookRepository.save(entity);
            return true;
        }
        return false;
    }

    @Override
    public boolean downvote(int id) {
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
