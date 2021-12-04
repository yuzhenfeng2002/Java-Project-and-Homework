package com.Yuzhen.ExerciseOnline.repository;

import com.Yuzhen.ExerciseOnline.entity.Exercise;
import com.Yuzhen.ExerciseOnline.entity.Knowledge;
import com.Yuzhen.ExerciseOnline.entity.Subject;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ExerciseRepository {
    public List<Subject> listSubject();
    public Subject selectSubject(Integer id);
    public Subject selectSubjectByName(String name);
    public List<Knowledge> listKnowledge(Integer id);
    public Exercise selectExerciseByID(Integer id);
    public List<Exercise> selectExercise(Integer knowledge_id);
    public List<Subject> isSubject(String name);
    public List<Subject> isKnowledge(Integer subjectID, String title);
    public int addSubject(Subject subject);
    public int addExercise(Exercise exercise);
    public int modifySubject(Subject subject);
    public int modifyExercise(Exercise exercise);
}
