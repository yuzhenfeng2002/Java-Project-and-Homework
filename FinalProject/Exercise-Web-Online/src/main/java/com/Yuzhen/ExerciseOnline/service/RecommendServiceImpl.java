package com.Yuzhen.ExerciseOnline.service;

import com.Yuzhen.ExerciseOnline.auxiliary.Auxiliary;
import com.Yuzhen.ExerciseOnline.entity.*;
import com.Yuzhen.ExerciseOnline.repository.ExerciseRepository;
import com.Yuzhen.ExerciseOnline.repository.KnowledgeRepository;
import com.Yuzhen.ExerciseOnline.repository.RecommendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Service
public class RecommendServiceImpl implements RecommendService {
    @Autowired
    private KnowledgeRepository knowledgeRepository;
    @Autowired
    private ExerciseRepository exerciseRepository;
    @Autowired
    private RecommendRepository recommendRepository;


    @Override
    public String listLearningSubject(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", (User) session.getAttribute("user"));
        List<Subject> learningSubjects = recommendRepository.selectSubjectByUser(user);
        for (Subject subject: learningSubjects) {
            Integer count = recommendRepository.getSubjectNum(subject);
            double progress;
            if (count == 0)
            {
                progress = 100;
            }
            else {
                Integer sum = recommendRepository.getSubjectProgress(user, subject);
                progress = (double) (sum / count);
            }
            subject.setProgress(progress);
        }
        model.addAttribute("subjects", learningSubjects);
        return "homepage";
    }

    @Override
    public String knowledgeProgress(HttpSession session, Model model, Integer id) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", (User) session.getAttribute("user"));
        Subject subject = knowledgeRepository.selectSubject(id);
        List<Knowledge> knowledgeList = knowledgeRepository.listKnowledge(id);
        for (Knowledge knowledge: knowledgeList) {
            Integer count = recommendRepository.getKnowledgeNum(knowledge);
            double progress;
            if (count == 0)
            {
                progress = 100;
            }
            else {
                Integer sum;
                if (recommendRepository.getKnowledgeProgress(user, knowledge) == null)
                {
                    sum = 0;
                }
                else {
                    sum = recommendRepository.getKnowledgeProgress(user, knowledge);
                }
                progress = (double) (sum / count);
            }
            knowledge.setProgress(progress);
        }
        model.addAttribute("subject", subject);
        model.addAttribute("knowledgeList", knowledgeList);
        model.addAttribute("currentKnowledgeID", -1);
        model.addAttribute("currentTitle", subject.getName());
        return "subjectProgress";
    }
}
