package com.ymnn.askquestion.controller;

import com.ymnn.askquestion.dto.request.UserRequest;
import com.ymnn.askquestion.dto.response.AuthenticationResponse;
import com.ymnn.askquestion.entity.User;
import com.ymnn.askquestion.jwt.JwtTokenProvider;
import com.ymnn.askquestion.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody UserRequest request) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword());
        Authentication auth = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwtToken = jwtTokenProvider.generateJwtToken(auth);
        User user =  userService.getUserByUsername(request.getUsername());
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setMessage("Bearer " + jwtToken);
        authenticationResponse.setUserId(user.getId());
        authenticationResponse.setUsername(user.getUsername());
        return authenticationResponse;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody UserRequest request) {
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        if(userService.getUserByUsername(request.getUsername()) != null) {
            authenticationResponse.setMessage("Username already in use");
            return new ResponseEntity(authenticationResponse, HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userService.createUser(user);
        authenticationResponse.setMessage("User registered.");
        return new ResponseEntity(authenticationResponse,HttpStatus.CREATED);
    }
}
