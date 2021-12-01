package com.Yuzhen.eRestaurant.repository.before;

import com.Yuzhen.eRestaurant.entity.BUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserRepository {
    List<BUser> isUse(BUser bUser);

    List<BUser> login(BUser bUser);
}
