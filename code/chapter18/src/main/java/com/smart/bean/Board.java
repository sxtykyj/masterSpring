package com.smart.bean;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: yk
 * @Date: 2020/1/19 20:35
 */
public class Board implements Serializable {

    private static final long serialVersionUID = 4792431293041848437L;

    private Integer boardId;
    private String boardName;
    private String boardDesc;
    private Integer topicNum ;
    private Set<User> users = new HashSet<>();


    public Integer getBoardId() {
        return boardId;
    }

    public void setBoardId(Integer boardId) {
        this.boardId = boardId;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public String getBoardDesc() {
        return boardDesc;
    }

    public void setBoardDesc(String boardDesc) {
        this.boardDesc = boardDesc;
    }

    public Integer getTopicNum() {
        return topicNum;
    }

    public void setTopicNum(Integer topicNum) {
        this.topicNum = topicNum;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Board() {
        super();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("boardId", boardId)
                .append("boardName", boardName)
                .append("boardDesc", boardDesc)
                .append("topicNum", topicNum)
                .append("users", users)
                .toString();
    }
}
