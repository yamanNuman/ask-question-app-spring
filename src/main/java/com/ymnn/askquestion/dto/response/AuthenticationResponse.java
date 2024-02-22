package com.ymnn.askquestion.dto.response;

import lombok.Data;

@Data
public class AuthenticationResponse {
    private String message;
    private String username;
    private Integer userId;
}
