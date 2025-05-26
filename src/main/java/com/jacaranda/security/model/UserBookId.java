package com.jacaranda.security.model;

import java.io.Serializable;
import java.util.Objects;

public class UserBookId implements Serializable {

    private Long user;
    private Long book;
	public Long getUser() {
		return user;
	}
	public void setUser(Long user) {
		this.user = user;
	}
	public Long getBook() {
		return book;
	}
	public void setBook(Long book) {
		this.book = book;
	}
	@Override
	public int hashCode() {
		return Objects.hash(book, user);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserBookId other = (UserBookId) obj;
		return Objects.equals(book, other.book) && Objects.equals(user, other.user);
	}
    
    

}
