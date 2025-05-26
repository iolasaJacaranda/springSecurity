package com.jacaranda.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jacaranda.security.exception.UserException;
import com.jacaranda.security.model.User;
import com.jacaranda.security.repository.UserRepository;

@Service
public class UserService implements UserDetailsService{

	@Autowired
    private  UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
  
    // Crear un nuevo usuario
    public User createUser(User user) throws UserException {
    	if (user.getId()!= null && getUserById(user.getId())== null) {
    		throw new UserException("Usuario ya existente");	
    	}
    	user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRole("user");
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
        user.setRole(updatedUser.getRole());
        
        return userRepository.save(user);
    }

    // Eliminar usuario por ID
    public void deleteUser(Long id) throws UserException {
    	if (getUserById(id)== null) {
    		throw new UserException("Usuario no encontrado");	
    	}
        userRepository.deleteById(id);
    }

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		List<User> listUser = userRepository.findByUsername(username);
		if (listUser.size() == 0) {
			throw new UsernameNotFoundException("Usuario no encontrado");
		}else if (listUser.size() > 1) {
			throw new UsernameNotFoundException("Mas de un usuario con el username");

		}
		
		return listUser.get(0);
	}
}