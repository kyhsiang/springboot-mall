package com.kyhsiang.springbootmall.service;

import com.kyhsiang.springbootmall.dto.UserLoginRequest;
import com.kyhsiang.springbootmall.dto.UserRegisterRequest;
import com.kyhsiang.springbootmall.dto.UserUpdateRequest;
import com.kyhsiang.springbootmall.model.User;

import java.util.List;

public interface UserService {

    Integer register(UserRegisterRequest userRegisterRequest);

    User login(UserLoginRequest userLoginRequest);

    User getUserById(Integer userId);

    List<User> getUsers();

    void updateUser(Integer userId, UserUpdateRequest userUpdateRequest);

}
