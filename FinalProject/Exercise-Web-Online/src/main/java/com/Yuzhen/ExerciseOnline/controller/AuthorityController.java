package com.Yuzhen.ExerciseOnline.controller;

import com.Yuzhen.ExerciseOnline.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;

import com.Yuzhen.ExerciseOnline.NoLoginException;

@Controller
public class AuthorityController {
    /**
     * 登录权限控制，处理方法执行前执行该方法
     */
    @ModelAttribute
    public void isLogin(HttpSession session) throws NoLoginException {
        if (session.getAttribute("user") == null) {
            throw new NoLoginException("用户未登录！");
        }
    }
}
