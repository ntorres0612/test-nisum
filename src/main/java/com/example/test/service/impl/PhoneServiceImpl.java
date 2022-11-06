package com.example.test.service.impl;

import com.example.test.model.Phone;
import com.example.test.repository.IPhoneRepository;
import com.example.test.service.IPhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneServiceImpl implements IPhoneService {

	@Autowired
	private IPhoneRepository phoneRepository;
	
	@Override
	public List<Phone> findAll() {
		return phoneRepository.findAll();
	}

	@Override
	public Phone findById(int id) {
		return phoneRepository.findById(id).get();
	}

	@Override
	public Phone saveOrUpdate(Phone phone) {
		return phoneRepository.save(phone);
	}

	@Override
	public void deleteById(int id) {
		phoneRepository.deleteById(id);
	}

}
