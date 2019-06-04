package com.mvuchevski.ilibrary.services;

import com.mvuchevski.ilibrary.exceptions.UsernameAlreadyExistsException;
import com.mvuchevski.ilibrary.models.User;
import com.mvuchevski.ilibrary.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public User saveUser(User newUser){
        try{

            newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
            newUser.setConfirmPassword("");

            return userRepository.save(newUser);

        }catch(Exception ex){
            throw new UsernameAlreadyExistsException("Username '" + newUser.getUsername() + "' already exists");
        }
    }
}