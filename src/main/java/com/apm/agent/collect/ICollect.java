package com.apm.agent.collect;

import javassist.CtClass;

import com.apm.agent.model.ServiceStatistics;

public interface ICollect {
	public ServiceStatistics start(String className, String methodName);
	public void end(ServiceStatistics serviceStatistics);
}
