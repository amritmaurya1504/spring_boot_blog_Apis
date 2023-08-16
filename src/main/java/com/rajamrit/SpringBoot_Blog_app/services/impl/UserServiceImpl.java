package com.rajamrit.SpringBoot_Blog_app.services.impl;

import com.rajamrit.SpringBoot_Blog_app.entities.User;
import com.rajamrit.SpringBoot_Blog_app.exceptions.ResourceNotFoundException;
import com.rajamrit.SpringBoot_Blog_app.payloads.UserDTO;
import com.rajamrit.SpringBoot_Blog_app.repositories.UserRepo;
import com.rajamrit.SpringBoot_Blog_app.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

// CRUD Operation

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = this.dtoToUser(userDTO);
        User savedUser = this.userRepo.save(user);
        return this.userToDto(savedUser);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setAbout(userDTO.getAbout());
        user.setPassword(userDTO.getPassword());

        User updatedUser = this.userRepo.save(user);
        UserDTO userDTO1 = this.userToDto(updatedUser);
        return userDTO1;
    }

    @Override
    public UserDTO getUserById(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        return this.userToDto(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = this.userRepo.findAll();
        List<UserDTO> userDTO = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
        return userDTO;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        this.userRepo.delete(user);
    }

    private User dtoToUser(UserDTO userDTO){
        User user = this.modelMapper.map(userDTO, User.class);

        // Manual wrapping
        //        user.set_id(userDTO.get_id());
        //        user.setName(userDTO.getName());
        //        user.setEmail(userDTO.getEmail());
        //        user.setPassword(userDTO.getPassword());
        //        user.setAbout(userDTO.getAbout());

        return user;
    }

    public UserDTO userToDto(User user){
        UserDTO userDTO = this.modelMapper.map(user, UserDTO.class);
        return userDTO;
    }


}
