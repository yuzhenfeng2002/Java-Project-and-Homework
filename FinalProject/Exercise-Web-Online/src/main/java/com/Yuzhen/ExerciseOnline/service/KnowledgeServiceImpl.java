package com.Yuzhen.ExerciseOnline.service;

import com.Yuzhen.ExerciseOnline.entity.Knowledge;
import com.Yuzhen.ExerciseOnline.entity.Subject;
import com.Yuzhen.ExerciseOnline.entity.User;
import com.Yuzhen.ExerciseOnline.repository.KnowledgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

@Service
public class KnowledgeServiceImpl implements KnowledgeService {
    @Autowired
    private KnowledgeRepository knowledgeRepository;
    @Override
    public String listSubject(HttpSession session, Model model) {
        List<Subject> subjects = knowledgeRepository.listSubject();
        model.addAttribute("user", ((User)session.getAttribute("user")));
        model.addAttribute("subjects", subjects);
        return "index";
    }

    @Override
    public String listKnowledge(HttpSession session, Model model, Integer id) {
        Subject subject = knowledgeRepository.selectSubject(id);
        List<Knowledge> knowledgeList = knowledgeRepository.listKnowledge(id);
        model.addAttribute("user", ((User)session.getAttribute("user")));
        model.addAttribute("subject", subject);
        model.addAttribute("knowledgeList", knowledgeList);
        model.addAttribute("currentKnowledgeID", -1);
        model.addAttribute("currentTitle", subject.getName());
        model.addAttribute("content", subject.getIntroduction());
        return "knowledge";
    }

    @Override
    public String showKnowledgeDetail(HttpSession session, Model model, Integer id) {
        Knowledge knowledge = knowledgeRepository.selectKnowledge(id);
        Subject subject = knowledgeRepository.selectSubject(knowledge.getSubject_id());
        List<Knowledge> knowledgeList = knowledgeRepository.listKnowledge(knowledge.getSubject_id());
        model.addAttribute("user", ((User)session.getAttribute("user")));
        model.addAttribute("subject", subject);
        model.addAttribute("knowledgeList", knowledgeList);
        model.addAttribute("currentKnowledgeID", id);
        model.addAttribute("currentTitle", knowledge.getTitle());
        model.addAttribute("content", knowledge.getContent());
        return "knowledge";
    }

    @Override
    public String addSubject(Subject subject, HttpSession session, Model model) {
        if (knowledgeRepository.isSubject(subject.getName()).size() > 0) {
            model.addAttribute("errorMessage", "课程已存在，请前往相关课程页面查看！");
        }
        else {
            knowledgeRepository.addSubject(subject);
        }
        return "redirect:/knowledge/list";
    }

    @Override
    public String addKnowledge(Knowledge knowledge, HttpSession session, Model model) {
        if (knowledgeRepository.isSubject(knowledge.getTitle()).size() > 0) {
            model.addAttribute("errorMessage", "知识点已存在，请前往相关课程页面查看！");
        }
        else {
            knowledgeRepository.addKnowledge(knowledge);
        }
        return "redirect:/knowledge/list";
    }
}
