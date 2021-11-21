package com.Yuzhen.ExerciseOnline.service;

import com.Yuzhen.ExerciseOnline.entity.Knowledge;
import com.Yuzhen.ExerciseOnline.entity.Subject;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpSession;

public interface KnowledgeService {
    public String listSubject(HttpSession session, Model model);
    public String listKnowledge(HttpSession session, Model model, Integer id);
    public String showKnowledgeDetail(HttpSession session, Model model, Integer id);
    public String addSubject(Subject subject, HttpSession session, Model model);
    public String addKnowledge(Knowledge knowledge, HttpSession session, Model model);
    public String toModifySubject(Integer id, HttpSession session, Model model);
    public String toModifyKnowledge(Integer id, HttpSession session, Model model);
}
