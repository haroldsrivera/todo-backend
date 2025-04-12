package com.bacobogota.todo.service;

import com.bacobogota.todo.model.User;
import com.bacobogota.todo.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final IUserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    // Función para verificar si el usuario existe y si la contraseña coincide
    public boolean authenticate(String email, String rawPassword) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        return userOpt.isPresent() &&
                passwordEncoder.matches(rawPassword, userOpt.get().getPassword());
    }

}
