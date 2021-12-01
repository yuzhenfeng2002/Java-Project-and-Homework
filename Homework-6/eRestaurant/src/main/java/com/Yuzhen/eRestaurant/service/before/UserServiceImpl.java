package com.Yuzhen.eRestaurant.service.before;

import com.Yuzhen.eRestaurant.entity.BUser;
import com.Yuzhen.eRestaurant.repository.before.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public String isUse(BUser bUser) {
        if (userRepository.isUse(bUser).size() > 0) {
            return "no";
        }
        return "ok";
    }

    @Override
    public String login(BUser bUser, HttpSession session, Model model) {
        String rand = (String) session.getAttribute("rand");
        if (!rand.equalsIgnoreCase(bUser.getCode())) {
            model.addAttribute("errorMessage", "验证码错误！");
            return "user/login";
        }
        List<BUser> list = userRepository.login(bUser);
        if (list.size() > 0) {
            session.setAttribute("bUser", list.get(0));
            return "redirect:/"; // 到首页
        }
        model.addAttribute("errorMessage", "桌号错误！");
        return "user/login";
    }

}
