package com.example.springmvcdemo.dev.model;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PublishingHouse")
public class PublishingHouse extends BaseObject{
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "email")
    private String email;
    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "publishingHouse", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @Fetch(FetchMode.SUBSELECT)
    private List<Book> listBook;

}
