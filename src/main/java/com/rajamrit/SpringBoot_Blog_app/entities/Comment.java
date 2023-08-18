package com.rajamrit.SpringBoot_Blog_app.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="comments")
@NoArgsConstructor
@Getter
@Setter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PRIMARY KEY
    private Integer commentId;

    @Column(nullable = false, length = 500)
    private String comment;

    // Manage Relation
    @ManyToOne
    @JoinColumn(name = "post_Id")
    private Post post;

    @ManyToOne
    private User user;
}
