package com.kyhsiang.springbootmall.service;

import com.kyhsiang.springbootmall.dto.UserLoginRequest;
import com.kyhsiang.springbootmall.dto.UserRegisterRequest;
import com.kyhsiang.springbootmall.model.User;

public interface UserService {

    Integer register(UserRegisterRequest userRegisterRequest);

    User login(UserLoginRequest userLoginRequest);

    User getUserById(Integer userId);

}
