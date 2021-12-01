package com.Yuzhen.eRestaurant.repository.before;

import com.Yuzhen.eRestaurant.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface CartRepository {
    int putCart(@Param("uid") Integer uid,
                @Param("gid") Integer gid,
                @Param("bnum") Integer bnum);

    List<Map<String, Object>> isPutCart(@Param("uid") Integer uid, @Param("gid") Integer gid);

    int updateCart(@Param("uid") Integer uid,
                   @Param("gid") Integer gid,
                   @Param("bnum") Integer bnum);

    List<Map<String, Object>> selectCart(Integer uid);

    int deleteAgoods(@Param("uid") Integer uid, @Param("gid") Integer gid);

    int clear(Integer uid);

    int addOrder(Order order);

    int addOrderDetail(@Param("ordersn") Integer ordersn, @Param("uid") Integer uid);

    List<Map<String, Object>> selectGoodsShop(Integer uid);

    int updateStore(Map<String, Object> map);

    int pay(Integer ordersn);

    int finish(Integer ordersn);

    List<Map<String, Object>> myOrder(Integer uid);

    List<Map<String, Object>> orderDetail(Integer id);
}
