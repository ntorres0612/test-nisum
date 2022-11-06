package com.example.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.test.model.User;

import java.util.List;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {

   User findByEmail(String email);
}
