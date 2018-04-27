package com.dxc.pai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.pai.dao.UserMapper;
import com.dxc.pai.model.User;

@Service("userService")
public class UserService {
	private String serName;
	public UserService() {}
	public UserService(String serName) {
		this.serName = serName;
	}
	
	public String getSer() {
		return this.serName;
	}
	
	@Autowired
	private UserMapper userMapper;
	
	public int addUser(User user) {
		return userMapper.insert(user);
	}
	
	public List<User> getAllUser(){
		return userMapper.selectAll();
	}
	
	public User getUserById(int id) {
		return userMapper.selectByPrimaryKey(id);
	}
	
	public int updateByPrimaryKey(User user) {
		return userMapper.updateByPrimaryKey(user);
	}
	
	public int deleteByPrimaryKey(int id) {
		return userMapper.deleteByPrimaryKey(id);
	}
}
