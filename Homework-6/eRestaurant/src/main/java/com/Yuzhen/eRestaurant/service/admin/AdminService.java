package com.Yuzhen.eRestaurant.service.admin;

import com.Yuzhen.eRestaurant.entity.AUser;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

public interface AdminService {
    String login(AUser aUser, HttpSession session, Model model);
}
