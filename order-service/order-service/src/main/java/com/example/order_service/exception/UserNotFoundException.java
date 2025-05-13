package com.example.order_service.exception;

public class UserNotFoundException extends CustomException {
  public UserNotFoundException(Integer userId) {

    super("user not found with " +userId);
  }
}
