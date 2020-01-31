package com.smart.dao;

import com.smart.bean.Post;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * @Author: yk
 * @Date: 2020/1/30 13:03
 */
public interface PostDao {
	
	List<Post> listByTopicId(@Param("topicId") int topicId, @Param("startIndex") int startIndex, @Param("times") int times);
	
	long countByTopicId(int topicId);
	
	void deleteTopicPosts(int topicId);
	
	void save(Post post);
	
	void delete(int id);
	
	Post get(int id);
}
