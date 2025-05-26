package com.jacaranda.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jacaranda.security.exception.BookException;
import com.jacaranda.security.model.Book;
import com.jacaranda.security.repository.BookRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    // Crear un nuevo libro
    public Book createBook(Book book) throws BookException {
        if (book.getId()!= null && getBookById(book.getId()) != null) {
            throw new BookException("Libro ya existe");
        }
        return bookRepository.save(book);
    }

    // Obtener todos los libros
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Obtener un libro por ID
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    // Actualizar libro existente
    public Book updateBook(Long id, Book updatedBook) throws BookException {
        Book book = this.getBookById(id);
        if (book == null) {
            throw new BookException("Libro no encontrado");
        }

        book.setTitle(updatedBook.getTitle());
        book.setAuthor(updatedBook.getAuthor());
        book.setIsbn(updatedBook.getIsbn());
        book.setPublicationDate(updatedBook.getPublicationDate());
        book.setGenre(updatedBook.getGenre());

        return bookRepository.save(book);
    }

    // Eliminar libro por ID
    public void deleteBook(Long id) throws BookException {
        if (getBookById(id) == null) {
            throw new BookException("Libro no encontrado");
        }
        bookRepository.deleteById(id);
    }
}
