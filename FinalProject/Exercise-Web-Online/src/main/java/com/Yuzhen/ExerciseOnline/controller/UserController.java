package com.Yuzhen.ExerciseOnline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

import com.Yuzhen.ExerciseOnline.entity.User;
import com.Yuzhen.ExerciseOnline.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/toLogin")
    public String toLogin(@ModelAttribute("user") User user, HttpSession session) {
        session.removeAttribute("user");
        return "login";
    }

    @RequestMapping("/login")
    public String login(@ModelAttribute("user") @Validated User user, BindingResult rs, HttpSession session, Model model) {
        if (rs.hasErrors()) // 验证失败
        {
            return "login";
        }
        return userService.login(user, session, model);
    }

    @RequestMapping("/toRegister")
    public String toRegister(@ModelAttribute("user") User user) {
        return "register";
    }

    @RequestMapping("/register")
    public String register(@ModelAttribute("user") @Validated User user, BindingResult rs, HttpSession session, Model model) {
        if (rs.hasErrors()) // 验证失败
        {
            return "register";
        }
        return userService.register(user, session, model);
    }

    @RequestMapping("/isUser")
    @ResponseBody
    public String isUser(@RequestBody User user) {
        return userService.isUser(user);
    }
}
