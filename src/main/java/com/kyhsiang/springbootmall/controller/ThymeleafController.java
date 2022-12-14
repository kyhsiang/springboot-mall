package com.kyhsiang.springbootmall.controller;

import com.kyhsiang.springbootmall.model.User;
import com.kyhsiang.springbootmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@Validated
public class ThymeleafController {

    @Autowired
    private UserService userService;

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/adduser")
    public String addUser() {
        return "adduser";
    }

    @GetMapping("/getuser")
    public String getUsers(Model model) {
        List<User> userList = userService.getUsers();
        model.addAttribute("userList", userList);
        return "getuser";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "index";
    }

    @GetMapping("/updateuser")
    public String updateUser() {
        return "updateuser";
    }

    @GetMapping("/addproduct")
    public String addProduct() {
        return "addproduct";
    }

    @GetMapping("/getproduct")
    public String getProduct() {
        return "getproduct";
    }

    @GetMapping("/updateproduct")
    public String updateProduct() {
        return "updateproduct";
    }

    @GetMapping("/deleteproduct")
    public String deleteProduct() {
        return "deleteproduct";
    }

    @GetMapping("/cart")
    public String cart() {
        return "cart";
    }

    @GetMapping("/getorder")
    public String getOrder() {
        return "getorder";
    }
}
