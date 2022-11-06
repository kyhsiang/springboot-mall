package com.kyhsiang.springbootmall.dao;

import com.kyhsiang.springbootmall.dto.UserRegisterRequest;
import com.kyhsiang.springbootmall.model.User;

public interface UserDao {

    Integer createUser(UserRegisterRequest userRegisterRequest);

    User getUserById(Integer userId);
}
