package com.ahpu.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ahpu.bean.Answer;
import com.ahpu.bean.Question;
import com.ahpu.bean.UserInfo;
import com.ahpu.intf.IAnswer;
import com.ahpu.intf.IQuestion;
import com.ahpu.intf.IUserInfo;

/*
 * 提问相关操作控制器
 */
@Controller
public class QuestionController {
    
    @Autowired
    IQuestion questionService;
    
    @Autowired
    IUserInfo userInfoService;
    
    @Autowired
    IAnswer answerService;
    
    /*
     * 进入互动页面
     */
    @RequestMapping("faq")
    public String queryQuestions(Model model, HttpServletRequest request, HttpServletResponse response){
        Subject currentUser = SecurityUtils.getSubject();
        if(currentUser.isAuthenticated()){
            Session session = currentUser.getSession();
            String username = (String) session.getAttribute("username");
            String role = (String) session.getAttribute("role");
            List<Question> list = questionService.queryByAll(0, 20);
            UserInfo userInfo = userInfoService.queryByName(username);
            model.addAttribute("role", role);
            model.addAttribute("questions", list);
            if(userInfo != null){
                model.addAttribute("userInfo", userInfo);
                model.addAttribute("avatar", userInfo.getAvatar());
            }
            model.addAttribute("active", 2);
            return "faq.ftl";
        }
        return "login.ftl";
    }
    
    /*
     * 进入与我相关页面
     */
    @RequestMapping("plans")
    public String plans(Model model, HttpServletRequest request, HttpServletResponse response){
        Subject currentUser = SecurityUtils.getSubject();
        if(currentUser.isAuthenticated()){
            Session session = currentUser.getSession();
            String username = (String) session.getAttribute("username");
            String role = (String) session.getAttribute("role");
            List<Question> list = questionService.queryByAuthorName(username);
            Map<String,List<Answer>> map = new HashMap<String,List<Answer>>();
            for(Question q : list){
                List<Answer> answerlist = answerService.queryByQuestion(q.getQuestionid());
                if(!CollectionUtils.isEmpty(answerlist)){
                    map.put(String.valueOf(q.getQuestionid()), answerlist);
                }
            }
            if(!CollectionUtils.isEmpty(map)){
                model.addAttribute("answermap", map);
            }
            UserInfo userInfo = userInfoService.queryByName(username);
            model.addAttribute("role", role);
            model.addAttribute("questions", list);
            if(userInfo != null){
                model.addAttribute("userInfo", userInfo);
                model.addAttribute("avatar", userInfo.getAvatar());
            }
            model.addAttribute("active", 3);
            return "plans.ftl";
        }
        return "login.ftl";
        
    }

    /*
     * 发表问题
     */
    @RequestMapping("publish")
    public String publish(Model model, HttpServletRequest request, HttpServletResponse response){
        Subject currentUser = SecurityUtils.getSubject();
        if(currentUser.isAuthenticated()){
            String title = request.getParameter("title");
            String content = request.getParameter("content");
            Session session = currentUser.getSession();
            String username = (String) session.getAttribute("username");
            String role = (String) session.getAttribute("role");
            UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
            Question question = new Question();
            question.setAuthorname(username);
            question.setDescription(title);
            question.setContent(content);
            question.setReadcount(0);
            question.setFinished(0);
            question.setCreateTime(new Date());
            question.setModifyTime(new Date());
            if(userInfo != null){
                question.setAuthorid(userInfo.getUserid());
                model.addAttribute("userInfo", userInfo);
                model.addAttribute("avatar", userInfo.getAvatar());
            }
            questionService.createQuestion(question);
            model.addAttribute("username", username);
            model.addAttribute("role", role);
            model.addAttribute("active", 2);
            return "succeed";
        }
        return "failed";
    }
}
