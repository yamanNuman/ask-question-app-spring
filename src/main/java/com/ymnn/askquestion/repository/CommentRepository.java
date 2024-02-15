package com.ymnn.askquestion.repository;

import com.ymnn.askquestionapp.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
