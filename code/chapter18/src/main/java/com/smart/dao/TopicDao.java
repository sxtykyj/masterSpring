package com.smart.dao;



import com.smart.bean.Topic;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * @Author: yk
 * @Date: 2020/1/30 13:03
 */
public interface TopicDao {
	
	void save(Topic topic);
	
	Topic get(int id);
	
	void remove(int id);
	
	void update(Topic topic);
	
	List<Topic> listByBoardId(@Param("boardId")int boardId, @Param("start")int start, @Param("count")int count);
	
	long countByBoardId(int boardId);
	
	List<Topic> listByTitle(@Param("title")String title, @Param("start")int start, @Param("count")int count);
	
	long countByTitle(String title);
}
