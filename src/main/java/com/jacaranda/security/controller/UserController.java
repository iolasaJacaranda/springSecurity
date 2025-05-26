package com.jacaranda.security.controller;


import com.jacaranda.security.exception.UserException;
import com.jacaranda.security.model.User;
import com.jacaranda.security.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Listar todos los usuarios
    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("userList", userService.getAllUsers());
        return "listUsers";
    }

    // Mostrar formulario para crear un nuevo usuario
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("action", "add");
        return "createUser";
    }

    // Procesar formulario de creación
    @PostMapping("/create")
    public String createUser(@ModelAttribute("user") User user, Model model) {
        try {
            userService.createUser(user);
            return "redirect:/users";
        } catch (UserException e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    // Mostrar formulario para editar un usuario
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        User user = userService.getUserById(id);
        if (user == null) {
            return "redirect:/users";
        }
        model.addAttribute("action", "edit");

        model.addAttribute("user", user);
        return "createUser";
    }

    // Procesar formulario de edición
    @PostMapping("/edit/{id}")
    public String updateUser(@PathVariable("id") Long id, @ModelAttribute("user") User updatedUser, Model model) {
        try {
            userService.updateUser(id, updatedUser);
            return "redirect:/users";
        } catch (UserException e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    // Eliminar un usuario
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        try {
            userService.deleteUser(id);
        } catch (UserException e) {
            // Podrías redirigir a una página de error o mostrar un mensaje
        }
        return "redirect:/users";
    }
}
