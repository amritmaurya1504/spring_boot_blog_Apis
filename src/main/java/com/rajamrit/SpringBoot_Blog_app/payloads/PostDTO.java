package com.rajamrit.SpringBoot_Blog_app.payloads;

import com.rajamrit.SpringBoot_Blog_app.entities.Category;
import com.rajamrit.SpringBoot_Blog_app.entities.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class PostDTO {

    private String postTitle;

    private String postContent;

    private String postImageName;

    private Date postDate;

    private CategoryDTO category;

    private UserDTO user;

}
