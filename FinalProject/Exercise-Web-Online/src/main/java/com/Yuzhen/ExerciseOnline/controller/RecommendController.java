package com.Yuzhen.ExerciseOnline.controller;

import com.Yuzhen.ExerciseOnline.entity.Answer;
import com.Yuzhen.ExerciseOnline.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class RecommendController extends AuthorityController {
    @Autowired
    private RecommendService recommendService;

    @RequestMapping("/index")
    public String index(HttpSession session, Model model) {
        return recommendService.listLearningSubject(session, model);
    }

    @RequestMapping("subject/progress")
    public String knowledgeProgress(HttpSession session, Model model, Integer id) {
        return recommendService.knowledgeProgress(session, model, id);
    }

    @RequestMapping("exercise/recommend")
    public String exerciseRecommend(@ModelAttribute("myAnswer") @Validated Answer answer, HttpSession session, Model model, Integer id) {
        return recommendService.exerciseRecommend(session, model, id);
    }

}
