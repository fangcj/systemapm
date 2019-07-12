package com.service;

import com.apm.agent.repository.UserBean;

public interface IUserService {
	void sayHello();
	String getAllUser() throws RuntimeException;
	String getUser(String userId);
	String addUser(UserBean userBean);
	String updUser(String userId,String userName);
}
