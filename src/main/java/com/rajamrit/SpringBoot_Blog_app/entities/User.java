package com.rajamrit.SpringBoot_Blog_app.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// if i don't write table here then by default it will create User named table
@Entity
@Table(name="users")
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int _id;

    @Column(name = "user_name", nullable = false, length = 100) // this line is used to create attribute name user_name in database
    private String name;

    @Column(name = "user_email", nullable = false, length = 100)
    private String email;

    @Column(name = "user_password", nullable = false, length = 100)
    private String password;

    @Column(name = "user_bio", nullable = false, length = 100)
    private String about;


}
