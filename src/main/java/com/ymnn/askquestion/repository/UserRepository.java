package com.ymnn.askquestion.repository;

import com.ymnn.askquestion.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
   User findByUserName(String username);
}
