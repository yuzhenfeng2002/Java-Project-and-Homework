package com.Yuzhen.eRestaurant.service.before;

import com.Yuzhen.eRestaurant.entity.BUser;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

public interface UserService {
    String isUse(BUser bUser);

    String login(BUser bUser, HttpSession session, Model model);
}
