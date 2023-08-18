package com.rajamrit.SpringBoot_Blog_app.repositories;

import com.rajamrit.SpringBoot_Blog_app.entities.Category;
import com.rajamrit.SpringBoot_Blog_app.entities.Comment;
import com.rajamrit.SpringBoot_Blog_app.entities.Post;
import com.rajamrit.SpringBoot_Blog_app.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {
    List<Post> findByCategory(Category category);
    List<Post> findByUser(User user);
    List<Post> findByPostTitleContaining(String postTitle);

}
