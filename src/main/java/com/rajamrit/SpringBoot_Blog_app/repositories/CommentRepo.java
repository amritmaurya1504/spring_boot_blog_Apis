package com.rajamrit.SpringBoot_Blog_app.repositories;

import com.rajamrit.SpringBoot_Blog_app.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment, Integer> {
}
