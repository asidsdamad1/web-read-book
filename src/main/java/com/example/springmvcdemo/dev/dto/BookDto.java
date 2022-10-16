package com.example.springmvcdemo.dev.dto;

import com.example.springmvcdemo.common.Constants;
import com.example.springmvcdemo.dev.model.Book;
import com.example.springmvcdemo.dev.model.BookAuthor;
import com.example.springmvcdemo.dev.model.BookFeatured;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class BookDto extends BaseObjectDto {
    private String name;
    private String description;
    private PublishingHouseDto publishingHouse;
    private List<BookAuthorDto> bookAuthorDtos;
    private List<BookFeaturedDto> bookFeatureds;
    private Set<AuthorDto> authorDtos;
    private Set<Integer> categoryIds;
    private Set<Integer> authorIds;
    private long views;
    private long upvote;
    private long downvote;
    private int star;
    private String pdf;
    private String img;
    private CategoryDto category;
    private AuthorDto author;

    public BookDto(Book entity) {
        this(entity, true);
    }

    public BookDto(Book entity, boolean simple) {
        if (entity != null) {
            this.id = entity.getId();
            this.name = entity.getName();
            this.description = entity.getDescription();
            this.views = entity.getViews();
            this.upvote = entity.getUpvote();
            this.downvote = entity.getDownvote();
            this.pdf = entity.getPdf();
            this.img = entity.getImg();

            if (entity.getPublishingHouse() != null) {
                this.publishingHouse = new PublishingHouseDto(entity.getPublishingHouse(), false);
            }
            if (simple) {
                if (entity.getBookAuthors() != null && entity.getBookAuthors().size() > 0) {
                    this.bookAuthorDtos = new ArrayList<>();
                    for (BookAuthor item : entity.getBookAuthors()) {
                        this.bookAuthorDtos.add(new BookAuthorDto(item));
                    }
                }
                if (entity.getBookFeatureds() != null && entity.getBookFeatureds().size() > 0) {
                    this.bookFeatureds = new ArrayList<>();
                    this.bookFeatureds.add(new BookFeaturedDto(entity.getBookFeatureds().get(0)));
                    for (BookFeatured item : entity.getBookFeatureds()) {
                        if (!Objects.equals(item.getAuthor().getName(), this.bookFeatureds.get(0).getAuthor().getName())
                                && !Objects.equals(item.getCategory().getCategoryName(), this.bookFeatureds.get(0).getCategory().getName())) {
                            this.bookFeatureds.add(new BookFeaturedDto(item));
                        }
                    }
                }
            }
        }
    }

    public BookDto(Book entity, RatingDto ratingDto, boolean simple) {
        if (entity != null) {
            this.id = entity.getId();
            this.name = entity.getName();
            this.description = entity.getDescription();
            this.views = entity.getViews();
            this.upvote = entity.getUpvote();
            this.downvote = entity.getDownvote();
            this.pdf = entity.getPdf();
            this.img = entity.getImg();
            this.star = ratingDto.SolveStar();
            if (entity.getPublishingHouse() != null) {
                this.publishingHouse = new PublishingHouseDto(entity.getPublishingHouse(), true);
            }
            if (simple) {
                if (entity.getBookAuthors() != null && entity.getBookAuthors().size() > 0) {
                    this.bookAuthorDtos = new ArrayList<>();
                    for (BookAuthor item : entity.getBookAuthors()) {
                        this.bookAuthorDtos.add(new BookAuthorDto(item));
                    }
                }
            }
        }
    }

    public static Book toEntity(BookDto dto) {
        return Constants.map().convertValue(dto, Book.class);
    }

    public static BookDto of(Book entity) {
        return Constants.map().convertValue(entity, BookDto.class);
    }
}
