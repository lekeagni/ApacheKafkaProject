package com.example.product_service.exception;

import com.example.product_service.model.ErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

public class GlobalHandlerException {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorException> HandlerProductNotFoundException(ProductNotFoundException exception){
        ErrorException errorException = ErrorException.builder()
                .localDateTime(LocalDateTime.now())
                .httpStatus(HttpStatus.NOT_FOUND.value())
                .message(exception.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorException);
    }

    @ExceptionHandler(ProductAlreadyExistException.class)
    public ResponseEntity<ErrorException>HandlerProductAlreadyExistException(ProductAlreadyExistException exception){
        ErrorException errorException = ErrorException.builder()
                .localDateTime(LocalDateTime.now())
                .httpStatus(HttpStatus.CONFLICT.value())
                .message(exception.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorException);
    }
}
