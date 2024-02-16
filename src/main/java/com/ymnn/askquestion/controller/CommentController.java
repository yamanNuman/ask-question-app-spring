package com.ymnn.askquestion.controller;

import com.ymnn.askquestion.dto.request.CommentRequest;
import com.ymnn.askquestion.entity.Comment;
import com.ymnn.askquestion.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/comments/{postId}")
    public ResponseEntity<List<Comment>> getAllComments(@PathVariable Integer postId) {
        return ResponseEntity.ok(commentService.getAllComments(postId));
    }
    @GetMapping("/comments/{postId}/{userId}")
    public ResponseEntity<List<Comment>> getUserComments(@PathVariable Integer postId,@PathVariable Integer userId) {
        return ResponseEntity.ok(commentService.getUserComments(postId,userId));
    }
    @GetMapping("/comment/{commentId}")
    public ResponseEntity<Comment> getComment(@PathVariable Integer commentId) {
        return ResponseEntity.ok(commentService.getComment(commentId));
    }
    @PostMapping("/comment/{postId}/{userId}")
    public ResponseEntity<Comment> createComment(@RequestBody CommentRequest comment, @PathVariable Integer postId, @PathVariable Integer userId) {
        commentService.createComment(comment,postId,userId);
        return new ResponseEntity(comment, HttpStatus.CREATED);
    }
    @PutMapping("/comment/{commentId}")
    public ResponseEntity<Comment> updateComment(@RequestBody CommentRequest comment, @PathVariable Integer commentId) {
        return ResponseEntity.ok(commentService.updateComment(comment,commentId));
    }
    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity deleteComment(@PathVariable Integer commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok("Comment deleted");
    }
}
