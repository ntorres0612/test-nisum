package com.example.test.model;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Entity(name = "users")
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String name;
	private String email;
	private String password;

	@JsonIgnore
	private Date created;

	@JsonIgnore
	private Date modified;

	@JsonIgnore
	private Date last_login;
	private String token;
	private Boolean is_active;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.MERGE, orphanRemoval = false, mappedBy = "user")
	private List<Phone> phones;
}