package com.klay.service;

import com.klay.model.Video;

import java.util.List;

public interface IndexService {

    public List<Video> getAllVideo();
    public List<Video> getVideoByVideoType(String videoType);
}
