package com.ymnn.askquestion.dto.response;

import com.ymnn.askquestion.entity.Comment;
import lombok.Data;

@Data
public class CommentResponse {
    private Integer userId;
    private String username;
    private String text;

    public CommentResponse(Comment entity) {
        this.text = entity.getText();
        this.userId = entity.getUser().getId();
        this.username = entity.getUser().getUsername();
    }

}
