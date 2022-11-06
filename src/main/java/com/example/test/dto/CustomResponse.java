package com.example.test.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class CustomResponse  implements Serializable {
    @JsonProperty("status")
    private String status;
    @JsonProperty("message")
    private String message;

}
