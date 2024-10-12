package com.example.transactbridge.config;

import com.example.transactbridge.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class TokenConfig implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // Generate a token for a default user (for demonstration purposes)
        String token = jwtUtil.generateToken("defaultUser");
        System.out.println("Generated JWT Token: " + token);
    }
}