package com.klay.dao;

import java.util.List;
import com.klay.model.VideoLike;
import org.apache.ibatis.annotations.Param;

public interface VideoLikeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table video_like
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(@Param("userId") String userId, @Param("videoId") String videoId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table video_like
     *
     * @mbggenerated
     */
    int insert(VideoLike record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table video_like
     *
     * @mbggenerated
     */
    List<VideoLike> selectAll();
}