package com.example.test.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class UserResponse {

    @JsonProperty("name")
    private String name;

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

    @JsonProperty("created")
    private Date created;

    @JsonProperty("modified")
    private Date modified;

    @JsonProperty("last_login")
    private Date last_login;

    @JsonProperty("token")
    private String token;

    @JsonProperty("is_active")
    private Boolean is_active;

    @JsonProperty("phones")
    private List<PhoneResponse> phones;
}
