package com.klay.controller;

import com.klay.dao.UserMapper;
import com.klay.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
public class loginController {

    @Autowired
    UserMapper userMapper;

    @RequestMapping("/login")
    public String login(HttpSession session){
        session.removeAttribute("msg");
        return "login";
    }

    @RequestMapping("/addLogin")
    public String addLogin(HttpServletRequest request, HttpSession httpSession){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = userMapper.selectByPrimaryKey(username);
        String userNickName = user.getUserName();
        String str = "";
        if (user == null || !user.getUserPassword().equals(password))
        {
            str = "redirect:/login";
        }else {
            str = "redirect:/index";
        }

        httpSession.setAttribute("sessionUsername",username);
        httpSession.setAttribute("nickName",userNickName);

        return str;
    }

    @RequestMapping("/register")
    public String register(Model model,HttpSession session){
        /*model.addAttribute("msg",session.getAttribute("msg"));*/
        return "register";
    }

    @RequestMapping("/addRegister")
    public String addRegister(HttpServletRequest request, Model model,HttpSession session){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String nickName = request.getParameter("nickName");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        /*Date birthday = request.getParameter("birthday");*/
        String image = request.getParameter("image");
        User user = new User();
        String str = "";
        String message1 = "注册失败，用户名已存在！";
        String message2 = "注册失败，存入数据库失败！";
        String message3 = "注册失败，用户名、昵称和密码不能为空！";
        session.removeAttribute("msg");
        if (username != null)
        {
            User userTest = userMapper.selectByPrimaryKey(username);
            if (userTest != null)
            {
                session.setAttribute("msg",message1);
                str = "redirect:/register";
                return str;
            }
        }
        if (username != null && password != null && nickName != null)
        {
            user.setUserId(username);
            user.setUserPassword(password);
            user.setUserName(nickName);
            user.setUserBirthday(null);
            user.setUserEmail(email);
            user.setUserGender(gender);
            boolean flag = userMapper.insert(user);
            if (!flag)
            {
                session.setAttribute("msg",message2);
                str = "redirect:/register";
            }else {
                str = "redirect:/login";
            }
        }else {
            session.setAttribute("msg",message3);
            str = "redirect:/register";
        }

        return str;
    }

    @RequestMapping("/logOut")
    public String logOut(HttpSession session){
        session.removeAttribute("sessionUsername");
        return "index";
    }
}
