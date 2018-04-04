package com.klay.model;

public class Comment {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment.user_id
     *
     * @mbggenerated
     */
    private String userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment.video_id
     *
     * @mbggenerated
     */
    private String videoId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment.value
     *
     * @mbggenerated
     */
    private String value;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comment.id
     *
     * @return the value of comment.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment.id
     *
     * @param id the value for comment.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comment.user_id
     *
     * @return the value of comment.user_id
     *
     * @mbggenerated
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment.user_id
     *
     * @param userId the value for comment.user_id
     *
     * @mbggenerated
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comment.video_id
     *
     * @return the value of comment.video_id
     *
     * @mbggenerated
     */
    public String getVideoId() {
        return videoId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment.video_id
     *
     * @param videoId the value for comment.video_id
     *
     * @mbggenerated
     */
    public void setVideoId(String videoId) {
        this.videoId = videoId == null ? null : videoId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comment.value
     *
     * @return the value of comment.value
     *
     * @mbggenerated
     */
    public String getValue() {
        return value;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment.value
     *
     * @param value the value for comment.value
     *
     * @mbggenerated
     */
    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }
}