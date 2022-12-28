package com.kyhsiang.springbootmall.controller;

import com.kyhsiang.springbootmall.dto.UserLoginRequest;
import com.kyhsiang.springbootmall.dto.UserRegisterRequest;
import com.kyhsiang.springbootmall.dto.UserUpdateRequest;
import com.kyhsiang.springbootmall.model.User;
import com.kyhsiang.springbootmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private HttpSession session;

    //註冊會員
    @PostMapping("/users/register")
    public ResponseEntity<User> register(@RequestBody @Valid UserRegisterRequest userRegisterRequest) {
        Integer userId = userService.register(userRegisterRequest);
        User user = userService.getUserById(userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    //登入
    @PostMapping("/users/login")
    public ResponseEntity<User> login(@RequestBody @Valid UserLoginRequest userLoginRequest){
        User user = userService.login(userLoginRequest);
        session.setAttribute("userId", user.getUserId());
        session.setAttribute("email", userLoginRequest.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    //修改會員密碼
    @PutMapping("/users/update")
    public ResponseEntity<User> updateUser(@RequestBody @Valid UserUpdateRequest userUpdateRequest) {
        Integer userId = (Integer) session.getAttribute("userId");
        userService.updateUser(userId, userUpdateRequest);
        User updatedUser = userService.getUserById(userId);
        return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
    }
}
