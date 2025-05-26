package com.jacaranda.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jacaranda.security.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findByUsername(String username);

}
