package com.apm.agent.collect;

import com.apm.agent.model.ServiceStatistics;

import java.lang.reflect.Method;
import java.util.Map;

public class ServiceCollect extends com.apm.agent.collect.AbstractCollect implements com.apm.agent.collect.ICollect {
	public static ServiceCollect INSTANCE = new ServiceCollect();
	
	public ServiceCollect(){
	}
	private void getRequestInfo(Object obj){
		try {
			Method method = obj.getClass().getMethod("getParameterMap");
			Map<String, String[]> parameterMap = (Map<String, String[]>) method.invoke(obj);
			com.apm.agent.common.JsonUtil.toJson(parameterMap);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	@Override
	public ServiceStatistics start(String className, String methodName) {
		System.out.println("ServiceCollect start execute:");

		ServiceStatistics serviceStatistics = new ServiceStatistics();
		serviceStatistics.setBegin(System.currentTimeMillis());
		serviceStatistics.setServiceName(className);
		serviceStatistics.setMethodName(methodName);
		return serviceStatistics;
	}

	@Override
	public void end(ServiceStatistics serviceStatistics) {
		System.out.println("ServiceCollect end execute:");
		System.out.println("serviceStatistics setBegin execute:"+serviceStatistics.getBegin());
		serviceStatistics.setEnd(System.currentTimeMillis());
		System.out.println(serviceStatistics);
	}
	@Override
	public void err(ServiceStatistics serviceStatistics) {
		// TODO Auto-generated method stub
		
	}
}
