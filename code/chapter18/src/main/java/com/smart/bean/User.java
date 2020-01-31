package com.smart.bean;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: yk
 * @Date: 2020/1/19 20:40
 */
public class User implements Serializable {
    private static final long serialVersionUID = -6569424992195903862L;

    /**
     * 锁定用户对应的状态值
     */
    public static final int USER_LOCK = 1;
    /**
     * 用户解锁对应的状态值
     */
    public static final int USER_UNLOCK = 0;
    /**
     * 管理员类型
     */
    public static final int FORUM_ADMIN = 2;
    /**
     * 普通用户类型
     */
    public static final int NORMAL_USER = 1;

    private Integer userId;
    private String userName;
    private int userType = NORMAL_USER;
    private String lastIp;
    private Date lastVisit;
    private String password;
    private Integer locked;
    private Integer credit;
    private Set<Board> manBoards = new HashSet<>();

    public static int getUserLock() {
        return USER_LOCK;
    }

    public static int getUserUnlock() {
        return USER_UNLOCK;
    }

    public static int getForumAdmin() {
        return FORUM_ADMIN;
    }

    public static int getNormalUser() {
        return NORMAL_USER;
    }


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getLastIp() {
        return lastIp;
    }

    public void setLastIp(String lastIp) {
        this.lastIp = lastIp;
    }

    public Date getLastVisit() {
        return lastVisit;
    }

    public void setLastVisit(Date lastVisit) {
        this.lastVisit = lastVisit;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getLocked() {
        return locked;
    }

    public void setLocked(Integer locked) {
        this.locked = locked;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public Set<Board> getManBoards() {
        return manBoards;
    }

    public void setManBoards(Set<Board> manBoards) {
        this.manBoards = manBoards;
    }

    public User() {
        super();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("userId", userId)
                .append("userName", userName)
                .append("userType", userType)
                .append("lastIp", lastIp)
                .append("lastVisit", lastVisit)
                .append("password", password)
                .append("locked", locked)
                .append("credit", credit)
                .append("manBoards", manBoards)
                .toString();
    }
}
