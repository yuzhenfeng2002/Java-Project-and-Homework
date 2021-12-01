package com.Yuzhen.eRestaurant.service.admin;

import com.Yuzhen.eRestaurant.entity.Goods;
import com.Yuzhen.eRestaurant.entity.GoodsType;
import com.Yuzhen.eRestaurant.repository.admin.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeRepository typeRepository;

    @Override
    public String selectAllTypeByPage(Model model, int currentPage) {
        // 共多少个类型
        int totalCount = typeRepository.selectAll();
        // 计算共多少页
        int pageSize = 15;
        int totalPage = (int) Math.ceil(totalCount * 1.0 / pageSize);
        List<GoodsType> typeByPage = typeRepository.selectAllTypeByPage((currentPage - 1) * pageSize, pageSize);
        model.addAttribute("allTypes", typeByPage);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("currentPage", currentPage);
        return "admin/selectGoodsType";
    }

    @Override
    public String delete(int id) {
        List<Goods> list = typeRepository.selectGoods(id);
        if (list.size() > 0) {
            // 该类型下有商品不允许删除
            return "no";
        } else {
            typeRepository.deleteType(id);
            // 删除后回到查询页面
            return "/type/selectAllTypeByPage?currentPage=1";
        }
    }

    @Override
    public String addType(GoodsType goodsType) {
        typeRepository.addType(goodsType);
        return "redirect:/type/selectAllTypeByPage?currentPage=1";
    }

}
