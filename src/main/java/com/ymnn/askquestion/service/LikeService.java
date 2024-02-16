package com.ymnn.askquestion.service;

import com.ymnn.askquestion.dto.request.LikeRequest;
import com.ymnn.askquestion.entity.Like;
import com.ymnn.askquestion.entity.Post;
import com.ymnn.askquestion.entity.User;
import com.ymnn.askquestion.exception.LikeNotFoundException;
import com.ymnn.askquestion.exception.PostNotFoundException;
import com.ymnn.askquestion.exception.UserNotFoundException;
import com.ymnn.askquestion.repository.LikeRepository;
import com.ymnn.askquestion.repository.PostRepository;
import com.ymnn.askquestion.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public List<Like> getAllLikes(Integer postId) {
       return likeRepository.findIsLike(postId);
    }

    public Like getLikeById(Integer likeId) {
       Like like =  likeRepository.findById(likeId).orElseThrow(() -> new LikeNotFoundException("Like not found with the given id : " + likeId));
        return like;
    }

    public void createLike(LikeRequest like,Integer userId, Integer postId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with the given id : " + userId));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post not found with the given id : " + postId));
        Like newLike = Like.builder()
                .isLike(like.isLike())
                .user(user)
                .post(post)
                .build();
        likeRepository.save(newLike);
    }

    public void deleteLike(Integer likeId) {
        likeRepository.findById(likeId).orElseThrow(() -> new LikeNotFoundException("Like not found with the given id : " + likeId));
        likeRepository.deleteById(likeId);
    }

    public Like updateLike(LikeRequest like, Integer likeId) {
        Like updateLike = likeRepository.findById(likeId).orElseThrow(() -> new LikeNotFoundException("Like not found with the given id : " + likeId));
        updateLike.setLike(like.isLike());
        likeRepository.save(updateLike);
        return updateLike;
    }
}
