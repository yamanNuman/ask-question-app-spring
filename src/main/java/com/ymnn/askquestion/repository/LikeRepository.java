package com.ymnn.askquestion.repository;

import com.ymnn.askquestion.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Integer> {
    @Query(value = "SELECT * FROM LIKES WHERE post_id= :postId AND is_like=true",nativeQuery = true)
    List<Like> findIsLike(Integer postId);
}
