package com.Yuzhen.eRestaurant.service.admin;

import com.Yuzhen.eRestaurant.entity.Order;
import org.springframework.ui.Model;

public interface UserAndOrderAndOutService {
    String selectUser(Model model, int currentPage);

    String deleteUser(Model model, int id);

    String selectOrder(Model model, int currentPage);

    String finish(Order order);
}
