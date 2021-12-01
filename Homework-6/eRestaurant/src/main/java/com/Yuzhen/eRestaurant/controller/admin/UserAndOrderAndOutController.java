package com.Yuzhen.eRestaurant.controller.admin;

import javax.servlet.http.HttpSession;

import com.Yuzhen.eRestaurant.entity.AUser;
import com.Yuzhen.eRestaurant.entity.Order;
import com.Yuzhen.eRestaurant.service.admin.UserAndOrderAndOutService;
import com.Yuzhen.eRestaurant.service.before.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserAndOrderAndOutController extends AdminBaseController{
	@Autowired
	private UserAndOrderAndOutService userAndOrderAndOutService;
	@RequestMapping("/loginOut")
	public String loginOut(@ModelAttribute("aUser") AUser aUser, HttpSession session) {
		session.invalidate();
		return "admin/login";
	}
	@RequestMapping("/selectUser")
	public String selectUser(Model model, int currentPage) {
		return userAndOrderAndOutService.selectUser(model, currentPage);
	}
	@RequestMapping("/deleteUser")
	@ResponseBody
	public String deleteUser(Model model, int id) {
		return userAndOrderAndOutService.deleteUser(model, id);
	}
	@RequestMapping("/selectOrder")
	public String selectOrder(Model model, int currentPage) {
		return userAndOrderAndOutService.selectOrder(model, currentPage);
	}
	@RequestMapping("/finish")
	@ResponseBody
	public String finish(@RequestBody Order order) {
		return userAndOrderAndOutService.finish(order);
	}
}
