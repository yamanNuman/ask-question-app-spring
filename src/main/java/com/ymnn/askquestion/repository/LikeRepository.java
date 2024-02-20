package com.ymnn.askquestion.repository;

import com.ymnn.askquestion.dto.response.LikeResponse;
import com.ymnn.askquestion.entity.Like;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

@Transactional
public interface LikeRepository extends JpaRepository<Like, Integer> {
    @Query(value = "SELECT * FROM LIKES WHERE post_id= :postId",nativeQuery = true)
    List<Like> findIsLike(Optional<Integer> postId);

    @Modifying
    @Query(value = "DELETE FROM LIKES WHERE user_id= :userId",nativeQuery = true)
    void deleteUserId(Integer userId);

}
