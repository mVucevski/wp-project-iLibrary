package com.mvuchevski.ilibrary.services;

import com.mvuchevski.ilibrary.exceptions.UsernameAlreadyExistsException;
import com.mvuchevski.ilibrary.models.User;
import com.mvuchevski.ilibrary.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if(user==null) throw new UsernameAlreadyExistsException("The user with username: '" + username + "' doesn't exist!");

        return user;
    }

    @Transactional
    public User loadUserById(Long id){
        User user = userRepository.getById(id);
        if(user==null) throw new UsernameAlreadyExistsException("The user with id: '" + id + "' doesn't exist!");

        return user;
    }
}
