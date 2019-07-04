package com.apm.agent;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.apm.agent.collect.ICollect;
import com.apm.agent.collect.ServiceCollect;

public class ProxyInstance implements InvocationHandler{
	public Object obj = null;
	public ProxyInstance(){
	}
	public Object getInstance(Class<?> cls){
		try {
			obj = cls.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return Proxy.newProxyInstance(cls.getClassLoader(), 
				cls.getInterfaces(), this);
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		method.invoke(obj, args);
		return null;
	}
	public static void main(String[] args) {
		ProxyInstance proxyInstance = new ProxyInstance();
		ICollect iCollect = (ICollect)proxyInstance.getInstance(ServiceCollect.class);
		iCollect.start("1", "2");
	}
}
