package com.ymnn.askquestion.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public Map<String,String> handleUserNotFoundException(UserNotFoundException exception) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("Error Message", exception.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(PostNotFoundException.class)
    public Map<String, String> handlePostNotFoundException(PostNotFoundException exception) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("Error Message", exception.getMessage());
        return errorMap;
    }
}
