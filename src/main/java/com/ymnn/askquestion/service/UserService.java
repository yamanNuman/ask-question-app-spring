package com.ymnn.askquestion.service;

import com.ymnn.askquestion.entity.User;
import com.ymnn.askquestion.exception.UserNotFoundException;
import com.ymnn.askquestion.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        List<User> allUsers = userRepository.findAll();
        return allUsers;
    }

    public void createUser(User newUser) {
        userRepository.save(newUser);
    }

    public User getUserById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with the given id : " + id));
        return user;
    }

    public User updateUser (User updateUser,Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() ->  new UserNotFoundException("User not found with the given id : " + id));
        user.setUsername(updateUser.getUsername());
        user.setPassword(updateUser.getPassword());
        userRepository.save(user);
        return user;
    }

    public void deleteUser(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() ->  new UserNotFoundException("User not found with the given id : " + id));
        userRepository.delete(user);
    }
}
