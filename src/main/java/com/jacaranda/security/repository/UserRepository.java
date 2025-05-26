package com.jacaranda.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jacaranda.security.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
