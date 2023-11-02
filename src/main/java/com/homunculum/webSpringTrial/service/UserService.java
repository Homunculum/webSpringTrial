package com.homunculum.webSpringTrial.service;

import com.homunculum.webSpringTrial.module.User;
import com.homunculum.webSpringTrial.repository.RoleRepository;
import com.homunculum.webSpringTrial.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public User findByUsername (String username){
        return userRepository.findByUsername(username);
    }
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public void saveUser(User user) {
        user.setRoles(Arrays.asList(roleRepository.findByRole("USER")));
        user.setEnebled(true);
        userRepository.save(user);
    }
}
