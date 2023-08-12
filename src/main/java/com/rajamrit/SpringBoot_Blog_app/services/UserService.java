package com.rajamrit.SpringBoot_Blog_app.services;

import com.rajamrit.SpringBoot_Blog_app.payloads.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO createUser(UserDTO user);
    UserDTO updateUser(UserDTO user, Integer userId);
    UserDTO getUserById(Integer userId);
    List<UserDTO>  getAllUsers();
    void deleteUser(Integer userId);

}
