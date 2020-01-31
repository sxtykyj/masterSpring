package com.smart.dao;

import org.apache.ibatis.annotations.Param;

/**
 * @Author: yk
 * @Date: 2020/1/30 13:03
 */
public interface BoardManagerDao {
	/**
	 * 这里为什么要给方法的参数添加@Param注解呢？
	 * 是因为该方法有两个或以上的参数，一定要加，不然mybatis识别不了。
	 * 若方法只有一个参数，可以不用加 @Param注解
	 * @param userId
	 * @param boardId
	 */
	
	void save(@Param("userId") int userId, @Param("boardId")int boardId);
}
