package com.ymnn.askquestion.controller;

import com.ymnn.askquestion.entity.User;
import com.ymnn.askquestion.repository.UserRepository;
import com.ymnn.askquestion.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {
    private UserRepository userRepository;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> allUser = userRepository.findAll();
        return ResponseEntity.ok(allUser);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getByIdUser(@PathVariable Integer id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with the given id : " + id));
        return ResponseEntity.ok(user);
    }

    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody User newUser) {
        userRepository.save(newUser);
        return new ResponseEntity(newUser, HttpStatus.CREATED);
    }

    @PutMapping("/user")
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody User updateUser) {
        User user = userRepository.findById(id)
                .orElseThrow(() ->  new UserNotFoundException("User not found with the given id : " + id));
        user.setUsername(updateUser.getUsername());
        user.setPassword(updateUser.getPassword());
        userRepository.save(user);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() ->  new UserNotFoundException("User not found with the given id : " + id));
        userRepository.delete(user);
        return ResponseEntity.ok("User deleted");
    }
}
