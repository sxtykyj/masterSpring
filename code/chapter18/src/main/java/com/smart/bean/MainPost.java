package com.smart.bean;

/**
 * Desc: 主题帖子
 *
 * @Author: yk
 * @Date: 2020/1/19 21:49
 */
public class MainPost extends Post {

    private static final long serialVersionUID = 8950615034697434945L;

    private int postType = 1;

    @Override
    public int getPostType() {
        return postType;
    }

    @Override
    public void setPostType(int postType) {
        this.postType = postType;
    }
}
