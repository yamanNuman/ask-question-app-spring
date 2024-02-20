package com.ymnn.askquestion.controller;

import com.ymnn.askquestion.dto.response.LikeResponse;
import com.ymnn.askquestion.entity.Like;
import com.ymnn.askquestion.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;

    @GetMapping("/likes/{postId}")
    public ResponseEntity<List<LikeResponse>> getAllLikes(@PathVariable Optional<Integer> postId) {
        return ResponseEntity.ok(likeService.getAllLikes(postId));
    }
    @GetMapping("/like/{likeId}")
    public ResponseEntity<Like> getLikeById(@PathVariable Integer likeId) {
        return ResponseEntity.ok(likeService.getLikeById(likeId));
    }
    @PostMapping("/like/{postId}/{userId}")
    public ResponseEntity<Like> createLike(@PathVariable Integer postId, @PathVariable Integer userId) {
        likeService.createLike(userId,postId);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @DeleteMapping("/like/{userId}")
    public ResponseEntity deleteLike(@PathVariable Integer userId) {
        likeService.deleteLike(userId);
        return ResponseEntity.ok("Like deleted.");
    }
}
