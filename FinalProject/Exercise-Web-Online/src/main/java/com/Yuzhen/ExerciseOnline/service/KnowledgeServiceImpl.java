package com.Yuzhen.ExerciseOnline.service;

import com.Yuzhen.ExerciseOnline.entity.Knowledge;
import com.Yuzhen.ExerciseOnline.entity.User;
import com.Yuzhen.ExerciseOnline.repository.KnowledgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

@Service
public class KnowledgeServiceImpl implements KnowledgeService {
    @Autowired
    private KnowledgeRepository knowledgeRepository;
    @Override
    public String list(HttpSession session, Model model) {
        model.addAttribute("username", ((User)session.getAttribute("user")).getUsername());
        System.out.println(session.getAttributeNames());
        return "index";
    }
}
