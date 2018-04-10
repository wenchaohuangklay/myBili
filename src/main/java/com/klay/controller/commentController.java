package com.klay.controller;

import com.klay.dao.CommentMapper;
import com.klay.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class commentController {

    @Autowired
    CommentMapper commentMapper;


    @ResponseBody
    @RequestMapping("/addComment")
    public Integer addComment(HttpServletRequest request, Model model, HttpSession session,String comment,String videoId){
        /*String commentValue = request.getParameter("comment");*/
        String userId = (String) session.getAttribute("sessionUsername");
        /*String videoId = request.getParameter("videoId");*/
        System.out.print(comment);
        Comment comment1 = new Comment();
        comment1.setUserId(userId);
        comment1.setVideoId(videoId);
        comment1.setValue(comment);
        int add = this.commentMapper.insert(comment1);
        return add;
    }
}
