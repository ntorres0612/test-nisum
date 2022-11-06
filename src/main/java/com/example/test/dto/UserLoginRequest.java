package com.example.test.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginRequest {

    @JsonProperty("email")
    private String email;
}
