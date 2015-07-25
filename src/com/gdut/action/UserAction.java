package com.gdut.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;

import com.gdut.po.User;
import com.gdut.service.UserService;

public class UserAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8361531054053894929L;
	private String username;
	private String password;
	@Resource
	private UserService userService;
	
	@Action(value="test")
	public String userTest(){
		User user = new User();
		user.setPassword(password);
		user.setUsername(username);
		Integer id = (Integer) userService.save(user);
		if(id != null){
			User u = userService.getById(id);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("user", u);
			return ajaxJsonByObjectMap(map);
		}
		return null;
			
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
