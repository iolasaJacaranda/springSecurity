package com.jacaranda.security.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario_libro")
@IdClass(UserBookId.class)

public class UserBook {

	  @Id
	  @ManyToOne
	  @JoinColumn(name = "usuario_id")
	  private User user;

	  @Id
	  @ManyToOne
	  @JoinColumn(name = "libro_id")
	  private Book book;

	  @Column(name="fecha_adquisicion")
	  private LocalDate acquisitionDate;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public LocalDate getAcquisitionDate() {
		return acquisitionDate;
	}

	public void setAcquisitionDate(LocalDate acquisitionDate) {
		this.acquisitionDate = acquisitionDate;
	}

    
}

