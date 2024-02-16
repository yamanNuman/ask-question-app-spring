package com.ymnn.askquestion.controller;

import com.ymnn.askquestion.dto.request.LikeRequest;
import com.ymnn.askquestion.entity.Like;
import com.ymnn.askquestion.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;

    @GetMapping("/likes/{postId}")
    public ResponseEntity<List<Like>> getAllLikes(@PathVariable Integer postId) {
        return ResponseEntity.ok(likeService.getAllLikes(postId));
    }
    @GetMapping("/like/{likeId}")
    public ResponseEntity<Like> getLikeById(@PathVariable Integer likeId) {
        return ResponseEntity.ok(likeService.getLikeById(likeId));
    }
    @PostMapping("/like/{postId}/{userId}")
    public ResponseEntity<Like> createLike(@RequestBody LikeRequest like, @PathVariable Integer postId, @PathVariable Integer userId) {
        likeService.createLike(like,userId,postId);
        return new ResponseEntity(like, HttpStatus.CREATED);
    }

    @PutMapping("/like/{likeId}")
    public ResponseEntity<Like> updateLike(@RequestBody LikeRequest like, @PathVariable Integer likeId) {
        return ResponseEntity.ok(likeService.updateLike(like,likeId));
    }
    @DeleteMapping("/like/{likeId}")
    public ResponseEntity deleteLike(@PathVariable Integer likeId) {
        likeService.deleteLike(likeId);
        return ResponseEntity.ok("Like deleted.");
    }
}
