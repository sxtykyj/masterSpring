package com.smart.dao;



import com.smart.bean.Board;

import java.util.List;

/**
 * @Author: yk
 * @Date: 2020/1/30 13:03
 */

public interface BoardDao {
	
	Board get(int id);
	
	long getBoardNum();
	
	void update(Board board);
	
	void save(Board board);
	
	void delete(int id);
	
	List<Board> list();
}
