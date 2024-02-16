package com.ymnn.askquestion.repository;

import com.ymnn.askquestion.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findByUserId(Optional<Integer> userId);
}
