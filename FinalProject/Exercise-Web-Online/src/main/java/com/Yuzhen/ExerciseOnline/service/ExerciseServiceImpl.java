package com.Yuzhen.ExerciseOnline.service;

import com.Yuzhen.ExerciseOnline.auxiliary.Auxiliary;
import com.Yuzhen.ExerciseOnline.entity.Exercise;
import com.Yuzhen.ExerciseOnline.entity.Knowledge;
import com.Yuzhen.ExerciseOnline.entity.Subject;
import com.Yuzhen.ExerciseOnline.entity.User;
import com.Yuzhen.ExerciseOnline.repository.ExerciseRepository;
import com.Yuzhen.ExerciseOnline.repository.KnowledgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class ExerciseServiceImpl implements ExerciseService {
    @Autowired
    private KnowledgeRepository knowledgeRepository;
    @Autowired
    private ExerciseRepository exerciseRepository;

    @Override
    public String listKnowledge(HttpSession session, Model model, Integer id) {
        Subject subject = knowledgeRepository.selectSubject(id);
        List<Knowledge> knowledgeList = knowledgeRepository.listKnowledge(id);
        model.addAttribute("user", ((User)session.getAttribute("user")));
        model.addAttribute("subject", subject);
        model.addAttribute("knowledgeList", knowledgeList);
        model.addAttribute("currentKnowledgeID", -1);
        model.addAttribute("currentTitle", subject.getName());
        model.addAttribute("content", Auxiliary.modifyContent(subject.getIntroduction()));
        return "exercise";
    }

    @Override
    public String showExerciseList(HttpSession session, Model model, Integer knowledge_id) {
        Knowledge knowledge = knowledgeRepository.selectKnowledge(knowledge_id);
        Subject subject = knowledgeRepository.selectSubject(knowledge.getSubject_id());
        List<Knowledge> knowledgeList = knowledgeRepository.listKnowledge(knowledge.getSubject_id());
        List<Exercise> exerciseList = exerciseRepository.selectExercise(knowledge_id);
        model.addAttribute("user", ((User)session.getAttribute("user")));
        model.addAttribute("subject", subject);
        model.addAttribute("knowledgeList", knowledgeList);
        model.addAttribute("currentKnowledgeID", knowledge_id);
        model.addAttribute("currentTitle", knowledge.getTitle());
        model.addAttribute("exercises", exerciseList);
        return "exercise";
    }

    @Override
    public String addExercise(Exercise exercise, HttpSession session, Model model) {
        Subject subject = knowledgeRepository.selectSubjectByName(exercise.getSubject_name());
        if (subject == null)
        {
            model.addAttribute("errorMessage", "课程不存在，请前往课程页面确认！");
            model.addAttribute("user", (User)session.getAttribute("user"));
            return "addExercise";
        }
        Knowledge knowledge = knowledgeRepository.selectKnowledgeByName(subject.getId(), exercise.getKnowledge_name());
        if (knowledge == null) {
            model.addAttribute("errorMessage", "知识点不存在，请前往相关课程页面确认！");
            model.addAttribute("user", (User)session.getAttribute("user"));
            return "addExercise";
        }
        else {
            exercise.setKnowledge_id(knowledge.getId());
            // knowledge.setContent(Auxiliary.modifyContent(knowledge.getContent()));
            if (exerciseRepository == null)
                System.out.println(1);
            exerciseRepository.addExercise(exercise);
        }
        return ("redirect:/knowledge/list");
    }

    @Override
    public String toModifyExercise(Integer id, HttpSession session, Model model) {
        Exercise exercise = exerciseRepository.selectExerciseByID(id);
        Knowledge knowledge = knowledgeRepository.selectKnowledge(exercise.getKnowledge_id());
        Subject subject = knowledgeRepository.selectSubject(knowledge.getSubject_id());
        List<Knowledge> knowledgeList = knowledgeRepository.listKnowledge(knowledge.getSubject_id());
        model.addAttribute("user", ((User)session.getAttribute("user")));
        model.addAttribute("knowledgeList", knowledgeList);
        model.addAttribute("currentKnowledgeID", id);
        model.addAttribute("originKnowledge", knowledge);
        model.addAttribute("originSubject", subject);
        exercise.setSubject_name(subject.getName());
        exercise.setKnowledge_name(knowledge.getTitle());
        model.addAttribute("originExercise", exercise);
        return "modifyExercise";
    }

    @Override
    public String modifyExercise(Exercise exercise, HttpSession session, Model model) {
        Exercise originExercise = exerciseRepository.selectExerciseByID(exercise.getId());
        exerciseRepository.modifyExercise(exercise);
        return ("redirect:/exercise/list?id=" + (exercise.getId()));
    }
}
