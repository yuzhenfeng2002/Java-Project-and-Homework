package com.Yuzhen.ExerciseOnline.service;

import com.Yuzhen.ExerciseOnline.entity.Knowledge;
import com.Yuzhen.ExerciseOnline.entity.Subject;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

public interface KnowledgeService {
    String listSubject(HttpSession session, Model model);

    String listKnowledge(HttpSession session, Model model, Integer id);

    String showKnowledgeDetail(HttpSession session, Model model, Integer id);

    String addSubject(Subject subject, HttpSession session, Model model);

    String addKnowledge(Knowledge knowledge, HttpSession session, Model model);

    String toAddKnowledge(Integer id, HttpSession session, Model model, Knowledge knowledge);

    String toModifySubject(Integer id, HttpSession session, Model model);

    String toModifyKnowledge(Integer id, HttpSession session, Model model);

    String modifySubject(Subject subject, HttpSession session, Model model);

    String modifyKnowledge(Knowledge knowledge, HttpSession session, Model model);
}
