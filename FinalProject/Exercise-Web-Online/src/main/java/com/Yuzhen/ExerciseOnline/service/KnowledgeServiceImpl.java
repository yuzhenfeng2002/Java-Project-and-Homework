package com.Yuzhen.ExerciseOnline.service;

import com.Yuzhen.ExerciseOnline.auxiliary.Auxiliary;
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
        for (Subject subject : subjects) {
            subject.setIntroduction(Auxiliary.modifyContent(subject.getIntroduction()));
        }
        model.addAttribute("user", ((User) session.getAttribute("user")));
        model.addAttribute("subjects", subjects);
        return "index";
    }

    @Override
    public String listKnowledge(HttpSession session, Model model, Integer id) {
        Subject subject = knowledgeRepository.selectSubject(id);
        List<Knowledge> knowledgeList = knowledgeRepository.listKnowledge(id);
        model.addAttribute("user", ((User) session.getAttribute("user")));
        model.addAttribute("subject", subject);
        model.addAttribute("knowledgeList", knowledgeList);
        model.addAttribute("currentKnowledgeID", -1);
        model.addAttribute("currentTitle", subject.getName());
        model.addAttribute("content", Auxiliary.modifyContent(subject.getIntroduction()));
        return "knowledge";
    }

    @Override
    public String showKnowledgeDetail(HttpSession session, Model model, Integer id) {
        Knowledge knowledge = knowledgeRepository.selectKnowledge(id);
        Subject subject = knowledgeRepository.selectSubject(knowledge.getSubject_id());
        List<Knowledge> knowledgeList = knowledgeRepository.listKnowledge(knowledge.getSubject_id());
        model.addAttribute("user", ((User) session.getAttribute("user")));
        model.addAttribute("subject", subject);
        model.addAttribute("knowledgeList", knowledgeList);
        model.addAttribute("currentKnowledgeID", id);
        model.addAttribute("currentTitle", knowledge.getTitle());
        model.addAttribute("content", Auxiliary.modifyContent(knowledge.getContent()));
        return "knowledge";
    }

    @Override
    public String toAddKnowledge(Integer id, HttpSession session, Model model, Knowledge knowledge) {
        Subject subject = knowledgeRepository.selectSubject(id);
        List<Knowledge> knowledgeList = knowledgeRepository.listKnowledge(id);
        knowledge.setSubject_name(subject.getName());
        knowledge.setSubject_id(subject.getId());
        model.addAttribute("user", ((User) session.getAttribute("user")));
        model.addAttribute("knowledgeList", knowledgeList);
        model.addAttribute("subject", subject);
        return "addKnowledge";
    }

    @Override
    public String addSubject(Subject subject, HttpSession session, Model model) {
        if (knowledgeRepository.isSubject(subject.getName()).size() > 0) {
            model.addAttribute("errorMessage", "课程已存在，请前往相关课程页面查看！");
            model.addAttribute("user", (User) session.getAttribute("user"));
            return "addSubject";
        } else {
            // subject.setIntroduction(Auxiliary.modifyContent(subject.getIntroduction()));
            knowledgeRepository.addSubject(subject);
        }
        return "redirect:/knowledge/list";
    }

    @Override
    public String addKnowledge(Knowledge knowledge, HttpSession session, Model model) {
        Subject subject = knowledgeRepository.selectSubjectByName(knowledge.getSubject_name());
        if (subject == null) {
            model.addAttribute("errorMessage", "课程不存在，请前往课程页面确认！");
            model.addAttribute("user", (User) session.getAttribute("user"));
            return "addKnowledge";
        } else if (knowledgeRepository.isKnowledge(subject.getId(), knowledge.getTitle()).size() > 0) {
            model.addAttribute("errorMessage", "知识点已存在，请前往相关课程页面查看！");
            model.addAttribute("user", (User) session.getAttribute("user"));
            return "addKnowledge";
        } else {
            knowledge.setSubject_id(subject.getId());
            // knowledge.setContent(Auxiliary.modifyContent(knowledge.getContent()));
            knowledgeRepository.addKnowledge(knowledge);
            Knowledge addedKnowledge = knowledgeRepository.selectKnowledgeByName(knowledge.getSubject_id(), knowledge.getTitle());
            for (Integer dependent_id: knowledge.getDependency()) {
                if (dependent_id != -1)
                    knowledgeRepository.addDependency(addedKnowledge.getId(), dependent_id);
            }
        }
        return ("redirect:/subject/progress?id=" + (subject.getId()));
    }

    @Override
    public String toModifySubject(Integer id, HttpSession session, Model model) {
        Subject subject = knowledgeRepository.selectSubject(id);
        List<Knowledge> knowledgeList = knowledgeRepository.listKnowledge(id);
        model.addAttribute("user", ((User) session.getAttribute("user")));
        model.addAttribute("knowledgeList", knowledgeList);
        model.addAttribute("currentKnowledgeID", -1);
        model.addAttribute("originSubject", subject);
        return "modifySubject";
    }

    @Override
    public String toModifyKnowledge(Integer id, HttpSession session, Model model) {
        Knowledge knowledge = knowledgeRepository.selectKnowledge(id);
        Subject subject = knowledgeRepository.selectSubject(knowledge.getSubject_id());
        List<Knowledge> knowledgeList = knowledgeRepository.listKnowledge(knowledge.getSubject_id());
        model.addAttribute("user", ((User) session.getAttribute("user")));
        model.addAttribute("knowledgeList", knowledgeList);
        model.addAttribute("currentKnowledgeID", id);
        model.addAttribute("originKnowledge", knowledge);
        model.addAttribute("originSubject", subject);
        return "modifyKnowledge";
    }

    @Override
    public String modifySubject(Subject subject, HttpSession session, Model model) {
        // subject.setIntroduction(Auxiliary.modifyContent(subject.getIntroduction()));
        knowledgeRepository.modifySubject(subject);
        return ("redirect:/knowledge/subject?id=" + (subject.getId()));
    }

    @Override
    public String modifyKnowledge(Knowledge knowledge, HttpSession session, Model model) {
        Knowledge originKnowledge = knowledgeRepository.selectKnowledge(knowledge.getId());
        knowledgeRepository.modifyKnowledge(knowledge);
        return ("redirect:/knowledge/detail?id=" + (knowledge.getId()));
    }
}
