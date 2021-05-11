package com.controller;

import com.controller.exceptions.UserNotFoundException;
import com.domain.User;
import com.domain.dto.UserDto;
import com.mapper.UserMapper;
import com.service.UserDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/v1/user")
public class UserController {
    
    private  UserMapper mapper;
    private  UserDbService service;

    @Autowired
    public UserController(UserMapper mapper, UserDbService service) {
        this.mapper = mapper;
        this.service = service;
    }

    @GetMapping(value = "getAllUsers")
    public List<UserDto> getUsers() {
        List<User> users = service.getAllUsers();
        return mapper.mapToUserDtoList(users);
    }

    @GetMapping("getUser")
    public UserDto getUser(@RequestParam Long userId) throws UserNotFoundException {
        return mapper.mapToUserDto(service.getUser(userId).orElseThrow(UserNotFoundException::new));
    }

    @PostMapping(value = "createUser", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody UserDto userDto) {
        User user = mapper.mapToUser(userDto);
        service.saveUser(user);
    }

    @PutMapping(value = "updateUser")
    public UserDto updateUser(@RequestBody UserDto userDto) {
        User user = mapper.mapToUser(userDto);
        User savedUser = service.saveUser(user);
        return mapper.mapToUserDto(savedUser);
    }

    @PutMapping(value = "banUser")
    public UserDto banUser(@RequestParam Long userId) throws UserNotFoundException {
        User user = service.getUser(userId).orElseThrow(UserNotFoundException::new);
        user.setStatus(false);
        User savedUser = service.saveUser(user);
        return mapper.mapToUserDto(savedUser);
    }

    @PutMapping(value = "generateRandomKey")
    public UserDto generateRandomKey(@RequestParam Long userId) throws UserNotFoundException {
        Random randomUserKey = new Random();
        User user = service.getUser(userId).orElseThrow(UserNotFoundException::new);
        user.setUserKey(Math.abs(randomUserKey.nextLong()));
        User savedUser = service.saveUser(user);
        return mapper.mapToUserDto(savedUser);
    }

    @DeleteMapping(value = "deleteUser")
    public void deleteProduct(@RequestParam Long userId) throws UserNotFoundException {
        service.deleteUser(userId);
    }
}
