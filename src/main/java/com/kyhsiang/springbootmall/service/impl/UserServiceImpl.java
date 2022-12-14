package com.kyhsiang.springbootmall.service.impl;

import com.kyhsiang.springbootmall.dao.UserDao;
import com.kyhsiang.springbootmall.dto.UserLoginRequest;
import com.kyhsiang.springbootmall.dto.UserRegisterRequest;
import com.kyhsiang.springbootmall.dto.UserUpdateRequest;
import com.kyhsiang.springbootmall.model.User;
import com.kyhsiang.springbootmall.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Component
public class UserServiceImpl implements UserService {

    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    //註冊會員
    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        //檢查註冊的email
        User user = userDao.getUserByEmail(userRegisterRequest.getEmail());
        if (user != null) {
            log.warn("該 email {} 已經被註冊", userRegisterRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        //使用MD5生成密碼的雜湊值
        String hashedPassword = DigestUtils.md5DigestAsHex(userRegisterRequest.getPassword().getBytes());
        userRegisterRequest.setPassword(hashedPassword);
        //創建帳號
        return userDao.createUser(userRegisterRequest);
    }

    //登入
    @Override
    public User login(UserLoginRequest userLoginRequest) {
        User user = userDao.getUserByEmail(userLoginRequest.getEmail());
        //檢查user是否存在
        if (user == null) {
            log.warn("該 email {} 未被註冊", userLoginRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        //使用MD5生成密碼的雜湊值
        String hashedPassword = DigestUtils.md5DigestAsHex(userLoginRequest.getPassword().getBytes());
        //比較密碼
        if (user.getPassword().equals(hashedPassword)) {
            return user;
        } else {
            log.warn("該 email {} 的密碼不正確", userLoginRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    //藉由會員編號獲取會員
    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

    //獲取所有會員
    @Override
    public List<User> getUsers() {
        return userDao.getUsers();
    }

    //修改會員密碼
    @Override
    public void updateUser(Integer userId, UserUpdateRequest userUpdateRequest) {
        User user = userDao.getUserById(userId);
        String oldHashedPassword = DigestUtils.md5DigestAsHex(userUpdateRequest.getOldPassword().getBytes());
        //檢查輸入的原密碼是否正確
        if (!oldHashedPassword.equals(user.getPassword())) {
            log.warn("原密碼不正確");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        //檢查舊密碼和新密碼是否重複
        if (userUpdateRequest.getNewPassword().equals(userUpdateRequest.getOldPassword())) {
            log.warn("新密碼不可與舊密碼一樣");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        String newHashedPassword = DigestUtils.md5DigestAsHex(userUpdateRequest.getNewPassword().getBytes());
        userUpdateRequest.setNewPassword(newHashedPassword);
        userDao.updateUser(userId, userUpdateRequest);
    }
}
