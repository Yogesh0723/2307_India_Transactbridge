package com.example.transactbridge.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MyUserDetailsService implements UserDetailsService {

    // In-memory user store for demonstration purposes
    private static final Map<String, String> users = new HashMap<>();

    static {
        // Add a sample user (username: "user", password: "password")
        users.put("user", "password");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String password = users.get(username);
        if (password == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return org.springframework.security.core.userdetails.User
                .withUsername(username)
                .password(password)
                .authorities("USER") // Assign roles/authorities as needed
                .build();
    }
}