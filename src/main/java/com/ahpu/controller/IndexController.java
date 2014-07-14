package com.ahpu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ahpu.bean.User;
import com.ahpu.bean.UserInfo;
import com.ahpu.intf.IQuestion;
import com.ahpu.intf.IUser;
import com.ahpu.intf.IUserInfo;

/*
 * 首页相关操作
 */
@Controller
public class IndexController {

    @Autowired
    IUser     userservice;

    @Autowired
    IUserInfo userInfoService;

    @Autowired
    IQuestion QuestionService;
    
    /*
     * 首页
     */
    @RequestMapping("index")
    public String index(Model model, HttpServletRequest requert, HttpServletResponse response) {
        Subject currentUser = SecurityUtils.getSubject();
        // 判断当前用户是否已经授权
        if (currentUser.isAuthenticated()) {
            Session session = currentUser.getSession();
            String username = (String) session.getAttribute("username");
            String role = (String) session.getAttribute("role");
            UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
            // model.addAttribute("userInfo", userInfo);
            List<User> list = userservice.queryAllUser();
            int enables = 0;
            for (User u : list) {
                if ("1".equals(u.getEnable().trim())) {
                    enables++;
                }
            }
            Long questions = QuestionService.countAll();
            Long finished = QuestionService.countFinished();
            model.addAttribute("allusers", list.size());// 用户总数
            model.addAttribute("enables", enables);// 用户总数
            model.addAttribute("questions", questions);// 问题总数
            model.addAttribute("finished", finished);// 已经解决
            model.addAttribute("username", username);
            model.addAttribute("role", role);
            model.addAttribute("users", list);
            if (userInfo != null) {
                model.addAttribute("avatar", userInfo.getAvatar());
            }
            model.addAttribute("active", 1);
            return "index.ftl";
        }
        return "login.ftl";
    }

    /*
     * 进入登录页面
     */
    @RequestMapping("login")
    public String login() {
        return "login.ftl";
    }
    
    /*
     * 进入注册页面
     */
    @RequestMapping("regist")
    public String regist() {
        return "register.ftl";
    }

    /*
     * 注册新用户
     */
    @RequestMapping("register")
    public void register(Model model, @RequestParam("username") String username,
            @RequestParam("password") String password, HttpServletRequest request,
            HttpServletResponse response) {
        if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setEnable("0");
            user.setRole("member");
            // 创建新用户
            userservice.createUser(user);
        }
    }

    /*
     * 校验用户名是否已经存在
     */
    @RequestMapping("checkname")
    public void checkName(Model model, @RequestParam("username") String username,
            HttpServletRequest request, HttpServletResponse response) {
        if (StringUtils.isNotBlank(username)) {
            boolean result = userservice.nameExist(username);
            PrintWriter pw = null;
            try {
                pw = response.getWriter();
                if (result) {
                    pw.write("exist");
                } else {
                    pw.write("no");
                }
            } catch (IOException e) {
                //
            } finally {
                if (pw != null) {
                    pw.close();
                }
            }
        }
    }

    /*
     * 登录验证
     */
    @RequestMapping("logon")
    public void logon(Model model, @RequestParam("username") String username,
            @RequestParam("password") String password, HttpServletRequest request,
            HttpServletResponse response) {
        if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {
            User user = userservice.queryByName(username);
            if (user != null && StringUtils.isNotBlank(user.getPassword())) {
                if (password.equals(user.getPassword())) {
                    Subject currentUser = SecurityUtils.getSubject();
                    UsernamePasswordToken token = new UsernamePasswordToken(username, password);
                    currentUser.login(token);
                    Session session = currentUser.getSession();
                    UserInfo userInfo = userInfoService.queryByName(username);
                    session.setAttribute("username", username);
                    if ("admin".equals(user.getRole())) {
                        session.setAttribute("role", "管理员");
                    } else {
                        session.setAttribute("role", "会员");
                    }
                    session.setAttribute("userInfo", userInfo);
                }
            }
        }

    }

    /*
     * 用户注销
     */
    @RequestMapping("logout")
    public String logout(Model model, HttpServletRequest requert, HttpServletResponse response) {
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        return "login.ftl";
    }

}
