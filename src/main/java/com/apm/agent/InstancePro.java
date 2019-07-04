package com.apm.agent;


public class InstancePro{

	public static <T> T newInstance(Class<T> cls){
		T obj = null;
		try {
			obj = cls.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}
}
