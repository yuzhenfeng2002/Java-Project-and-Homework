package com.Yuzhen.ExerciseOnline.repository;

import com.Yuzhen.ExerciseOnline.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RecommendRepository {
    public List<Subject> selectSubjectByUser(User user);
    public Integer getSubjectProgress(@Param("user") User user, @Param("subject") Subject subject);
    public Integer getSubjectNum(@Param("subject") Subject subject);
    public Integer getKnowledgeProgress(@Param("user") User user, @Param("knowledge") Knowledge knowledge);
    public Integer getKnowledgeNum(@Param("knowledge") Knowledge knowledge);

//    public Exercise selectExerciseByID(Integer id);
//
//    public List<Exercise> selectExercise(Integer knowledge_id);
//
//    public int addExercise(Exercise exercise);
//
//    public int modifyExercise(Exercise exercise);
//
//    public int recordSolutionHistory(Answer answer);
//
//    public List<Answer> selectAnswerByUser(@Param("email") String email, @Param("id") Integer id);
//
//    public Answer selectAnswerByID(Integer id);
//
//    public List<Answer> selectAnswerByExerciseID(Integer id);
//
//    public int modifyAnswer(Answer answer);
}
