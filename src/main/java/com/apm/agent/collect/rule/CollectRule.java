package com.apm.agent.collect.rule;

import javassist.CtClass;

public interface CollectRule{
	public boolean isNeedCollect(CtClass ctClass);
}
