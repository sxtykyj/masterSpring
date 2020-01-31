package com.smart.bean;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * Desc: 主题
 *
 * @Author: yk
 * @Date: 2020/1/19 21:48
 */
public class Topic implements Serializable {

    private static final long serialVersionUID = 6632595094514289741L;


    /**
     * 精华主题帖子
     */
    public static final int DIGEST_TOPIC = 1;
    /**
     * 普通的主题帖子
     */
    public static final int NOT_DIGEST_TOPIC = 0;

    private Integer topicId;
    private String topicTitle;
    private User user;
    private Integer boardId;
    private MainPost mainPost = new MainPost();
    private Date lastPost = new Date();
    private Date createTime = new Date();
    private Integer views;
    private Integer replies;
    private Integer digest = NOT_DIGEST_TOPIC;

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getBoardId() {
        return boardId;
    }

    public void setBoardId(Integer boardId) {
        this.boardId = boardId;
    }

    public MainPost getMainPost() {
        return mainPost;
    }

    public void setMainPost(MainPost mainPost) {
        this.mainPost = mainPost;
    }

    public Date getLastPost() {
        return lastPost;
    }

    public void setLastPost(Date lastPost) {
        this.lastPost = lastPost;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Integer getReplies() {
        return replies;
    }

    public void setReplies(Integer replies) {
        this.replies = replies;
    }

    public Integer getDigest() {
        return digest;
    }

    public void setDigest(Integer digest) {
        this.digest = digest;
    }

    public Topic() {
        super();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("topicId", topicId)
                .append("topicTitle", topicTitle)
                .append("user", user)
                .append("boardId", boardId)
                .append("mainPost", mainPost)
                .append("lastPost", lastPost)
                .append("createTime", createTime)
                .append("views", views)
                .append("replies", replies)
                .append("digest", digest)
                .toString();
    }
}
