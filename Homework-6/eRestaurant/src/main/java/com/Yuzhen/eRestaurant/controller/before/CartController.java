package com.Yuzhen.eRestaurant.controller.before;

import javax.servlet.http.HttpSession;

import com.Yuzhen.eRestaurant.entity.Goods;
import com.Yuzhen.eRestaurant.entity.Order;
import com.Yuzhen.eRestaurant.service.before.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/cart")
public class CartController{
	@Autowired
	private CartService cartService;
	@RequestMapping("/putCart")
	public String putCart(Goods goods, Model model, HttpSession session) {
		return cartService.putCart(goods, model, session);
	}
	@RequestMapping("/selectCart")
	public String selectCart(Model model, HttpSession session, String act) {
		return cartService.selectCart(model, session, act);
	}
	@RequestMapping("/deleteCart")
	public String deleteCart(HttpSession session, Integer gid) {
		return cartService.deleteCart(session, gid);
	}
	@RequestMapping("/clearCart")
	public String clearCart(HttpSession session) {
		return cartService.clearCart(session);
	}
	@RequestMapping("/submitOrder")
	public String submitOrder(Order order, Model model, HttpSession session) {
		return cartService.submitOrder(order, model, session);
	}
	@RequestMapping("/pay")
	@ResponseBody
	public String pay(@RequestBody Order order) {
		return cartService.pay(order);
	}
	@RequestMapping("/myOder")
	public String myOder(Model model, HttpSession session) {
		return cartService.myOder(model, session);
	}
	@RequestMapping("/orderDetail")
	public String orderDetail(Model model, Integer id) {
		return cartService.orderDetail(model, id);
	}
}
