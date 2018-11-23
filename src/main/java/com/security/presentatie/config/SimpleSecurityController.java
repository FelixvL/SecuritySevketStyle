package com.security.presentatie.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;

@Controller
public class SimpleSecurityController {

    private final InMemoryUserDetailsManager inMemoryUserDetailsManager;

    @Autowired
    public SimpleSecurityController(InMemoryUserDetailsManager inMemoryUserDetailsManager) {
        this.inMemoryUserDetailsManager = inMemoryUserDetailsManager;
    }

    public boolean userExists(String username) {
        return inMemoryUserDetailsManager.userExists(username);
    }

    public void add(String username, String password, String role) {
        inMemoryUserDetailsManager
                .createUser(
                        User.withUsername(username)
                                .password(password)
                                .roles(role)
                                .build());
        System.out.println("Add user is working for - " + username + "- password : " + password);
    }

    public void remove(String username) {
        inMemoryUserDetailsManager
                .deleteUser(username);
        System.out.println("Deleted user is working for - " + username);
    }
}