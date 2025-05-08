package com.example.user_service.controller;

import com.example.user_service.event.UserEvent;
import com.example.user_service.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Tag(name = "interactive user documentation", description = " documentation APIs RESTful")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/save")
    @Operation(summary = "add user", description = "save user in database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = " saved user successful"),
            @ApiResponse(responseCode = "404", description = "user not found"),
            @ApiResponse(responseCode = "500", description = " error server")
    })
    public ResponseEntity<UserEvent> create(@RequestBody UserEvent userEvent){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.createUser(userEvent));
    }

    @GetMapping()
    @Operation(summary = "get all user", description = "get all users in database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = " get user successful"),
            @ApiResponse(responseCode = "404", description = "user not found"),
            @ApiResponse(responseCode = "500", description = " error server")
    })
    public ResponseEntity<List<UserEvent>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.getAllUser());
    }

    @GetMapping("/{userId}")
    @Operation(summary = "get one user", description = "get user in database with her id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = " get user successful"),
            @ApiResponse(responseCode = "404", description = "user not found"),
            @ApiResponse(responseCode = "500", description = " error server")
    })
    public ResponseEntity<UserEvent> get(@PathVariable int userId){
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.getById(userId));
    }

    @PutMapping("/update/{userId}")
    @Operation(summary = "update user", description = "update user in database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = " updated user successful"),
            @ApiResponse(responseCode = "404", description = "user not found"),
            @ApiResponse(responseCode = "500", description = " error server")
    })
    public ResponseEntity<UserEvent>update(@PathVariable int userId, @RequestBody UserEvent userEvent){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.userService.updateUser(userId, userEvent));
    }

    @DeleteMapping("/delete/{userId}")
    @Operation(summary = "delete user", description = "delete user in database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = " deleted user successful"),
            @ApiResponse(responseCode = "404", description = "user not found"),
            @ApiResponse(responseCode = "500", description = " error server")
    })
    public ResponseEntity<Boolean> delete(@PathVariable int userId){
        boolean isDelete = this.userService.deleteUser(userId);
        if (isDelete){
            return ResponseEntity.status(HttpStatus.OK).body(true);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
    }
}
