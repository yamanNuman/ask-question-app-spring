package com.ymnn.askquestion.dto.response;

import com.ymnn.askquestion.entity.Like;
import lombok.Data;

@Data
public class LikeResponse {
    private Integer id;
    private Integer userId;
    private Integer postId;

    public LikeResponse(Like entity) {
        this.id = entity.getId();
        this.userId = entity.getUser().getId();
        this.postId = entity.getPost().getId();
    }
}
