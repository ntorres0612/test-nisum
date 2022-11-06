package com.example.test.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class ConfigRequest  implements Serializable {
    @JsonProperty("key")
    private String key;
    @JsonProperty("value")
    private String value;

}
