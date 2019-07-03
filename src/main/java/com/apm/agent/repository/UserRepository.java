package com.apm.agent.repository;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {
	
	public static Map<String,UserBean> users= new HashMap<String,UserBean>();
	static{
		users.put("user1", new UserBean("user1","tomcat"));
		users.put("user2", new UserBean("user2","jack"));
	}

}
