package com.klay.service;

import com.klay.dao.CommentMapper;
import com.klay.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("videoService")
public class VideoServiceImp implements VideoService {

    @Autowired
    CommentMapper commentMapper;

    @Override
    public List<Comment> getCommentByVideoId(String videoId) {
        return commentMapper.selectByVideoId(videoId);
    }
}
