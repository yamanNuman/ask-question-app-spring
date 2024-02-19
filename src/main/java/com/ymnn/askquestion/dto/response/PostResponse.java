package com.ymnn.askquestion.dto.response;

import com.ymnn.askquestion.entity.Post;
import lombok.Data;

@Data
public class PostResponse {
    private Integer id;
    private Integer userId;
    private String username;
    private String title;
    private String text;

    public PostResponse(Post entity) {
        this.id = entity.getId();
        this.userId = entity.getUser().getId();
        this.username = entity.getUser().getUsername();
        this.title = entity.getTitle();
        this.text = entity.getText();
    }
}
