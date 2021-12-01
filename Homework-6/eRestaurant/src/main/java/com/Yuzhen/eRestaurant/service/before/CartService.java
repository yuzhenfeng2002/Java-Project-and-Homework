package com.Yuzhen.eRestaurant.service.before;

import com.Yuzhen.eRestaurant.entity.Goods;
import com.Yuzhen.eRestaurant.entity.Order;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

public interface CartService {
    String putCart(Goods goods, Model model, HttpSession session);

    String selectCart(Model model, HttpSession session, String act);

    String deleteCart(HttpSession session, Integer gid);

    String clearCart(HttpSession session);

    String submitOrder(Order order, Model model, HttpSession session);

    String pay(Order order);

    String myOder(Model model, HttpSession session);

    String orderDetail(Model model, Integer id);

    String finish(Order order);
}
