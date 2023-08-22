package com.rajamrit.SpringBoot_Blog_app.Security;

import com.rajamrit.SpringBoot_Blog_app.entities.User;
import com.rajamrit.SpringBoot_Blog_app.exceptions.ResourceNotFoundException;
import com.rajamrit.SpringBoot_Blog_app.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    // loading user from database by username
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepo.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException("Username", "User with email " + username, 0));
    }
}
