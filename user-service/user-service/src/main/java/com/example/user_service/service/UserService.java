package com.example.user_service.service;

import com.example.user_service.event.UserEvent;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    public UserEvent createUser(UserEvent userEvent);

    public List<UserEvent>getAllUser();

    public UserEvent getById(int userId);

    public UserEvent updateUser(int userId, UserEvent userEvent);

    public Boolean deleteUser(int userId);
}
