package com.klay.controller;

import com.klay.dao.VideoMapper;
import com.klay.model.Comment;
import com.klay.model.Video;
import com.klay.service.IndexService;
import com.klay.service.VideoService;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private IndexService indexService;
    @Autowired
    private VideoService videoService;
    @Autowired
    private VideoMapper videoMapper;

    @RequestMapping
    public String home(HttpSession session, Model model){
        model.addAttribute("username",session.getAttribute("sessionUsername"));
        session.removeAttribute("msg");
        session.removeAttribute("wrongId");
        return "index";
    }


    @ResponseBody
    @RequestMapping("/showAllVideo")
    public List<Video> index(){
        List<Video> videoList = this.indexService.getAllVideo();
        return videoList;
    }

    @RequestMapping("/showVideo")
    public String show(Model model, @RequestParam(value = "videoPath", required = false)String videoPath,@RequestParam(value = "videoId", required = false)String videoId,HttpSession session){
        session.removeAttribute("msg");
        session.removeAttribute("wrongId");
        List<Comment> commentList = this.videoService.getCommentByVideoId(videoId);
        model.addAttribute("videoPath",videoPath);
        model.addAttribute("videoId",videoId);
        model.addAttribute("commentList",commentList);
        model.addAttribute("username",session.getAttribute("sessionUsername"));
        if (videoId != null) {
            Video video = videoMapper.selectByPrimaryKey(videoId);
            if (video.getVideoWatchCount() == null) {
                video.setVideoWatchCount(1);
            }else{
                video.setVideoWatchCount(video.getVideoWatchCount() + 1);
            }
            int flag = videoMapper.updateByPrimaryKey(video);
        }
        return "video";
    }

    @RequestMapping("/category")
    public String category(Model model,HttpSession session,@RequestParam(value = "videoType", required = false)String videoType){
        session.removeAttribute("msg");
        session.removeAttribute("wrongId");
        List<Video> videoList = this.indexService.getVideoByVideoType(videoType);
        model.addAttribute("username",session.getAttribute("sessionUsername"));
        model.addAttribute("videoList",videoList);
        model.addAttribute("videoType",videoType);
        return "category";
    }

    @RequestMapping("/search")
    public String search(HttpServletRequest request, Model model, HttpSession session){
        String searchTitles = request.getParameter("searchTitle");
        List<Video> videoList = this.videoMapper.selectByUserVideoTitle(searchTitles);
        model.addAttribute("username",session.getAttribute("sessionUsername"));
        model.addAttribute("videoList",videoList);
        return "searchResults";
    }

}
