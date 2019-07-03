package com.apm.agent.collect;

import javassist.CtClass;

import com.apm.agent.model.ServiceStatistics;

public interface ICollect {
	public boolean isNeedCollect(CtClass ctClass);
	public ServiceStatistics start(String className, String methodName);
	public void end(ServiceStatistics serviceStatistics);
}
