package com.example.springmvcdemo.dev.dto;

import com.example.springmvcdemo.common.Constants;
import com.example.springmvcdemo.dev.model.Author;
import com.example.springmvcdemo.dev.model.BookAuthor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AuthorDto extends BaseObjectDto {
    private String name;
    private String description;
    private String email;
    private String address;
    private String roleName;
    private List<BookAuthorDto> bookAuthors;

    public AuthorDto(Author entity) {
        this(entity, true);
    }

    public AuthorDto(Author entity, boolean simple) {
        if(entity != null) {
            this.id = entity.getId();
            this.name = entity.getName();
            this.description = entity.getDescription();
            this.email = entity.getEmail();
            this.address = entity.getAddress();

            if(simple) {
                if(entity.getListBook() != null && entity.getListBook().size() >0) {
                    this.bookAuthors = new ArrayList<BookAuthorDto>();
                    for(BookAuthor bookAuthor : entity.getListBook()) {
                        this.bookAuthors.add(new BookAuthorDto(bookAuthor));
                    }
                }
            }
        }
    }

    public static Author toEntity(AuthorDto dto) {
        return Constants.map().convertValue(dto, Author.class);
    }

    public static AuthorDto of(Author entity) {
        return Constants.map().convertValue(entity, AuthorDto.class);
    }

}
