package com.jacaranda.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jacaranda.security.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
