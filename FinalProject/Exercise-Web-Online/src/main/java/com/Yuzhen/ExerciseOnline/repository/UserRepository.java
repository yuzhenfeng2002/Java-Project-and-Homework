package com.Yuzhen.ExerciseOnline.repository;

import org.apache.ibatis.annotations.Mapper;

import com.Yuzhen.ExerciseOnline.entity.User;

import java.util.List;

@Mapper
public interface UserRepository {
    public List<User> login(User user);

    public int register(User user);

    public List<User> isUser(User user);
}
