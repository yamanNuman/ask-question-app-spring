package com.ymnn.askquestion.repository;

import com.ymnn.askquestionapp.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
