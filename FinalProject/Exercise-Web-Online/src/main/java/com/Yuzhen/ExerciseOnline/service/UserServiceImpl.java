package com.Yuzhen.ExerciseOnline.service;

import com.Yuzhen.ExerciseOnline.entity.User;
import com.Yuzhen.ExerciseOnline.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public String login(User user, HttpSession session, Model model) {
        model.addAttribute("errorMessage", "");
        String rand = (String) session.getAttribute("rand");
        if (!rand.equalsIgnoreCase(user.getValidateCode())) {
            model.addAttribute("errorMessage", "验证码错误！");
            return "login";
        }
        List<User> list = userRepository.login(user);
        if (list.size() > 0) // 登录成功
        {
            session.setAttribute("user", list.get(0));
            return "redirect:/index";
        } else // 登录失败
        {
            model.addAttribute("errorMessage", "用户名、密码或用户类型错误！");
            return "login";
        }
    }

    @Override
    public String register(User user, HttpSession session, Model model) {
        model.addAttribute("errorMessage", "");
        String rand = (String) session.getAttribute("rand");
        if (!rand.equalsIgnoreCase(user.getValidateCode())) {
            model.addAttribute("errorMessage", "验证码错误！");
            return "register";
        }
        if (userRepository.isUser(user).size() > 0) {
            model.addAttribute("errorMessage", "邮箱已存在！");
            return "register";
        }
        if (!Objects.equals(user.getPassword(), user.getPassword2())) {
            model.addAttribute("errorMessage", "两次密码不一致，请重新输入！");
            return "register";
        }
        if (userRepository.register(user) > 0) {
            return "login";
        }
        return "register";
    }

    @Override
    public String isUser(User user) {
        if (userRepository.isUser(user).size() > 0) {
            return "yes";
        }
        return "no";
    }
}
