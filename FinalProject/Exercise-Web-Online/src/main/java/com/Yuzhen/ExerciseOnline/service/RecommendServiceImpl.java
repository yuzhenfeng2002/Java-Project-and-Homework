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
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

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

    @Override
    public String exerciseRecommend(HttpSession session, Model model, Integer id) {
        Integer recommendNum = 15;
        double progressLimit = 0.75;
        Integer scoreLimit = 85;
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", (User) session.getAttribute("user"));
        Subject subject = knowledgeRepository.selectSubject(id);
        List<Exercise> exerciseList = new LinkedList<Exercise>();
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

            if (knowledge.getProgress() == 0) {
                List<Integer> dependencies = recommendRepository.getDependencies(knowledge);
                boolean isDependent = false;
                for (Integer knowledge_id : dependencies) {
                    Knowledge dependentKnowledge = knowledgeRepository.selectKnowledge(knowledge_id);
                    Integer countK = recommendRepository.getKnowledgeNum(dependentKnowledge);
                    double progressK;
                    if (countK == 0) {
                        progressK = 100;
                        continue;
                    } else {
                        Integer sum;
                        if (recommendRepository.getKnowledgeProgress(user, dependentKnowledge) == null) {
                            sum = 0;
                            isDependent = true;
                            break;
                        } else {
                            sum = recommendRepository.getKnowledgeProgress(user, dependentKnowledge);
                        }
                        progressK = (double) (sum / countK);
                    }
                    isDependent = (isDependent || (progressK < progressLimit));
                }
                knowledge.setDependent(isDependent);
                if (!isDependent) {
                    exerciseList.addAll(exerciseRepository.selectExercise(knowledge.getId()));
                }
            }
            else {
                exerciseList.addAll(recommendRepository.getRecommendedExercise(user, knowledge, scoreLimit));
            }
        }
        int deleteNum = exerciseList.size() - recommendNum;
        for (int i = 0; i < deleteNum; i ++)
        {
            exerciseList.remove(ThreadLocalRandom.current().nextInt(0, exerciseList.size()));
        }
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
        model.addAttribute("subject", subject);
        model.addAttribute("knowledgeList", knowledgeList);
        model.addAttribute("currentKnowledgeID", -1);
        model.addAttribute("currentTitle", subject.getName());
        return "exercise";
    }
}
