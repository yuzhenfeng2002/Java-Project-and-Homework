package com.Yuzhen.eRestaurant.service.admin;

import com.Yuzhen.eRestaurant.entity.AUser;
import com.Yuzhen.eRestaurant.repository.admin.AdminRepository;
import com.Yuzhen.eRestaurant.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRepository adminRepository;

    @Override
    public String login(AUser aUser, HttpSession session, Model model) {
        aUser.setApwd(MD5Util.MD5(aUser.getApwd()));
        List<AUser> list = adminRepository.login(aUser);
        if (list.size() > 0) { // 登录成功
            session.setAttribute("auser", aUser);
            return "forward:/goods/selectAllGoodsByPage?currentPage=1&act=select";
        } else { // 登录失败
            model.addAttribute("errorMessage", "用户名或密码错误！");
            return "admin/login";
        }
    }
}
