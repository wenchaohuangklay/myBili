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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    private final Path rootLocation = Paths.get("C:\\Users\\khuan\\IdeaProjects\\bilibili\\src\\main\\resources\\static\\image");
    private final Path videoLocation = Paths.get("C:\\Users\\khuan\\IdeaProjects\\bilibili\\src\\main\\resources\\static\\video");
    /*private final Path ffmpegPath = Paths.get("C:\\ffmpeg-4.0\\ffmpeg-4.0");*/


    @RequestMapping("/profile")
    public String profile(HttpSession session, Model model){
        model.addAttribute("username",session.getAttribute("sessionUsername"));
        session.removeAttribute("wrongId");
        User user = this.userMapper.selectByPrimaryKey((String)session.getAttribute("sessionUsername"));
        if(user.getAvatar() == null){
            user.setAvatar("/image/dongman.jpg");
        }
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
        List<Video> myVideoList = this.videoMapper.selectByUserId((String) session.getAttribute("sessionUsername"));
        model.addAttribute("myVideoList",myVideoList);
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


    @RequestMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file,HttpServletRequest request,HttpSession session, Model model){
        model.addAttribute("username",session.getAttribute("sessionUsername"));
        String uploadDir = "C:/Users/khuan/IdeaProjects/bilibili/src/main/resources/static/video/";/*视频存放地址*/
        String imageDir = "C:/Users/khuan/IdeaProjects/bilibili/src/main/resources/static/video_cover/";/*视频截图存放地址*/
        String filename = file.getOriginalFilename();/*视频的名字*/
        String ffmpegPath = "C:/ffmpeg-4.0/ffmpeg.exe";/*转换器的地址*/
        File serverFile = new File(uploadDir+filename);/*视频文件*/
        boolean mark = true;
        if (!file.isEmpty()){
            try {
                /*String uploadDir = "C://Users//khuan//IdeaProjects//bilibili//src//main//resources//static//video//";
                String filename = file.getOriginalFilename();
                File serverFile = new File(uploadDir+filename);*/
                if(!serverFile.getParentFile().exists()){
                    serverFile.getParentFile().mkdirs();
                }
                file.transferTo(serverFile);
            }catch (Exception e){
                mark = false;
                e.printStackTrace();
                return "redirect:/uploadVideo";
            }

        }
        // 创建一个List集合来保存从视频中截取图片的命令
        List<String> cutpic = new ArrayList<String>();
        cutpic.add(ffmpegPath);
        cutpic.add("-i");
        cutpic.add(uploadDir+filename); // 同上（指定的文件即可以是转换为flv格式之前的文件，也可以是转换的flv文件）
        cutpic.add("-y");
        cutpic.add("-f");
        cutpic.add("image2");
        cutpic.add("-ss"); // 添加参数＂-ss＂，该参数指定截取的起始时间
        cutpic.add("5"); // 添加起始时间为第17秒
        //cutpic.add("-t"); // 添加参数＂-t＂，该参数指定持续时间
       // cutpic.add("0.001"); // 添加持续时间为1毫秒 /*兄弟这两条代码我花费了一个下午来解决，真是他妈的日了狗了*/
        cutpic.add("-s"); // 添加参数＂-s＂，该参数指定截取的图片大小
        cutpic.add("400*300"); // 添加截取的图片大小为400*300
        cutpic.add(imageDir + filename.substring(0,filename.indexOf(".")) + ".jpg"); // 添加截取的图片的保存路径
        ProcessBuilder builder = new ProcessBuilder(cutpic);
        try {
            /*builder.command(cutpic);*/
           /* builder.redirectErrorStream(true);*/
            builder.start();
        } catch (Exception e) {
            mark = false;
            e.printStackTrace();
            return "redirect:/uploadVideo";
        }
        Video video = new Video();
        String videoId = request.getParameter("videoId");
        if (videoId != null){
            Video video1 = this.videoMapper.selectByPrimaryKey(videoId);
            if (video1 != null){
                String wrongVideoId = "id已经存在，换个id试试";
                session.setAttribute("wrongId",wrongVideoId);
                return "redirect:/uploadVideo";
            }
        }
        String videoTitle= request.getParameter("videoTitle");
        String videoType = request.getParameter("videoType");
        video.setUserId((String) session.getAttribute("sessionUsername"));
        video.setVideoCoverPath("/video_cover/" + filename.substring(0,filename.indexOf(".")) + ".jpg");
        video.setVideoId(videoId);
        video.setVideoPath("/video/"+ file.getOriginalFilename());
        video.setVideoTitle(videoTitle);
        video.setVideoType(videoType);
        int add = this.videoMapper.insert(video);
        return "/index";
    }

    @RequestMapping("/uploadVideo")
    public String uploadVideo(HttpSession session,Model model){
        model.addAttribute("username",session.getAttribute("sessionUsername"));
        return "upload";
    }

    @RequestMapping(value = "/server",method = RequestMethod.GET)
    public String server(Model model){
       List<User> userList = userMapper.selectAll();
        List<Comment> commentList = commentMapper.selectAll();
        List<Video> videoList = videoMapper.selectAll();
        model.addAttribute("userList",userList);
        model.addAttribute("commentList",commentList);
        model.addAttribute("videoList",videoList);
        return "server";
    }

    @ResponseBody
    @RequestMapping(value = "/server2",method = RequestMethod.GET)
    public List<Video> serverSec(Model model){
        List<User> userList = userMapper.selectAll();
        List<Comment> commentList = commentMapper.selectAll();
        List<Video> videoList = videoMapper.selectAll();
        model.addAttribute("userList",userList);
        model.addAttribute("commentList",commentList);
        model.addAttribute("videoList",videoList);
        return videoList;
    }

    @ResponseBody
    @RequestMapping(value = "/serverUser",method = RequestMethod.GET)
    public List<User> serverUser(Model model){
        List<User> userList = userMapper.selectAll();
        model.addAttribute("userList",userList);
        return userList;
    }

    @RequestMapping("/deleteUser")
    public String deleteUser(String userId, HttpSession session, Model model){
        model.addAttribute("username",session.getAttribute("sessionUsername"));
        if (userId != null) {
           int flag = userMapper.deleteByPrimaryKey(userId);
        }
        return "redirect:/server";
    }

    @RequestMapping("/deleteVideo")
    public String deleteVideo(String videoId, HttpSession session, Model model){
        model.addAttribute("username",session.getAttribute("sessionUsername"));
        if (videoId != null) {
            int flag = videoMapper.deleteByPrimaryKey(videoId);
        }
        return "redirect:/server";
    }

    @RequestMapping("/deleteComment")
    public String deleteComment(String commentId, HttpSession session, Model model){
        model.addAttribute("username",session.getAttribute("sessionUsername"));
        if (commentId != null) {
            int flag = commentMapper.deleteByPrimaryKey(Integer.parseInt(commentId));
        }
        return "redirect:/server";
    }
}
