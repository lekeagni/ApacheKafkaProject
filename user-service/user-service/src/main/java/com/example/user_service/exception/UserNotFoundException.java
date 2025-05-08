package com.example.user_service.exception;

public class UserNotFoundException extends CustomException {
    public UserNotFoundException(Integer userId) {

        super("User not fount with"+userId);
    }
}
