package com.example.springmvcdemo.dev.dto;

import com.example.springmvcdemo.common.Constants;
import com.example.springmvcdemo.dev.model.Book;
import com.example.springmvcdemo.dev.model.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto extends BaseObjectDto {
    private String name;
    private String description;
    private List<BookDto> listBook;

    public CategoryDto(Category entity, boolean simple) {
        if (entity != null) {
            this.id = entity.getId();
            this.name = entity.getCategoryName();
            this.description = entity.getDescription();


        }
    }

    public static Category toEntity(CategoryDto dto) {
        return Constants.map().convertValue(dto, Category.class);
    }
    public static CategoryDto of(Category entity) {
        return Constants.map().convertValue(entity, CategoryDto.class);
    }
}
