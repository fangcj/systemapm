package com.apm.agent.service;

import com.apm.agent.repository.UserBean;

public interface IUserService {
	void sayHello();
	String getAllUser();
	String getUser(String userId);
	String addUser(UserBean userBean);
	String updUser(String userId,String userName);
}
