package com.example.test.service;

import com.example.test.model.Phone;

import java.util.List;

public interface IPhoneService {
	 List<Phone> findAll();
	 Phone findById(int id);
	 Phone saveOrUpdate(Phone phone);
	 void deleteById(int id);
}
