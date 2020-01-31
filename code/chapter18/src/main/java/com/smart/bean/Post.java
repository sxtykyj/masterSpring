package com.smart.bean;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * Desc: 帖子
 *
 * @Author: yk
 * @Date: 2020/1/19 21:47
 */
public class Post implements Serializable {

    private static final long serialVersionUID = 4517736543901157221L;

    private Integer postId;
    private String postTitle;
    private String postText;
    private int postType = 2;
    private Integer boardId;
    private Date createTime;
    private User user;
    private Topic topic;

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public int getPostType() {
        return postType;
    }

    public void setPostType(int postType) {
        this.postType = postType;
    }

    public Integer getBoardId() {
        return boardId;
    }

    public void setBoardId(Integer boardId) {
        this.boardId = boardId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Post() {
        super();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("postId", postId)
                .append("postTitle", postTitle)
                .append("postText", postText)
                .append("postType", postType)
                .append("boardId", boardId)
                .append("createTime", createTime)
                .append("user", user)
                .append("topic", topic)
                .toString();
    }
}
