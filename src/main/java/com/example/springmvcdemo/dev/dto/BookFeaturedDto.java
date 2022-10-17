package com.example.springmvcdemo.dev.dto;

import com.example.springmvcdemo.common.Constants;
import com.example.springmvcdemo.dev.model.BookAuthor;
import com.example.springmvcdemo.dev.model.BookFeatured;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookFeaturedDto extends BaseObjectDto {
    private int bookId;
    private int authorId;
    private int categoryId;
    private BookDto book;
    private AuthorDto author;
    private CategoryDto category;

    public static BookFeaturedDto of(BookFeatured entity) {
        return Constants.map().convertValue(entity, BookFeaturedDto.class);
    }

    public static BookFeatured toEntity(BookFeaturedDto dto) {
        return Constants.map().convertValue(dto, BookFeatured.class);
    }

    public BookFeaturedDto(BookFeatured entity) {
        if (entity != null) {
            if (entity.getBook() != null) {
                this.book = new BookDto(entity.getBook(), false);
            }
            if (entity.getAuthor() != null) {
                this.author = new AuthorDto(entity.getAuthor(), false);
            }
            if (entity.getCategory() != null) {
                this.category = new CategoryDto(entity.getCategory(), false);
            }
        }

    }
}
