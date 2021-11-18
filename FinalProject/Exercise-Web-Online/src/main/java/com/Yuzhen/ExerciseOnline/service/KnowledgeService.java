package com.Yuzhen.ExerciseOnline.service;

import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

public interface KnowledgeService {
    public String list(HttpSession session, Model model);
}
