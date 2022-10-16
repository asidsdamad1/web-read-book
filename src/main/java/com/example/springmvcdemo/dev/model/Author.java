package com.example.springmvcdemo.dev.model;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Author")
public class Author extends BaseObject{

    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "email")
    private String email;
    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "author")
    private List<BookAuthor> listBook;

    @OneToMany(mappedBy = "author",fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.SUBSELECT)
    private Set<BookFeatured> bookFeatureds;
}
