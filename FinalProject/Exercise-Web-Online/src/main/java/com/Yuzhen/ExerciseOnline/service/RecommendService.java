package com.Yuzhen.ExerciseOnline.service;

import com.Yuzhen.ExerciseOnline.entity.Answer;
import com.Yuzhen.ExerciseOnline.entity.Exercise;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

public interface RecommendService {
    public String listLearningSubject(HttpSession session, Model model);

    public String knowledgeProgress(HttpSession session, Model model, Integer id);

    public String exerciseRecommend(HttpSession session, Model model, Integer id);
//    public String listKnowledge(HttpSession session, Model model, Integer id);
//
//    public String showExerciseList(HttpSession session, Model model, Integer knowledge_id);
//
//    public String toAddExercise(Exercise exercise, HttpSession session, Model model, Integer id);
//    public String addExercise(Exercise exercise, HttpSession session, Model model);
//
//    public String toModifyExercise(Integer id, HttpSession session, Model model);
//
//    public String modifyExercise(Exercise exercise, HttpSession session, Model model);
//
//    public String tryToSolveExercise(Answer answer, HttpSession session, Model model);
//
//    public String answerConclude(Integer id, HttpSession session, Model model);
//    public String reviewConclude(Integer id, HttpSession session, Model model);
//    public String toReviewAnswer(Integer id, HttpSession session, Model model);
//    public String reviewAnswer(Answer answer, HttpSession session, Model model);
//
//    String answerDetail(Integer id, HttpSession session, Model model);
}
