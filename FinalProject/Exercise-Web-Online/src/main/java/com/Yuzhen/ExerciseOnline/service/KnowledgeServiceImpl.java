package com.Yuzhen.ExerciseOnline.service;

import com.Yuzhen.ExerciseOnline.auxiliary.Auxiliary;
import com.Yuzhen.ExerciseOnline.entity.Image;
import com.Yuzhen.ExerciseOnline.entity.Knowledge;
import com.Yuzhen.ExerciseOnline.entity.Subject;
import com.Yuzhen.ExerciseOnline.entity.User;
import com.Yuzhen.ExerciseOnline.repository.KnowledgeRepository;
import com.Yuzhen.ExerciseOnline.repository.RecommendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class KnowledgeServiceImpl implements KnowledgeService {
    @Autowired
    private KnowledgeRepository knowledgeRepository;
    @Autowired
    private RecommendRepository recommendRepository;

    @Override
    public String listSubject(HttpSession session, Model model) {
        List<Subject> subjects = knowledgeRepository.listSubject();
        for (Subject subject : subjects) {
            subject.setIntroduction(Auxiliary.modifyContent(subject.getIntroduction()));
        }
        model.addAttribute("user", session.getAttribute("user"));
        model.addAttribute("subjects", subjects);
        return "index";
    }

    @Override
    public String listKnowledge(HttpSession session, Model model, Integer id) {
        Subject subject = knowledgeRepository.selectSubject(id);
        // List<Knowledge> knowledgeList = knowledgeRepository.listKnowledge(id);
        model.addAttribute("user", session.getAttribute("user"));
        model.addAttribute("subject", subject);
        // model.addAttribute("knowledgeList", knowledgeList);
        // model.addAttribute("currentKnowledgeID", -1);
        model.addAttribute("currentTitle", subject.getName());
        model.addAttribute("content", Auxiliary.modifyContent(subject.getIntroduction()));
        return "knowledge";
    }

    @Override
    public String showKnowledgeDetail(HttpSession session, Model model, Integer id) {
        Knowledge knowledge = knowledgeRepository.selectKnowledge(id);
        Subject subject = knowledgeRepository.selectSubject(knowledge.getSubject_id());
        // List<Knowledge> knowledgeList = knowledgeRepository.listKnowledge(knowledge.getSubject_id());
        model.addAttribute("user", session.getAttribute("user"));
        model.addAttribute("subject", subject);
        // model.addAttribute("knowledgeList", knowledgeList);
        // model.addAttribute("currentKnowledgeID", id);
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
        model.addAttribute("user", session.getAttribute("user"));
        model.addAttribute("knowledgeList", knowledgeList);
        model.addAttribute("subject", subject);
        return "addKnowledge";
    }

    @Override
    public String addSubject(Subject subject, HttpSession session, Model model) {
        if (knowledgeRepository.isSubject(subject.getName()).size() > 0) {
            model.addAttribute("errorMessage", "课程已存在，请前往相关课程页面查看！");
            model.addAttribute("user", session.getAttribute("user"));
            return "addSubject";
        } else {
            knowledgeRepository.addSubject(subject);
        }
        return "redirect:/knowledge/list";
    }

    @Override
    public String addKnowledge(Knowledge knowledge, HttpSession session, Model model) {
        Subject subject = knowledgeRepository.selectSubjectByName(knowledge.getSubject_name());
        if (subject == null) {
            model.addAttribute("errorMessage", "课程不存在，请前往课程页面确认！");
            model.addAttribute("user", session.getAttribute("user"));
            return "addKnowledge";
        } else if (knowledgeRepository.isKnowledge(subject.getId(), knowledge.getTitle()).size() > 0) {
            model.addAttribute("errorMessage", "知识点已存在，请前往相关课程页面查看！");
            model.addAttribute("user", session.getAttribute("user"));
            return "addKnowledge";
        } else {
            knowledge.setSubject_id(subject.getId());
            knowledgeRepository.addKnowledge(knowledge);
            Knowledge addedKnowledge = knowledgeRepository.selectKnowledgeByName(knowledge.getSubject_id(), knowledge.getTitle());
            for (Integer dependent_id : knowledge.getDependency()) {
                if (dependent_id != -1)
                    knowledgeRepository.addDependency(addedKnowledge.getId(), dependent_id);
            }
        }
        return ("redirect:/subject/progress?id=" + (subject.getId()));
    }

    @Override
    public String toModifySubject(Integer id, HttpSession session, Model model) {
        Subject subject = knowledgeRepository.selectSubject(id);
        // List<Knowledge> knowledgeList = knowledgeRepository.listKnowledge(id);
        model.addAttribute("user", session.getAttribute("user"));
        // model.addAttribute("knowledgeList", knowledgeList);
        // model.addAttribute("currentKnowledgeID", -1);
        model.addAttribute("originSubject", subject);
        return "modifySubject";
    }

    @Override
    public String toModifyKnowledge(Integer id, HttpSession session, Model model) {
        Knowledge knowledge = knowledgeRepository.selectKnowledge(id);
        Subject subject = knowledgeRepository.selectSubject(knowledge.getSubject_id());
        List<Knowledge> knowledgeList = knowledgeRepository.listKnowledge(knowledge.getSubject_id());
        model.addAttribute("user", session.getAttribute("user"));
        model.addAttribute("knowledgeList", knowledgeList);
        // model.addAttribute("currentKnowledgeID", id);
        List<Integer> dependency = recommendRepository.getDependencies(knowledge);
        knowledge.setDependency(dependency);
        model.addAttribute("originKnowledge", knowledge);
        model.addAttribute("originSubject", subject);
        return "modifyKnowledge";
    }

    @Override
    public String modifySubject(Subject subject, HttpSession session, Model model) {
        knowledgeRepository.modifySubject(subject);
        return ("redirect:/knowledge/subject?id=" + (subject.getId()));
    }

    @Override
    public String modifyKnowledge(Knowledge knowledge, HttpSession session, Model model) {
        knowledgeRepository.modifyKnowledge(knowledge);
        knowledgeRepository.dropDependency(knowledge.getId());
        for (Integer dependent_id : knowledge.getDependency()) {
            if (dependent_id != -1)
                knowledgeRepository.addDependency(knowledge.getId(), dependent_id);
        }
        return ("redirect:/knowledge/detail?id=" + (knowledge.getId()));
    }

    @Override
    public String toAddImage(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        model.addAttribute("imageList", knowledgeRepository.listImageOfUser(user));
        return "image";
    }

    @Override
    public String addImage(Image image, HttpSession session, Model model, HttpServletRequest request) {
        User user = (User) session.getAttribute("user");
        MultipartFile myfile = image.getAddress();
        // 如果选择了上传文件，将文件上传到指定的目录images
        if (!myfile.isEmpty()) {
            // 上传文件路径（生产环境）
            String path = request.getServletContext().getRealPath("/images/");
            // 获得上传文件原名
            // 上传文件路径（开发环境）
            // String path = "/Users/fxb/Desktop/大三上/Java语言程序设计/FinalProject/Exercise-Web-Online/src/main/resources/static/images";
            // 获得上传文件原名
            String fileName = myfile.getOriginalFilename();
            // 对文件重命名
            String fileNewName = Auxiliary.getNewFileName(fileName);
            File filePath = new File(path + File.separator + fileNewName);
            // 如果文件目录不存在，创建目录
            if (!filePath.getParentFile().exists()) {
                filePath.getParentFile().mkdirs();
            }
            // 将上传文件保存到一个目标文件中
            try {
                myfile.transferTo(filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            image.setNew_name(fileNewName);
        }
        knowledgeRepository.addImage(user, image);
        return "redirect:/knowledge/toAddImage";
    }

    @Override
    public String deleteImage(Integer id, HttpSession session, Model model, HttpServletRequest request) {
        String path = request.getServletContext().getRealPath("/images/");
        // String path = "/Users/fxb/Desktop/大三上/Java语言程序设计/FinalProject/Exercise-Web-Online/src/main/resources/static/images";
        Image image = knowledgeRepository.selectImage(id);
        File filePath = new File(path + File.separator + image.getNew_name());
        filePath.delete();
        knowledgeRepository.deleteImage(id);
        return "redirect:/knowledge/toAddImage";
    }
}
