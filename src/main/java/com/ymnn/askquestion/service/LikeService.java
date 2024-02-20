package com.ymnn.askquestion.service;

import com.ymnn.askquestion.dto.response.LikeResponse;
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
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public List<LikeResponse> getAllLikes(Optional<Integer> postId) {
       List<Like> list;
       list = likeRepository.findIsLike(postId);
        return list.stream().map(like -> new LikeResponse(like)).collect(Collectors.toList());
    }

    public Like getLikeById(Integer likeId) {
       Like like =  likeRepository.findById(likeId).orElseThrow(() -> new LikeNotFoundException("Like not found with the given id : " + likeId));
        return like;
    }

    public void createLike(Integer userId, Integer postId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with the given id : " + userId));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post not found with the given id : " + postId));
        Like newLike = Like.builder()
                .user(user)
                .post(post)
                .build();
        likeRepository.save(newLike);
    }

    public void deleteLike(Integer userId) {
        likeRepository.deleteUserId(userId);
    }

}
