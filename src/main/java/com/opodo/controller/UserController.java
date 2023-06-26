package com.opodo.controller;

import com.opodo.payload.UserDto;
import com.opodo.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserDto createdUser = userService.createUser(userDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUserPassword(
            @PathVariable Long userId,
            @RequestBody UserDto userDto) {
        UserDto updatedUser = userService.updateUserPassword(userId, userDto);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>( "User is deleted",HttpStatus.OK);
    }
    @GetMapping("/{userid}")
    public ResponseEntity<UserDto> getPostById(@PathVariable Long userid){
        return new ResponseEntity<>(userService.getUserById(userid),HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<Page<UserDto>> getUsers(@PageableDefault(size = 10, sort = "id") Pageable pageable) {
        //   pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("id").ascending());
        Page<UserDto> usersPage = userService.getUsers(pageable);
        return new ResponseEntity<>(usersPage, HttpStatus.OK);
    }


}

