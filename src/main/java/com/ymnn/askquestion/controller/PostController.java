package com.ymnn.askquestion.controller;

import com.ymnn.askquestion.dto.request.PostRequest;
import com.ymnn.askquestion.dto.response.PostResponse;
import com.ymnn.askquestion.entity.Post;
import com.ymnn.askquestion.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/posts")
    public ResponseEntity<List<PostResponse>> getAllPost(@RequestParam Optional<Integer> userId){
        return ResponseEntity.ok(postService.getAllPost(userId));
    }
    @GetMapping("/post/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Integer id){
        Post post = postService.getPostById(id);
     return ResponseEntity.ok(post);
    }
    @PostMapping("/post/{userId}")
    public ResponseEntity<Post> createPost(@RequestBody PostRequest post, @PathVariable Integer userId) {
        postService.createPost(post,userId);
        return new ResponseEntity(post, HttpStatus.CREATED);
    }

    @PutMapping("/post/{userId}/{postId}")
    public ResponseEntity<Post> updatePost(@RequestBody PostRequest post,@PathVariable Integer userId,@PathVariable Integer postId) {
        return ResponseEntity.ok(postService.updatePost(post,userId,postId));
    }

    @DeleteMapping("/post/{postId}")
    public ResponseEntity deletePost(@PathVariable Integer postId){
        postService.deletePost(postId);
        return ResponseEntity.ok("Post deleted");
    }
}
