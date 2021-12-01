package com.Yuzhen.eRestaurant.repository.admin;

import com.Yuzhen.eRestaurant.entity.AUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminRepository {
    List<AUser> login(AUser aUser);
}
