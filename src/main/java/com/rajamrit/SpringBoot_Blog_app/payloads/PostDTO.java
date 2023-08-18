package com.rajamrit.SpringBoot_Blog_app.payloads;

import com.rajamrit.SpringBoot_Blog_app.entities.Category;
import com.rajamrit.SpringBoot_Blog_app.entities.Comment;
import com.rajamrit.SpringBoot_Blog_app.entities.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class PostDTO {

    private Integer postId;

    @NotBlank
    @Size(min = 5)
    private String postTitle;

    @NotBlank
    @Size(min = 100)
    private String postContent;

    private String postImageName;

    private Date postDate;

    private CategoryDTO category;

    private UserDTO user;

    private Set<CommentDTO> comment = new HashSet<>();

}
