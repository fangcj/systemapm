package com.service;

import com.apm.agent.repository.UserBean;

@TestAgent
public class UserServiceImpl implements IUserService{

	@Override
	public void sayHello() {
		System.out.println("say hello world");
	}

	@Override
	public String getAllUser() throws RuntimeException{
		System.out.println("hahah");
		return null;
	}

	@Override
	public String getUser(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addUser(UserBean userBean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updUser(String userId, String userName) {
		// TODO Auto-generated method stub
		return null;
	}

}
