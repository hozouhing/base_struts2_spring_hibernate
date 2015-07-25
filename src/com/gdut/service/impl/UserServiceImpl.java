package com.gdut.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gdut.dao.UserDao;
import com.gdut.po.User;
import com.gdut.service.UserService;

@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

	@Resource
	public void setBaseDao(UserDao userDao){
		super.setBaseDao(userDao);
	}
	
}
