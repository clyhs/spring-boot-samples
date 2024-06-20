package org.abigfish.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public ResponseEntity<Object> handleMediaTypeNotAcceptableException(HttpMediaTypeNotAcceptableException ex) {
        // 构建错误响应对象
        ErrorResponse errorResponse = ErrorResponse.builder(ex, HttpStatus.NOT_ACCEPTABLE, "Unsupported media type").build();

        // 返回错误响应
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_ACCEPTABLE);
    }
}