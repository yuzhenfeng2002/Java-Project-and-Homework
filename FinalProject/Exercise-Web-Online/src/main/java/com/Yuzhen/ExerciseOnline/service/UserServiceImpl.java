package com.Yuzhen.ExerciseOnline.service;

import com.Yuzhen.ExerciseOnline.entity.User;
import com.Yuzhen.ExerciseOnline.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Override
    public String login(User user, HttpSession session, Model model) {
        model.addAttribute("errorMessage", "");
        String rand = (String)session.getAttribute("rand");
        if(!rand.equalsIgnoreCase(user.getValidateCode())) {
            model.addAttribute("errorMessage", "验证码错误！");
            return "login";
        }
        List<User> list = userRepository.login(user);
        System.out.println("Succeeded to query!");
        if (list.size() > 0) // 登录成功
        {
            System.out.println("Succeeded to login!");
            session.setAttribute("user", user);
            return "test";
        }
        else // 登录失败
        {
            System.out.println("Failed to login!");
            model.addAttribute("errorMessage", "用户名或密码错误！");
            return "login";
        }
    }

    @Override
    public String register(User user) {
        if(userRepository.register(user) > 0) {
            return "login";
        }
        return "register";
    }

    @Override
    public String isUser(User user) {
        if(userRepository.isUser(user).size() > 0) {
            return "no";
        }
        return "ok";
    }
}
