package com.ahpu.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ahpu.bean.User;
import com.ahpu.bean.UserInfo;
import com.ahpu.intf.IUser;
import com.ahpu.intf.IUserInfo;

@Controller
public class UserController {

    @Autowired
    IUser     userservice;

    @Autowired
    IUserInfo userInfoService;

    /*
     * 查看用户信息
     */
    @RequestMapping("account")
    public String userInfo(Model model, HttpServletRequest request, HttpServletResponse response) {
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser.isAuthenticated()) {
            Session session = currentUser.getSession();
            String username = (String) session.getAttribute("username");
            String role = (String) session.getAttribute("role");
            UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
            User user = userservice.queryByName(username);
            if (userInfo != null) {
                model.addAttribute("userInfo", userInfo);
                model.addAttribute("avatar", userInfo.getAvatar());
            }
            model.addAttribute("username", username);
            model.addAttribute("role", role);
            model.addAttribute("pwd", user.getPassword());
            model.addAttribute("active", 6);
            return "account.ftl";
        }
        return "login.ftl";
    }

    /*
     * 更新用户信息
     */
    @RequestMapping("update")
    public void updateInfo(Model model, HttpServletRequest request, HttpServletResponse response) {
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser.isAuthenticated()) {
            Session session = currentUser.getSession();
            String username = (String) session.getAttribute("username");
            UserInfo userInfo = new UserInfo();
            String realname = request.getParameter("firstname");
            String sex = request.getParameter("sex");
            String email = request.getParameter("email");
            String avatar = request.getParameter("avatar");
            String address = request.getParameter("address");
            String telephone = request.getParameter("telephone");
            Integer community = Integer.valueOf(request.getParameter("community").trim());
            userInfo.setUsername(username);
            userInfo.setRealname(realname);
            userInfo.setEmail(email);
            userInfo.setAddress(address);
            userInfo.setAvatar(avatar);
            userInfo.setTelephone(telephone);
            userInfo.setSex(sex);
            userInfo.setCommunityid(community);
            UserInfo user = userInfoService.queryByName(username);
            if (user == null) {
                userInfoService.createUser(userInfo);
            } else {
                userInfoService.updateByName(userInfo);
            }
            session.removeAttribute("userInfo");
            model.addAttribute("userInfo", userInfo);
            model.addAttribute("avatar", userInfo.getAvatar());
        }
    }

    /*
     * 修改密码
     */
    @RequestMapping("change_password")
    public void changepassword(Model model, HttpServletRequest request, HttpServletResponse response) {
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser.isAuthenticated()) {
            Session session = currentUser.getSession();
            String username = (String) session.getAttribute("username");
            String password = request.getParameter("password");
            if (StringUtils.isNotBlank(password) && StringUtils.isNotBlank(username)) {
                User user = new User();
                user.setUsername(username);
                user.setPassword(password);
                userservice.updatePwdByName(user);
            }
        }
    }

    @RequestMapping("charts")
    public String charts() {
        return "charts.ftl";
    }

    @RequestMapping("grid")
    public String grid() {
        return "grid.ftl";
    }
}
