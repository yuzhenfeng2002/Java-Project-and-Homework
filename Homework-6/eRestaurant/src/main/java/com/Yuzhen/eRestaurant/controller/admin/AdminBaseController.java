package com.Yuzhen.eRestaurant.controller.admin;
import javax.servlet.http.HttpSession;

import com.Yuzhen.eRestaurant.NoLoginException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class AdminBaseController {
	/**
	 * 权限控制
	 */
	@ModelAttribute  
    public void isLogin(HttpSession session) throws NoLoginException {
       if(session.getAttribute("auser") == null){  
            throw new NoLoginException("用户未登录！");
       }  
    } 
}