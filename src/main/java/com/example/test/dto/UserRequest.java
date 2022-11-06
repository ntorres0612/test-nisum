package com.example.test.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class UserRequest {

    @JsonProperty("id")
    private int id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

    @JsonProperty("token")
    private String token;

    @JsonProperty("is_active")
    private Boolean is_active;

    @JsonProperty("phones")
    private List<PhoneRequest> phones;
}
