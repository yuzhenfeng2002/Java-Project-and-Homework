package com.Yuzhen.ExerciseOnline.repository;

import com.Yuzhen.ExerciseOnline.entity.Knowledge;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface KnowledgeRepository {
    public List<Knowledge> list();
}
