package com.rajamrit.SpringBoot_Blog_app.services;

import com.rajamrit.SpringBoot_Blog_app.payloads.CommentDTO;

public interface CommentService {

    CommentDTO addComment(CommentDTO commentDTO, int userId, Integer postId);

    void deleteComment(Integer commentId);

}
