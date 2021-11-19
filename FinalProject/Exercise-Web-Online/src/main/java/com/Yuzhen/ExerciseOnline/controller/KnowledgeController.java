package com.Yuzhen.ExerciseOnline.controller;

import com.Yuzhen.ExerciseOnline.entity.Knowledge;
import com.Yuzhen.ExerciseOnline.entity.Subject;
import com.Yuzhen.ExerciseOnline.entity.User;
import com.Yuzhen.ExerciseOnline.service.KnowledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
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
        return knowledgeService.listSubject(session, model);
    }

    @RequestMapping("/subject")
    public String subjectDetail(HttpSession session, Model model, Integer id)
    {
        return knowledgeService.listKnowledge(session, model, id);
    }

    @RequestMapping("/subject/toAdd")
    public String toAddSubject(HttpSession session, Model model) {
        System.out.println(((User)session.getAttribute("user")).getUsertype());
        model.addAttribute("user", (User)session.getAttribute("user"));
        return "addSubject";
    }

    @RequestMapping("/detail/toAdd")
    public String toAddKnowledge(HttpSession session, Model model) {
        model.addAttribute("user", (User)session.getAttribute("user"));
        return "addKnowledge";
    }

    @RequestMapping("/subject/add")
    public String addSubject(@ModelAttribute("subject") @Validated Subject subject, BindingResult rs, HttpSession session, Model model)
    {
        if (rs.hasErrors()) // 验证失败
        {
            return "addSubject";
        }
        return knowledgeService.addSubject(subject, session, model);
    }

    @RequestMapping("/detail/add")
    public String addKnowledge(@ModelAttribute("knowledge") @Validated Knowledge knowledge, BindingResult rs, HttpSession session, Model model)
    {
        if (rs.hasErrors()) // 验证失败
        {
            return "addKnowledge";
        }
        return knowledgeService.addKnowledge(knowledge, session, model);
    }

    @RequestMapping("/detail")
    public String knowledgeDetail(HttpSession session, Model model, Integer id)
    {
        return knowledgeService.showKnowledgeDetail(session, model, id);
    }
}
