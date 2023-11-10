package com.dong.Springs.service;

import com.dong.Springs.entity.User;
import com.dong.Springs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user= userRepository.findByUsername(username);
        if (user != null) {
            return (UserDetails) user;
        }
        throw new UsernameNotFoundException("User nao encontrado");
    }

    public String registrar(User user) throws Exception {
        User userNameBanco = userRepository.findByUsername(user.getUsername());

        if (userNameBanco.getUsername().equals(user.getUsername())){
            User userNew = new User();
            userNew.setId(user.getId());
            userNew.setUsername(user.getUsername());
            userNew.setPassword(passwordEncoder.encode(user.getPassword()));
            userNew.setRole(user.getRole());

            userRepository.save(userNew);

            return "Salvo com sucesso";
        } else {
            throw new Exception("Já existe");
        }

    }
}
