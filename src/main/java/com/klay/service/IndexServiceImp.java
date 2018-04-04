package com.klay.service;

import com.klay.dao.VideoMapper;
import com.klay.model.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("indexService")
public class IndexServiceImp  implements IndexService{


    @Autowired
    private VideoMapper videoMapper;

    @Override
    public List<Video> getAllVideo() {
        return videoMapper.selectAll();
    }

    @Override
    public List<Video> getVideoByVideoType(String videoType) {

        return  videoMapper.selectByVideoType(videoType);
    }
}
