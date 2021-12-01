package com.Yuzhen.eRestaurant.service.admin;

import com.Yuzhen.eRestaurant.entity.BUser;
import com.Yuzhen.eRestaurant.entity.Order;
import com.Yuzhen.eRestaurant.repository.admin.UserAndOrderAndOutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Map;

@Service
public class UserAndOrderAndOutServiceImpl implements UserAndOrderAndOutService {
    @Autowired
    private UserAndOrderAndOutRepository userAndOrderAndOutRepository;

    @Override
    public String selectUser(Model model, int currentPage) {
        // 共多少个用户
        int totalCount = userAndOrderAndOutRepository.selectAllUser();
        // 计算共多少页
        int pageSize = 15;
        int totalPage = (int) Math.ceil(totalCount * 1.0 / pageSize);
        List<BUser> typeByPage = userAndOrderAndOutRepository.selectUserByPage((currentPage - 1) * pageSize, pageSize);
        model.addAttribute("allUsers", typeByPage);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("currentPage", currentPage);
        return "admin/allUser";
    }

    @Override
    public String deleteUser(Model model, int id) {
        if (userAndOrderAndOutRepository.selectCartUser(id).size() > 0
                || userAndOrderAndOutRepository.selectOrderUser(id).size() > 0) {
            return "no";
        } else {
            userAndOrderAndOutRepository.deleteUser(id);
            return "/selectUser?currentPage=1";
        }
    }

    @Override
    public String selectOrder(Model model, int currentPage) {
        // 共多少个订单
        int totalCount = userAndOrderAndOutRepository.selectAllOrder();
        // 计算共多少页
        int pageSize = 15;
        int totalPage = (int) Math.ceil(totalCount * 1.0 / pageSize);
        List<Map<String, Object>> orderByPage = userAndOrderAndOutRepository.selectOrderByPage((currentPage - 1) * pageSize, pageSize);
        model.addAttribute("allOrders", orderByPage);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("currentPage", currentPage);
        return "admin/allOrder";
    }

    @Override
    public String finish(Order order) {
        userAndOrderAndOutRepository.finish(order.getId());
        return "ok";
    }
}
