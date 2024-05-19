package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.JwtUtil;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/register")
    public Map<String, String> register(@RequestBody User user) {
        Map<String, String> response = new HashMap<>();
        if (userService.existsByUsername(user.getUsername())) {
            response.put("message", "Username is already taken!");
            return response;
        }

        if (userService.existsByEmail(user.getEmail())) {
            response.put("message", "Email is already taken!");
            return response;
        }

        userService.saveUser(user);
        response.put("message", "User registered successfully");
        return response;
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        String jwt = jwtUtil.generateToken(userDetails.getUsername());

        Map<String, String> response = new HashMap<>();
        response.put("token", jwt);
        return response;
    }
}
