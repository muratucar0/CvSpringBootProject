package com.muratucar.financialanalysis.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.muratucar.financialanalysis.entity.User;
import com.muratucar.financialanalysis.requestdto.AuthRequest;
import com.muratucar.financialanalysis.requestdto.CreateUserRequest;
import com.muratucar.financialanalysis.service.JwtService;
import com.muratucar.financialanalysis.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/auth")
@Slf4j
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public UserController(UserService userService, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/newuser")
    public ResponseEntity<User> addUser(@RequestBody CreateUserRequest request) {
        User createdUser = userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PostMapping("/generateToken")
    public ResponseEntity<String> generateToken(@RequestBody AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));

        if (authentication.isAuthenticated()) {
            String token = jwtService.generateToken(request.username());
            return ResponseEntity.ok(token);
        } else {
            log.info("Invalid username or password: " + request.username());
            throw new UsernameNotFoundException("Invalid username or password: " + request.username());
        }
    }
}