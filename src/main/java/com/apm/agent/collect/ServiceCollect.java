package com.apm.agent.collect;

import com.apm.agent.model.ServiceStatistics;

public class ServiceCollect implements ICollect {
	public static ServiceCollect INSTANCE = new ServiceCollect();
	
	public ServiceCollect(){
	}
	@Override
	public ServiceStatistics start(String className, String methodName) {
		System.out.println("ServiceCollect start execute:");
		ServiceStatistics serviceStatistics = new ServiceStatistics();
		serviceStatistics.setBegin(System.currentTimeMillis());
		return serviceStatistics;
	}

	@Override
	public void end(ServiceStatistics serviceStatistics) {
		System.out.println("ServiceCollect end execute:");
		System.out.println("serviceStatistics setBegin execute:"+serviceStatistics.getBegin());
		serviceStatistics.setEnd(System.currentTimeMillis());
		System.out.println(serviceStatistics);
	}
}
