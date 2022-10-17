package com.example.springmvcdemo.dev.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Book")
public class Book extends BaseObject{
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "views")
    private long views;
    @Column(name = "upvote")
    private long upvote;
    @Column(name = "downvote")
    private long downvote;
    @Column(name = "pdf")
    private String pdf;
    @Column(name = "img")
    private String img;

    @ManyToOne
    @JoinColumn(name = "publishingHouseId")
    private PublishingHouse publishingHouse;

    @OneToMany(mappedBy = "book",fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @Fetch(FetchMode.SUBSELECT)
    private List<BookFeatured> bookFeatureds;
}
