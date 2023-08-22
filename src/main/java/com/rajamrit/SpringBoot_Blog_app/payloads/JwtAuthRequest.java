package com.rajamrit.SpringBoot_Blog_app.payloads;


import lombok.Data;

@Data
public class JwtAuthRequest {

    private String username;
    private String password;

}
