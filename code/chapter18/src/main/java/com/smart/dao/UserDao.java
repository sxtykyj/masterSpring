package com.smart.dao;


import com.smart.bean.User;

import java.util.List;
/**
 * @Author: yk
 * @Date: 2020/1/30 13:03
 */
public interface UserDao {

	User get(int id);
	
	User getUserByUserName(String userName);

	List<User> queryUserByUserName(String userName);
	
	List<User> list();
	
	void save(User user);
	
	void update(User user);
}
