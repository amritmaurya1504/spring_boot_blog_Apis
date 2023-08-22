package com.rajamrit.SpringBoot_Blog_app.payloads;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class JwtAuthResponse {
    private String token;
}
