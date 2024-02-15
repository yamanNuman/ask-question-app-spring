package com.ymnn.askquestion.controller;

import com.ymnn.askquestion.entity.User;
import com.ymnn.askquestion.repository.UserRepository;
import com.ymnn.askquestion.exception.UserNotFoundException;
import com.ymnn.askquestion.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getByIdUser(@PathVariable Integer id){
       User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody User newUser) {
        userService.createUser(newUser);
        return new ResponseEntity(newUser, HttpStatus.CREATED);
    }

    @PutMapping("/user")
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody User updateUser) {
        User user = userService.updateUser(updateUser,id);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted");
    }
}
