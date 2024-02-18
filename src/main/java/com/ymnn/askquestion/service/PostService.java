package com.ymnn.askquestion.service;

import com.ymnn.askquestion.dto.request.PostRequest;
import com.ymnn.askquestion.entity.Post;
import com.ymnn.askquestion.entity.User;
import com.ymnn.askquestion.exception.PostNotFoundException;
import com.ymnn.askquestion.exception.UserNotFoundException;
import com.ymnn.askquestion.repository.PostRepository;
import com.ymnn.askquestion.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public List<Post> getAllPost(Optional<Integer> userId) {
        if(userId.isEmpty()){
            return (List<Post>) new PostNotFoundException("Post not found with the given id : " + userId.get());
        }
        return postRepository.findByUserId(userId);
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
