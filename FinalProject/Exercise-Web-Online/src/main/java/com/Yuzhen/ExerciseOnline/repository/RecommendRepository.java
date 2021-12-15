package com.Yuzhen.ExerciseOnline.repository;

import com.Yuzhen.ExerciseOnline.entity.Exercise;
import com.Yuzhen.ExerciseOnline.entity.Knowledge;
import com.Yuzhen.ExerciseOnline.entity.Subject;
import com.Yuzhen.ExerciseOnline.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RecommendRepository {
    List<Subject> selectSubjectByUser(User user);

    Integer getSubjectProgress(@Param("user") User user, @Param("subject") Subject subject);

    Integer getSubjectNum(@Param("subject") Subject subject);

    Integer getKnowledgeProgress(@Param("user") User user, @Param("knowledge") Knowledge knowledge);

    Integer getKnowledgeNum(@Param("knowledge") Knowledge knowledge);

    List<Integer> getDependencies(Knowledge knowledge);

    List<Exercise> getRecommendedExercise(@Param("user") User user, @Param("knowledge") Knowledge knowledge, @Param("limit") Integer limit);

}
