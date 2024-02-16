package com.ymnn.askquestion.repository;

import com.ymnn.askquestion.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findByPostId(Integer postId);
    List<Comment> findByUserIdAndPostId(Integer userId,Integer postId);

}
