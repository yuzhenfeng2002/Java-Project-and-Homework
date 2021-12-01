package com.Yuzhen.eRestaurant.service.admin;

import com.Yuzhen.eRestaurant.entity.GoodsType;
import org.springframework.ui.Model;

public interface TypeService {
    String selectAllTypeByPage(Model model, int currentPage);

    String delete(int id);

    String addType(GoodsType goodsType);
}
