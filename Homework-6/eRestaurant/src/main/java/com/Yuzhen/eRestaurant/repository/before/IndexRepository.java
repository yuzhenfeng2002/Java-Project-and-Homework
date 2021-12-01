package com.Yuzhen.eRestaurant.repository.before;

import com.Yuzhen.eRestaurant.entity.Goods;
import com.Yuzhen.eRestaurant.entity.GoodsType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IndexRepository {
    List<Goods> selectAdvertisementGoods();

    List<GoodsType> selectGoodsType();

    List<Goods> selectRecommendGoods(Integer tid);

    List<Goods> selectLastedGoods(Integer tid);

    Goods selectAGoods(Integer id);

    List<Goods> search(String mykey);
}
