package com.jacaranda.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jacaranda.security.exception.BookException;
import com.jacaranda.security.model.Book;
import com.jacaranda.security.service.BookService;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    // Listar todos los libros
    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("bookList", bookService.getAllBooks());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "listBooks";
    }

    // Mostrar formulario de creación
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("action", "add");
        model.addAttribute("book", new Book());
        return "createBook";
    }

    // Procesar formulario de creación
    @PostMapping("/create")
    public String createBook(@ModelAttribute("book") Book book, Model model) {
        try {
            bookService.createBook(book);
            return "redirect:/books";
        } catch (BookException e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    // Mostrar formulario de edición
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Book book = bookService.getBookById(id);
        if (book == null) {
            return "error";
        }
        model.addAttribute("book", book);
        model.addAttribute("action", "edit");
        return "createBook";
    }

    // Procesar formulario de edición
    @PostMapping("/edit/{id}")
    public String updateBook(@PathVariable("id") Long id, @ModelAttribute("book") Book updatedBook, Model model) {
        try {
            bookService.updateBook(id, updatedBook);
            return "redirect:/books";
        } catch (BookException e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    // Eliminar libro
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        try {
            bookService.deleteBook(id);
        } catch (BookException e) {
            // Podrías mostrar una alerta en el futuro
        }
        return "redirect:/books";
    }
}
