package com.klay.controller;

import com.klay.dao.CommentMapper;
import com.klay.dao.UserMapper;
import com.klay.dao.VideoLikeMapper;
import com.klay.dao.VideoMapper;
import com.klay.model.Comment;
import com.klay.model.User;
import com.klay.model.Video;
import com.klay.model.VideoLike;
import com.klay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class profileController {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserService userService;

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    VideoMapper videoMapper;

    @Autowired
    VideoLikeMapper videoLikeMapper;

    @RequestMapping("/profile")
    public String profile(HttpSession session, Model model){
        model.addAttribute("username",session.getAttribute("sessionUsername"));
        User user = this.userMapper.selectByPrimaryKey((String)session.getAttribute("sessionUsername"));
        List<Comment> commentList = this.commentMapper.selectByUserId((String)session.getAttribute("sessionUsername"));
        model.addAttribute("commentList",commentList);
        model.addAttribute("user",user);
        if (session.getAttribute("sessionUsername") == null){
            return "index";
        }
        List<VideoLike> videoLikeList = this.videoLikeMapper.selectAll();
        List<VideoLike> myVideoLikeList = new ArrayList<>();
        List<Video> videoList = new ArrayList<>();
        for (VideoLike videolike : videoLikeList) {
            if(videolike.getUserId().equals(session.getAttribute("sessionUsername"))){
                myVideoLikeList.add(videolike);
            }
        }
        for (VideoLike videolike : myVideoLikeList) {
            videoList.add(this.videoMapper.selectByPrimaryKey(videolike.getVideoId()));
        }
        model.addAttribute("videoList",videoList);
        return "profile";
    }

    @RequestMapping("/getProfile")
    @ResponseBody
    public User getProfile(HttpSession session,Model model){
        User user = this.userMapper.selectByPrimaryKey((String)session.getAttribute("sessionUsername"));
        model.addAttribute("user",user);
        return  user;
    }

    @RequestMapping("/profile/change")
    public String updateUser(HttpServletRequest request){
        String userId = request.getParameter("userId");
        String userName = request.getParameter("userName");
        String userEmail = request.getParameter("userEmail");
        String gender = request.getParameter("gender");
        User user1 = this.userMapper.selectByPrimaryKey(userId);
        String password = user1.getUserPassword();
        String avatar = user1.getAvatar();
        User user2 = new User();
        user2.setUserId(userId);
        user2.setUserName(userName);
        user2.setUserPassword(password);
        user2.setUserGender(gender);
        user2.setUserEmail(userEmail);
        user2.setAvatar(avatar);
        try {
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date=simpleDateFormat.parse(request.getParameter("birthday"));
            user2.setUserBirthday(date);
        }catch (Exception e){
            e.printStackTrace();
        }
        Integer change = this.userMapper.updateByPrimaryKey(user2);
        System.out.print(change);
        return "redirect:/profile";
    }

    @ResponseBody
    @RequestMapping("/addVideoLike")
    public void addVideoLike(String videoId,HttpSession session){
        String username = (String) session.getAttribute("sessionUsername");
        VideoLike videoLike = new VideoLike();
        videoLike.setUserId(username);
        videoLike.setVideoId(videoId);
        int add = this.videoLikeMapper.insert(videoLike);
    }

    @ResponseBody
    @RequestMapping("/deleteVideoLike")
    public void deleteVideoLike(String videoId,HttpSession session){
        String username = (String) session.getAttribute("sessionUsername");
        VideoLike videoLike = new VideoLike();
        videoLike.setUserId(username);
        videoLike.setVideoId(videoId);
        int delete = this.videoLikeMapper.deleteByPrimaryKey(username,videoId);
    }

    @ResponseBody
    @RequestMapping("/selectAllVideoLike")
    public boolean selectAllVideoLike(String videoId,HttpSession session,Model model){
        String username = (String) session.getAttribute("sessionUsername");
        VideoLike videoLike1 = new VideoLike();
        videoLike1.setVideoId(videoId);
        videoLike1.setUserId(username);
        List<VideoLike> videoLikeList = this.videoLikeMapper.selectAll();
        boolean flag = false;
        for (VideoLike videoLike:videoLikeList){
            if(videoLike.getUserId().equals(videoLike1.getUserId())&&videoLike.getVideoId().equals(videoLike1.getVideoId()))
            {
                flag = true;
            }
        }
        model.addAttribute("flag",flag);
        return flag;
    }
}
