package com.rajamrit.SpringBoot_Blog_app.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Table(name = "categories")
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;

    @Column(name="title", length = 100, nullable = false)
    private String categoryTitle;

    @Column(name="description")
    private String categoryDesc;

}
