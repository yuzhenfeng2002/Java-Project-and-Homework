package com.Yuzhen.ExerciseOnline.controller;

import com.Yuzhen.ExerciseOnline.entity.Answer;
import com.Yuzhen.ExerciseOnline.entity.Exercise;
import com.Yuzhen.ExerciseOnline.entity.User;
import com.Yuzhen.ExerciseOnline.service.ExerciseService;
import com.Yuzhen.ExerciseOnline.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    public String knowledgeProgress(HttpSession session, Model model, Integer id)
    {
        return recommendService.knowledgeProgress(session, model, id);
    }

    /*
    @RequestMapping("/add")
    public String addExercise(@ModelAttribute("exercise") @Validated Exercise exercise, BindingResult rs, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user.getUsertype() == 1) {
            model.addAttribute("errorMessage", "您没有权限！");
            return "errorPage";
        }
        if (rs.hasErrors()) // 验证失败
        {
            return "addExercise";
        }
        return exerciseService.addExercise(exercise, session, model);
    }

    @RequestMapping("/subject")
    public String subjectDetail(HttpSession session, Model model, Integer id) {
        return exerciseService.listKnowledge(session, model, id);
    }

    @RequestMapping("/list")
    public String exerciseList(HttpSession session, Model model, Integer id) {
        Answer answer = new Answer();
        model.addAttribute("myAnswer", answer);
        return exerciseService.showExerciseList(session, model, id);
    }

    @RequestMapping("/toModify")
    public String toModifyExercise(Integer id, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user.getUsertype() == 1) {
            model.addAttribute("errorMessage", "您没有权限！");
            return "errorPage";
        }
        model.addAttribute("user", user);
        return exerciseService.toModifyExercise(id, session, model);
    }

    @RequestMapping("/modify")
    public String modifyExercise(@ModelAttribute("originExercise") @Validated Exercise exercise, BindingResult rs, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user.getUsertype() == 1) {
            model.addAttribute("errorMessage", "您没有权限！");
            return "errorPage";
        }
        return exerciseService.modifyExercise(exercise, session, model);
    }

    @RequestMapping("/tryToSolve")
    public String tryToSolveExercise(@ModelAttribute("myAnswer") @Validated Answer answer, BindingResult rs, HttpSession session, Model model, Integer id) {
        User user = (User) session.getAttribute("user");
        answer.setExercise_id(id);
        answer.setUser_email(user.getEmail());
        return exerciseService.tryToSolveExercise(answer, session, model);
    }

    @RequestMapping("/answerConclude")
    public String answerConclude(HttpSession session, Model model, Integer id) {
        return exerciseService.answerConclude(id, session, model);
    }

    @RequestMapping("/answerDetail")
    public String answerDetail(HttpSession session, Model model, Integer id) {
        return exerciseService.answerDetail(id, session, model);
    }

    @RequestMapping("/reviewConclude")
    public String reviewPage(HttpSession session, Model model, Integer id) {
        User user = (User) session.getAttribute("user");
        if (user.getUsertype() == 1) {
            model.addAttribute("errorMessage", "您没有权限！");
            return "errorPage";
        }
        return exerciseService.reviewConclude(id, session, model);
    }

    @RequestMapping("/toReview")
    public String toReview(HttpSession session, Model model, Integer id) {
        User user = (User) session.getAttribute("user");
        if (user.getUsertype() == 1) {
            model.addAttribute("errorMessage", "您没有权限！");
            return "errorPage";
        }
        return exerciseService.toReviewAnswer(id, session, model);
    }

    @RequestMapping("/review")
    public String review(@ModelAttribute("originAnswer") @Validated Answer answer, BindingResult rs, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user.getUsertype() == 1) {
            model.addAttribute("errorMessage", "您没有权限！");
            return "errorPage";
        }
        return exerciseService.reviewAnswer(answer, session, model);
    }

    */
}
