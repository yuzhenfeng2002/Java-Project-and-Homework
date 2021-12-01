package com.Yuzhen.eRestaurant.repository.admin;

import com.Yuzhen.eRestaurant.entity.Goods;
import com.Yuzhen.eRestaurant.entity.GoodsType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TypeRepository {
    int selectAll();

    List<GoodsType> selectAllTypeByPage(@Param("startIndex") int startIndex, @Param("perPageSize") int perPageSize);

    int deleteType(int id);

    List<Goods> selectGoods(int goodstype_id);

    int addType(GoodsType goodsType);
}
