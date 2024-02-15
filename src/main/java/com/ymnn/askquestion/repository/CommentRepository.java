package com.ymnn.askquestion.repository;

import com.ymnn.askquestion.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
