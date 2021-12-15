package com.Yuzhen.ExerciseOnline.repository;

import com.Yuzhen.ExerciseOnline.entity.Knowledge;
import com.Yuzhen.ExerciseOnline.entity.Subject;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface KnowledgeRepository {
    List<Subject> listSubject();

    Subject selectSubject(Integer id);

    Subject selectSubjectByName(String name);

    List<Knowledge> listKnowledge(Integer id);

    Knowledge selectKnowledge(Integer id);

    Knowledge selectKnowledgeByName(Integer subject_id, String name);

    List<Subject> isSubject(String name);

    List<Subject> isKnowledge(Integer subjectID, String title);

    int addSubject(Subject subject);

    int addKnowledge(Knowledge knowledge);

    int modifySubject(Subject subject);

    int modifyKnowledge(Knowledge knowledge);

    int addDependency(Integer id, Integer dep_id);

    void dropDependency(Integer id);
}
