package com.ahpu.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
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

@Controller
public class AnswerController {
    
    @Autowired
    IUserInfo userInfoService;
    
    @Autowired
    IAnswer answerService;
    
    @Autowired
    IQuestion questionService;
    
    /*
     * 回答操作
     */
    @RequestMapping("answer")
    public String publish(Model model, HttpServletRequest request, HttpServletResponse response){
        Subject currentUser = SecurityUtils.getSubject();
        if(currentUser.isAuthenticated()){
            Session session = currentUser.getSession();
            String username = (String) session.getAttribute("username");
            String role = (String) session.getAttribute("role");
            UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
            String content = request.getParameter("answers");
            String questionId = request.getParameter("questionid");
            //Question question = questionService.getById(Long.valueOf(questionId));
            
            Answer answer = new Answer();
            answer.setContent(content);
            answer.setUsername(username);
            answer.setCreateTime(new Date());
            answer.setModifyTime(new Date());
            answer.setReadcount(0);
            answer.setQuestionid(Integer.valueOf(questionId));
            
            answerService.createAnswer(answer);
            
            if(userInfo != null){
                model.addAttribute("userInfo", userInfo);
                model.addAttribute("avatar", userInfo.getAvatar());
            }
            model.addAttribute("username", username);
            model.addAttribute("role", role);
            model.addAttribute("active", 2);
            return "faq.ftl";
        }
        return "login.ftl";
    }

}
