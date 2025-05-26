package com.jacaranda.security.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jacaranda.security.exception.UserException;
import com.jacaranda.security.model.User;
import com.jacaranda.security.repository.UserRepository;

@Service
public class UserService {

	@Autowired
    private  UserRepository userRepository;

  
    // Crear un nuevo usuario
    public User createUser(User user) throws UserException {
    	if (user.getId()!= null && getUserById(user.getId())== null) {
    		throw new UserException("Usuario ya existente");	
    	}
        return userRepository.save(user);
    }

    // Obtener todos los usuarios
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Obtener un usuario por ID
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    // Actualizar usuario existente
    public User updateUser(Long id, User updatedUser) throws UserException {
    	
    	User user = this.getUserById(id);
    	if (user == null) {
    		throw new UserException("Usuario no encontrado");	
    	}
    	
    	user.setUsername(updatedUser.getUsername());
        user.setEmail(updatedUser.getEmail());
        user.setPassword(updatedUser.getPassword());
        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(updatedUser.getLastName());
        
        return userRepository.save(user);
    }

    // Eliminar usuario por ID
    public void deleteUser(Long id) throws UserException {
    	if (getUserById(id)== null) {
    		throw new UserException("Usuario no encontrado");	
    	}
        userRepository.deleteById(id);
    }
}