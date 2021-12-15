package com.Yuzhen.ExerciseOnline.service;

import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

public interface RecommendService {
    String listLearningSubject(HttpSession session, Model model);

    String knowledgeProgress(HttpSession session, Model model, Integer id);

    String exerciseRecommend(HttpSession session, Model model, Integer id);

}
