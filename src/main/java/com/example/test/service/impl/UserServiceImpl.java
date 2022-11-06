package com.example.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.model.User;
import com.example.test.repository.IUserRepository;
import com.example.test.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserRepository userRepository;
	
	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User findById(int idClient) {
		return userRepository.findById(idClient).get();
	}

	@Override
	public User save(User client) {
		return userRepository.save(client);
	}

	@Override
	public void deleteById(int idClient) {
		userRepository.deleteById(idClient);
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

}
