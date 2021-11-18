package com.Yuzhen.ExerciseOnline.controller;

import com.Yuzhen.ExerciseOnline.service.KnowledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/knowledge")
public class KnowledgeController extends AuthorityController{
    @Autowired
    private KnowledgeService knowledgeService;

    @RequestMapping("/list")
    public String knowledgeList(HttpSession session, Model model)
    {
        return knowledgeService.list(session, model);
    }

}
