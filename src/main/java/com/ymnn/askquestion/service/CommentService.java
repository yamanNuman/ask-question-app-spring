package com.ymnn.askquestion.service;

import com.ymnn.askquestion.dto.request.CommentRequest;
import com.ymnn.askquestion.dto.response.CommentResponse;
import com.ymnn.askquestion.entity.Comment;
import com.ymnn.askquestion.entity.Post;
import com.ymnn.askquestion.entity.User;
import com.ymnn.askquestion.exception.CommentNotFoundException;
import com.ymnn.askquestion.exception.PostNotFoundException;
import com.ymnn.askquestion.exception.UserNotFoundException;
import com.ymnn.askquestion.repository.CommentRepository;
import com.ymnn.askquestion.repository.PostRepository;
import com.ymnn.askquestion.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    public List<CommentResponse> getAllComments(Integer postId) {
        List<Comment> list;
        list = commentRepository.findByPostId(postId);
        return list.stream().map(comment -> new CommentResponse(comment)).collect(Collectors.toList());
    }

    public List<Comment> getUserComments(Integer postId, Integer userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with the given id : " + userId));
        postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post not found with the given id : " + postId));
        return commentRepository.findByUserIdAndPostId(userId,postId);
    }

    public Comment getComment(Integer commentId) {
        Comment comment =  commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException("Comment not found with the given id " + commentId));
        return comment;
    }

    public void createComment(CommentRequest comment,Integer postId, Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with the given id : " + userId));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post not found with the given id : " + postId));
        Comment newComment = Comment.builder()
                .post(post)
                .user(user)
                .text(comment.getText())
                .build();
        commentRepository.save(newComment);
    }

    public Comment updateComment(CommentRequest comment, Integer commentId) {
        Comment updateComment = commentRepository.findById(commentId).orElseThrow(() -> new CommentNotFoundException("Comment not found with the given id : " + commentId));
        updateComment.setText(comment.getText());
        commentRepository.save(updateComment);
        return updateComment;
    }

    public void deleteComment(Integer commentId) {
        commentRepository.findById(commentId).orElseThrow(() -> new CommentNotFoundException("Comment not found with the given id : " + commentId));
        commentRepository.deleteById(commentId);
    }
}
