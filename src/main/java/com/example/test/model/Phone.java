package com.example.test.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity(name = "phones")
@Data
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private long number;
    private long citycode;
    private long contrycode;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", nullable = true)
    private User user;
}
