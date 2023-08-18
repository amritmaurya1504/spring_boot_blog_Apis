package com.rajamrit.SpringBoot_Blog_app.services.impl;

import com.rajamrit.SpringBoot_Blog_app.entities.Comment;
import com.rajamrit.SpringBoot_Blog_app.entities.Post;
import com.rajamrit.SpringBoot_Blog_app.entities.User;
import com.rajamrit.SpringBoot_Blog_app.exceptions.ResourceNotFoundException;
import com.rajamrit.SpringBoot_Blog_app.payloads.CommentDTO;
import com.rajamrit.SpringBoot_Blog_app.repositories.CommentRepo;
import com.rajamrit.SpringBoot_Blog_app.repositories.PostRepo;
import com.rajamrit.SpringBoot_Blog_app.repositories.UserRepo;
import com.rajamrit.SpringBoot_Blog_app.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDTO addComment(CommentDTO commentDTO, int userId, Integer postId) {
        User user = this.userRepo.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User", "userId", userId));
        Post post = this.postRepo.findById(postId).orElseThrow(() ->
                new ResourceNotFoundException("Post", "postId", postId));
        Comment comment = this.modelMapper.map(commentDTO, Comment.class);
        comment.setUser(user);
        comment.setPost(post);
        Comment addedComment = this.commentRepo.save(comment);
        return this.modelMapper.map(addedComment, CommentDTO.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment = this.commentRepo.findById(commentId).orElseThrow(() ->
                new ResourceNotFoundException("Comment", "Commentid", commentId));
        this.commentRepo.delete(comment);
    }
}
