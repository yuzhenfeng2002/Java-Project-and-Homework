package com.Yuzhen.eRestaurant.service.admin;

import com.Yuzhen.eRestaurant.entity.Goods;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface GoodsService {
    String selectAllGoodsByPage(Model model, int currentPage, String act);

    String addGoods(Goods goods, HttpServletRequest request, String act) throws IllegalStateException, IOException;

    String toAddGoods(Goods goods, Model model);

    String detail(Model model, Integer id, String act);

    String delete(Integer id);
}
