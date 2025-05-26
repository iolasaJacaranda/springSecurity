package com.jacaranda.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jacaranda.security.model.UserBook;
import com.jacaranda.security.model.UserBookId;

public interface UserBookRepository extends JpaRepository<UserBook, UserBookId> {

}
