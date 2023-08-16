package com.rajamrit.SpringBoot_Blog_app.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO {

    private int _id;

    @NotNull
    @Size(min = 4, message = "Username must be min of 4 characters !!")
    private String name;

    @Email (message = "Email address is not valid !!")
    private String email;

    @NotNull
    @Size (min = 3, max = 10, message = "Password must be min of  chars and max of 10 chars")
//    @Pattern()
    private String password;

    @NotNull
    private String about;

}
