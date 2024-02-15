package com.ymnn.askquestion.repository;

import com.ymnn.askquestion.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Integer> {
}
