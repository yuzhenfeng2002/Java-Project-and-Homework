package com.Yuzhen.ExerciseOnline.controller;

import com.Yuzhen.ExerciseOnline.entity.Exercise;
import com.Yuzhen.ExerciseOnline.entity.Knowledge;
import com.Yuzhen.ExerciseOnline.entity.Subject;
import com.Yuzhen.ExerciseOnline.entity.User;
import com.Yuzhen.ExerciseOnline.service.ExerciseService;
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
@RequestMapping("/exercise")
public class ExerciseController extends AuthorityController{
    @Autowired
    private ExerciseService exerciseService;

    @RequestMapping("/toAdd")
    public String toAddExercise(@ModelAttribute("exercise") Exercise exercise, HttpSession session, Model model) {
        User user = (User)session.getAttribute("user");
        if (user.getUsertype() == 1)
        {
            model.addAttribute("errorMessage", "您没有权限！");
            return "errorPage";
        }
        model.addAttribute("user", user);
        return "addExercise";
    }

    @RequestMapping("/add")
    public String addExercise(@ModelAttribute("exercise") @Validated Exercise exercise, BindingResult rs, HttpSession session, Model model)
    {
        User user = (User)session.getAttribute("user");
        if (user.getUsertype() == 1)
        {
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
    public String subjectDetail(HttpSession session, Model model, Integer id)
    {
        return exerciseService.listKnowledge(session, model, id);
    }

    @RequestMapping("/list")
    public String exerciseList(HttpSession session, Model model, Integer id)
    {
        return exerciseService.showExerciseList(session, model, id);
    }

    @RequestMapping("/toModify")
    public String toModifyExercise(Integer id, HttpSession session, Model model) {
        User user = (User)session.getAttribute("user");
        if (user.getUsertype() == 1)
        {
            model.addAttribute("errorMessage", "您没有权限！");
            return "errorPage";
        }
        model.addAttribute("user", user);
        return exerciseService.toModifyExercise(id, session, model);
    }

    @RequestMapping("/modify")
    public String modifyExercise(@ModelAttribute("originExercise") @Validated Exercise exercise, BindingResult rs, HttpSession session, Model model)
    {
        User user = (User)session.getAttribute("user");
        if (user.getUsertype() == 1)
        {
            model.addAttribute("errorMessage", "您没有权限！");
            return "errorPage";
        }
        return exerciseService.modifyExercise(exercise, session, model);
    }

    /*
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

    @RequestMapping("/subject/toModify")
    public String toModifySubject(Integer id, HttpSession session, Model model) {
        User user = (User)session.getAttribute("user");
        if (user.getUsertype() == 1)
        {
            model.addAttribute("errorMessage", "您没有权限！");
            return "errorPage";
        }
        model.addAttribute("user", user);
        return knowledgeService.toModifySubject(id, session, model);
    }

    @RequestMapping("/detail/toModify")
    public String toModifyKnowledge(Integer id, HttpSession session, Model model) {
        User user = (User)session.getAttribute("user");
        if (user.getUsertype() == 1)
        {
            model.addAttribute("errorMessage", "您没有权限！");
            return "errorPage";
        }
        model.addAttribute("user", user);
        return knowledgeService.toModifyKnowledge(id, session, model);
    }

    @RequestMapping("/subject/add")
    public String addSubject(@ModelAttribute("subject") @Validated Subject subject, BindingResult rs, HttpSession session, Model model)
    {
        User user = (User)session.getAttribute("user");
        if (user.getUsertype() == 1)
        {
            model.addAttribute("errorMessage", "您没有权限！");
            return "errorPage";
        }
        if (rs.hasErrors()) // 验证失败
        {
            return "addSubject";
        }
        return knowledgeService.addSubject(subject, session, model);
    }

    @RequestMapping("/detail/add")
    public String addKnowledge(@ModelAttribute("knowledge") @Validated Knowledge knowledge, BindingResult rs, HttpSession session, Model model)
    {
        User user = (User)session.getAttribute("user");
        if (user.getUsertype() == 1)
        {
            model.addAttribute("errorMessage", "您没有权限！");
            return "errorPage";
        }
        if (rs.hasErrors()) // 验证失败
        {
            return "addKnowledge";
        }
        return knowledgeService.addKnowledge(knowledge, session, model);
    }

    @RequestMapping("/subject/modify")
    public String modifySubject(@ModelAttribute("originSubject") @Validated Subject subject, BindingResult rs, HttpSession session, Model model)
    {
        User user = (User)session.getAttribute("user");
        if (user.getUsertype() == 1)
        {
            model.addAttribute("errorMessage", "您没有权限！");
            return "errorPage";
        }
        if (rs.hasErrors()) // 验证失败
        {
            return this.toModifySubject(subject.getId(), session, model);
        }
        return knowledgeService.modifySubject(subject, session, model);
    }

    @RequestMapping("/detail/modify")
    public String modifyKnowledge(@ModelAttribute("originKnowledge") @Validated Knowledge knowledge, BindingResult rs, HttpSession session, Model model)
    {
        User user = (User)session.getAttribute("user");
        if (user.getUsertype() == 1)
        {
            model.addAttribute("errorMessage", "您没有权限！");
            return "errorPage";
        }
        return knowledgeService.modifyKnowledge(knowledge, session, model);
    }

    @RequestMapping("/detail")
    public String knowledgeDetail(HttpSession session, Model model, Integer id)
    {
        return knowledgeService.showKnowledgeDetail(session, model, id);
    }
    */
}
