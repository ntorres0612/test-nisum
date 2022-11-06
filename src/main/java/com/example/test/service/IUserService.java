package com.example.test.service;

import java.util.List;

import com.example.test.model.User;

public interface IUserService {
	 List<User> findAll();
	 User findById(int idClient);
	 User save(User client);
	 void deleteById(int idClient);
	 User findByEmail(String email);


}
