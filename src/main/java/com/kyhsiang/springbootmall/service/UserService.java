package com.kyhsiang.springbootmall.service;

import com.kyhsiang.springbootmall.dto.UserRegisterRequest;
import com.kyhsiang.springbootmall.model.User;

public interface UserService {

    Integer register(UserRegisterRequest userRegisterRequest);

    User getUserById(Integer userId);
}
