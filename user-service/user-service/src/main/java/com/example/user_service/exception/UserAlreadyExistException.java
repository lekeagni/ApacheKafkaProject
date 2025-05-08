package com.example.user_service.exception;

public class UserAlreadyExistException extends CustomException {
    public UserAlreadyExistException(String name) {

        super("User already exist with"+ name);
    }
}
