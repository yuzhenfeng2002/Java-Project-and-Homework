package com.Yuzhen.ExerciseOnline.service;

import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

import com.Yuzhen.ExerciseOnline.entity.User;

public interface UserService {
    public String login(User user, HttpSession session, Model model);

    public String register(User user, HttpSession session, Model model);

    public String isUser(User user);
}
