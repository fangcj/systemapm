package com.apm.agent.common.util;

import java.io.IOException;
import java.util.Properties;

public class AgentPropertiesUtil {
	
	private static Properties pro = new Properties();
	static{
		try {
			pro.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("agent_config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Object getValue(String key){
		return pro.get(key);
	}
}
