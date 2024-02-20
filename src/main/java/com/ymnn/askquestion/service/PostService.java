package com.ymnn.askquestion.service;

import com.ymnn.askquestion.dto.request.PostRequest;
import com.ymnn.askquestion.dto.response.LikeResponse;
import com.ymnn.askquestion.dto.response.PostResponse;
import com.ymnn.askquestion.entity.Like;
import com.ymnn.askquestion.entity.Post;
import com.ymnn.askquestion.entity.User;
import com.ymnn.askquestion.exception.PostNotFoundException;
import com.ymnn.askquestion.exception.UserNotFoundException;
import com.ymnn.askquestion.repository.PostRepository;
import com.ymnn.askquestion.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private  LikeService likeService;
    @Autowired
    public void setLikeService(LikeService likeService) {
        this.likeService = likeService;
    }

    public List<PostResponse> getAllPost(Optional<Integer> userId) {
        List<Post> list;
        if(userId.isPresent()){
           list = postRepository.findByUserId(userId.get());
        } else {
            list = postRepository.findAll();
        }
        return list.stream().map(post -> {
            List<LikeResponse> likes = likeService.getAllLikes(Optional.of(post.getId()));
            return new PostResponse(post,likes);
        }).collect(Collectors.toList());
    }

    public Post getPostById(Integer id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("Post not found with the given id : " + id));
        return post;
    }

    public void createPost(PostRequest post, Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with the given id : " + userId));
        Post newPost = Post.builder()
                        .text(post.getText())
                        .title(post.getTitle())
                        .user(user)
                        .build();
        postRepository.save(newPost);
    }

    public Post updatePost(PostRequest post,Integer userId, Integer postId) {
        Post updatePost = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post not found with the given id : " + postId));
        userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with the given id : " + userId));
        updatePost.setText(post.getText());
        updatePost.setTitle(post.getTitle());
        postRepository.save(updatePost);
        return updatePost;
    }

    public void deletePost(Integer postId) {
        postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post not found with the given id : " + postId));
        postRepository.deleteById(postId);
    }
}
