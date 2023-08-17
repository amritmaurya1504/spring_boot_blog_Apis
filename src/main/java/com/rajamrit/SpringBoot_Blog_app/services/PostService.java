package com.rajamrit.SpringBoot_Blog_app.services;

import com.rajamrit.SpringBoot_Blog_app.payloads.PostDTO;
import com.rajamrit.SpringBoot_Blog_app.payloads.PostResponse;

import java.util.List;

public interface PostService {

    // create
    PostDTO createPost(PostDTO postDTO, Integer userId, Integer categoryId);

    //update
    PostDTO updatePost(PostDTO postDTO, Integer postId);

    //delete
    void deletePost(Integer postId);

    // get all posts
    PostResponse getAllPost(Integer pageNumber, Integer pageSize);

    // get Single Post
    PostDTO getPostById(Integer postId);

    // get all posts by category
    List<PostDTO> getPostsByCategory(Integer categoryId);

    // get all posts by User
    List<PostDTO> getPostsByUser(Integer userId);

    // searchPost
    List<PostDTO> searchPosts(String keyword);

}
