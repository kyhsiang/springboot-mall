package com.kyhsiang.springbootmall.dao;

import com.kyhsiang.springbootmall.dto.UserRegisterRequest;
import com.kyhsiang.springbootmall.dto.UserUpdateRequest;
import com.kyhsiang.springbootmall.model.User;

import java.util.List;

public interface UserDao {

    Integer createUser(UserRegisterRequest userRegisterRequest);

    User getUserById(Integer userId);

    User getUserByEmail(String email);

    List<User> getUsers();

    void updateUser(Integer userId, UserUpdateRequest userUpdateRequest);
}
