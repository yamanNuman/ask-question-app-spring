package com.ymnn.askquestion.repository;

import com.ymnn.askquestionapp.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Integer> {
}
