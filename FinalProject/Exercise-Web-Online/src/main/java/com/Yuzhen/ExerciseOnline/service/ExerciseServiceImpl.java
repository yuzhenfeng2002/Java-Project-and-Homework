package com.Yuzhen.ExerciseOnline.service;

import com.Yuzhen.ExerciseOnline.auxiliary.Auxiliary;
import com.Yuzhen.ExerciseOnline.entity.*;
import com.Yuzhen.ExerciseOnline.repository.ExerciseRepository;
import com.Yuzhen.ExerciseOnline.repository.KnowledgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        model.addAttribute("user", ((User) session.getAttribute("user")));
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
        model.addAttribute("user", ((User) session.getAttribute("user")));
        model.addAttribute("subject", subject);
        model.addAttribute("knowledgeList", knowledgeList);
        model.addAttribute("currentKnowledgeID", knowledge_id);
        model.addAttribute("currentTitle", knowledge.getTitle());
        for (Exercise exercise: exerciseList){
            if (exercise.getType() == 1) {
                Map<String, Object> result = Auxiliary.modifyRatioExercise(exercise.getContent());
                String modifiedContent = (String) result.get("modified_str");
                Integer opt_num = (Integer) result.get("opt_num");
                exercise.setContent(modifiedContent);
                exercise.setOptNum(opt_num);
            }
        }
        model.addAttribute("exercises", exerciseList);
        return "exercise";
    }

    @Override
    public String toAddExercise(Exercise exercise, HttpSession session, Model model, Integer id) {
        Knowledge knowledge = knowledgeRepository.selectKnowledge(id);
        Subject subject = knowledgeRepository.selectSubject(knowledge.getSubject_id());
        model.addAttribute("user", ((User) session.getAttribute("user")));
        model.addAttribute("originKnowledge", knowledge);
        model.addAttribute("originSubject", subject);
        exercise.setSubject_name(subject.getName());
        exercise.setKnowledge_name(knowledge.getTitle());
        return "addExercise";
    }

    @Override
    public String addExercise(Exercise exercise, HttpSession session, Model model) {
        Subject subject = knowledgeRepository.selectSubjectByName(exercise.getSubject_name());
        if (subject == null) {
            model.addAttribute("errorMessage", "课程不存在，请前往课程页面确认！");
            model.addAttribute("user", (User) session.getAttribute("user"));
            return "addExercise";
        }
        Knowledge knowledge = knowledgeRepository.selectKnowledgeByName(subject.getId(), exercise.getKnowledge_name());
        if (knowledge == null) {
            model.addAttribute("errorMessage", "知识点不存在，请前往相关课程页面确认！");
            model.addAttribute("user", (User) session.getAttribute("user"));
            return "addExercise";
        } else {
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
        model.addAttribute("user", ((User) session.getAttribute("user")));
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
        return ("redirect:/exercise/list?id=" + (originExercise.getKnowledge_id()));
    }

    @Override
    public String tryToSolveExercise(Answer answer, HttpSession session, Model model) {
        exerciseRepository.recordSolutionHistory(answer);
        Exercise exercise = exerciseRepository.selectExerciseByID(answer.getExercise_id());
        return ("redirect:/exercise/list?id=" + (exercise.getKnowledge_id()));
    }

    @Override
    public String answerConclude(Integer id, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        List<Answer> answerList = exerciseRepository.selectAnswerByUser(user.getEmail(), id);
        Exercise exercise = exerciseRepository.selectExerciseByID(id);
        Knowledge knowledge = knowledgeRepository.selectKnowledge(exercise.getKnowledge_id());
        Subject subject = knowledgeRepository.selectSubject(knowledge.getSubject_id());
        List<Knowledge> knowledgeList = knowledgeRepository.listKnowledge(knowledge.getSubject_id());
        model.addAttribute("user", ((User) session.getAttribute("user")));
        model.addAttribute("knowledgeList", knowledgeList);
        model.addAttribute("currentKnowledgeID", id);
        model.addAttribute("originKnowledge", knowledge);
        model.addAttribute("originSubject", subject);
        exercise.setSubject_name(subject.getName());
        exercise.setKnowledge_name(knowledge.getTitle());
        if (exercise.getType() == 1) {
            Map<String, Object> result = Auxiliary.modifyRatioExercise(exercise.getContent());
            String modifiedContent = (String) result.get("modified_str");
            Integer opt_num = (Integer) result.get("opt_num");
            exercise.setContent(modifiedContent);
            exercise.setOptNum(opt_num);
        }
        model.addAttribute("originExercise", exercise);
        model.addAttribute("answerList", answerList);
        return "answerConclude";
    }

    @Override
    public String reviewConclude(Integer id, HttpSession session, Model model) {
        List<Answer> answerList = exerciseRepository.selectAnswerByExerciseID(id);
        Exercise exercise = exerciseRepository.selectExerciseByID(id);
        Knowledge knowledge = knowledgeRepository.selectKnowledge(exercise.getKnowledge_id());
        Subject subject = knowledgeRepository.selectSubject(knowledge.getSubject_id());
        List<Knowledge> knowledgeList = knowledgeRepository.listKnowledge(knowledge.getSubject_id());
        model.addAttribute("user", ((User) session.getAttribute("user")));
        model.addAttribute("knowledgeList", knowledgeList);
        model.addAttribute("currentKnowledgeID", id);
        model.addAttribute("originKnowledge", knowledge);
        model.addAttribute("originSubject", subject);
        exercise.setSubject_name(subject.getName());
        exercise.setKnowledge_name(knowledge.getTitle());
        if (exercise.getType() == 1) {
            Map<String, Object> result = Auxiliary.modifyRatioExercise(exercise.getContent());
            String modifiedContent = (String) result.get("modified_str");
            Integer opt_num = (Integer) result.get("opt_num");
            exercise.setContent(modifiedContent);
            exercise.setOptNum(opt_num);
        }
        model.addAttribute("originExercise", exercise);
        model.addAttribute("answerList", answerList);
        return "reviewConclude";
    }

    @Override
    public String answerDetail(Integer id, HttpSession session, Model model) {
        Answer answer = exerciseRepository.selectAnswerByID(id);
        Exercise exercise = exerciseRepository.selectExerciseByID(answer.getExercise_id());
        Knowledge knowledge = knowledgeRepository.selectKnowledge(exercise.getKnowledge_id());
        Subject subject = knowledgeRepository.selectSubject(knowledge.getSubject_id());
        List<Knowledge> knowledgeList = knowledgeRepository.listKnowledge(knowledge.getSubject_id());
        model.addAttribute("user", ((User) session.getAttribute("user")));
        model.addAttribute("knowledgeList", knowledgeList);
        model.addAttribute("currentKnowledgeID", id);
        model.addAttribute("originKnowledge", knowledge);
        model.addAttribute("originSubject", subject);
        exercise.setSubject_name(subject.getName());
        exercise.setKnowledge_name(knowledge.getTitle());
        model.addAttribute("originExercise", exercise);
        model.addAttribute("originAnswer", answer);
        return "answerDetail";
    }

    public String toReviewAnswer(Integer id, HttpSession session, Model model)
    {
        Answer answer = exerciseRepository.selectAnswerByID(id);
        Exercise exercise = exerciseRepository.selectExerciseByID(answer.getExercise_id());
        Knowledge knowledge = knowledgeRepository.selectKnowledge(exercise.getKnowledge_id());
        Subject subject = knowledgeRepository.selectSubject(knowledge.getSubject_id());
        List<Knowledge> knowledgeList = knowledgeRepository.listKnowledge(knowledge.getSubject_id());
        model.addAttribute("user", ((User) session.getAttribute("user")));
        model.addAttribute("knowledgeList", knowledgeList);
        model.addAttribute("currentKnowledgeID", id);
        model.addAttribute("originKnowledge", knowledge);
        model.addAttribute("originSubject", subject);
        exercise.setSubject_name(subject.getName());
        exercise.setKnowledge_name(knowledge.getTitle());
        model.addAttribute("originExercise", exercise);
        answer.setIs_right(0);
        model.addAttribute("originAnswer", answer);
        return "reviewDetail";
    }

    @Override
    public String reviewAnswer(Answer answer, HttpSession session, Model model) {
        exerciseRepository.modifyAnswer(answer);
        return ("redirect:/exercise/reviewConclude?id=" + (exerciseRepository.selectAnswerByID(answer.getId()).getExercise_id()));
    }
}
