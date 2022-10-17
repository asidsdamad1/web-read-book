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
    private AuthorDto author;
    private CategoryDto category;

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

                if (entity.getBookFeatureds() != null && entity.getBookFeatureds().size() > 0) {
                    this.bookFeatureds = new ArrayList<>();
                    for (BookFeatured item : entity.getBookFeatureds()) {
                        this.bookFeatureds.add(new BookFeaturedDto(item));
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
                if (entity.getBookFeatureds() != null && entity.getBookFeatureds().size() > 0) {
                    this.bookFeatureds = new ArrayList<>();
                    for (BookFeatured item : entity.getBookFeatureds()) {
                        this.bookFeatureds.add(new BookFeaturedDto(item));
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
