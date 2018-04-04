package com.klay.service;

import com.klay.model.Comment;

import java.util.List;

public interface VideoService {
    public List<Comment> getCommentByVideoId(String videoId);
}
