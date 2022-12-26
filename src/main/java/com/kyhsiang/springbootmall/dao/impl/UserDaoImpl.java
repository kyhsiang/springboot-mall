package com.kyhsiang.springbootmall.dao.impl;

import com.kyhsiang.springbootmall.dao.UserDao;
import com.kyhsiang.springbootmall.dto.UserRegisterRequest;
import com.kyhsiang.springbootmall.dto.UserUpdateRequest;
import com.kyhsiang.springbootmall.mapper.UserRowMapper;
import com.kyhsiang.springbootmall.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserDaoImpl implements UserDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    //新增會員
    @Override
    public Integer createUser(UserRegisterRequest userRegisterRequest) {
        String sql = "INSERT INTO user(email, password, created_date, last_modified_date) VALUES (:email, :password, :createdDate, :lastModifiedDate)";
        Map<String, Object> map = new HashMap<>();
        map.put("email", userRegisterRequest.getEmail());
        map.put("password", userRegisterRequest.getPassword());
        Date now = new Date();
        map.put("createdDate", now);
        map.put("lastModifiedDate", now);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);
        int userId = keyHolder.getKey().intValue();
        return userId;
    }

    //藉由會員編號獲取特定會員資訊
    @Override
    public User getUserById(Integer userId) {
        String sql = "SELECT user_id, email, password, created_date, last_modified_date FROM user WHERE user_id = :userId";
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        List<User> userList = namedParameterJdbcTemplate.query(sql, map, new UserRowMapper());
        if (userList.size() > 0) {
            return userList.get(0);
        } else {
            return null;
        }
    }

    //藉由會員Email獲取特定會員資訊
    @Override
    public User getUserByEmail(String email) {
        String sql = "SELECT user_id, email, password, created_date, last_modified_date FROM user WHERE email = :email";
        Map<String, Object> map = new HashMap<>();
        map.put("email", email);
        List<User> userList = namedParameterJdbcTemplate.query(sql, map, new UserRowMapper());
        if (userList.size() > 0) {
            return userList.get(0);
        } else {
            return null;
        }
    }

    //獲取所有會員資訊
    @Override
    public List<User> getUsers() {
        String sql = "SELECT user_id, email, password, created_date, last_modified_date FROM user";
        Map<String, Object> map = new HashMap<>();
        List<User> userList = namedParameterJdbcTemplate.query(sql, map, new UserRowMapper());
        return userList;
    }

    //修改會員密碼
    @Override
    public void updateUser(Integer userId, UserUpdateRequest userUpdateRequest) {
        String sql = "UPDATE user SET password = :newPassword, last_modified_date = :lastModifiedDate WHERE user_id = :userId";
        Map<String, Object> map = new HashMap<>();
        map.put("newPassword", userUpdateRequest.getNewPassword());
        map.put("userId", userId);
        map.put("lastModifiedDate", new Date());
        namedParameterJdbcTemplate.update(sql, map);
    }
}
