package com.fst.projet_CarPooling_jee.Service.impl;

import com.fst.projet_CarPooling_jee.Entity.User;
import com.fst.projet_CarPooling_jee.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService  {
    @Autowired
    private UserRepository userRepository;


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public void saveUser(User user) {
        /*BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);*/  // Remplace le mot de passe en clair par le mot de passe haché
        this.userRepository.save(user);
    }

    // Méthode pour authentifier un utilisateur
    public User authenticate(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    // Méthode pour vérifier si un email existe déjà dans la base de données
    public boolean emailExists(String email) {
        return userRepository.findByEmail(email) != null;
    }


}
