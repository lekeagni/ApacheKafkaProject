package com.example.user_service.service.ServiceImpl;

import com.example.user_service.event.UserEvent;
import com.example.user_service.exception.UserAlreadyExistException;
import com.example.user_service.exception.UserNotFoundException;
import com.example.user_service.mapper.UserMapper;
import com.example.user_service.model.UserModel;
import com.example.user_service.repository.UserRepository;
import com.example.user_service.service.KafkaProducerService;
import com.example.user_service.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final KafkaProducerService kafkaProducerService;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, KafkaProducerService kafkaProducerService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.kafkaProducerService = kafkaProducerService;
    }

    @Override
    public UserEvent createUser(UserEvent userEvent) {
        UserModel userModel = this.userRepository.findAllByName(userEvent.getName());
        if (userModel != null){
             throw new UserAlreadyExistException(userEvent.getName());
        }
        UserModel user = userMapper.toTEntity(userEvent);
        UserModel saved = this.userRepository.save(user);
        UserEvent savedEvent = userMapper.toDO(saved);
        KafkaProducerService.SendUserEvent(savedEvent);
        return savedEvent;
    }

    @Override
    public List<UserEvent> getAllUser() {
        List<UserModel> get = this.userRepository.findAll();
        return get.stream().map(userMapper::toDO).collect(Collectors.toList());
    }

    @Override
    public UserEvent getById(int userId) {
        UserModel userModel = this.userRepository.findById(userId)
                .orElseThrow(()->new UserNotFoundException(userId));
        return userMapper.toDO(userModel);
    }

    @Override
    public UserEvent updateUser(int userId, UserEvent userEvent) {
        UserModel exist = this.userRepository.findById(userId)
                .orElseThrow(()->new UserNotFoundException(userId));

            exist.setName(userEvent.getName());
            exist.setEmail(userEvent.getEmail());
            exist.setPhone(userEvent.getPhone());
            return userMapper.toDO(this.userRepository.save(exist));

    }

    @Override
    public Boolean deleteUser(int userId) {
        Optional<UserModel> exist = this.userRepository.findById(userId);
        if (exist.isPresent()){
            UserModel u = exist.get();
            this.userRepository.delete(u);
            return true;
        }
        return false ;
    }
}
