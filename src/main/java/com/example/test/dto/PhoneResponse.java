package com.example.test.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhoneResponse {

    @JsonProperty("number")
    private long number;

    @JsonProperty("citycode")
    private long citycode;

    @JsonProperty("contrycode")
    private long contrycode;


    @JsonProperty("user_id")
    private int user_id;
}
