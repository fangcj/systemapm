package com.apm.agent.collect;

import com.apm.agent.model.ServiceStatistics;

public interface ICollect{
	public ServiceStatistics start(String className, String methodName);
	public void err(ServiceStatistics serviceStatistics);
	public void end(ServiceStatistics serviceStatistics);
}
