package com.apm.agent.collect.rule;

import javassist.CtClass;

import com.apm.agent.common.cons.AgentConst;
import com.apm.agent.common.util.AgentPropertiesUtil;

public class ClassNameCollectRule implements CollectRule{

	private final static String COLLECT_RULE_SERVICE_CLASSNAME = (String) AgentPropertiesUtil.getValue(AgentConst.COLLECT_RULE_SERVICE_CLASSNAME);
	@Override
	public boolean isNeedCollect(CtClass ctClass) {
		if(COLLECT_RULE_SERVICE_CLASSNAME==null ||
				ctClass==null){
			return false;
		}
		String ruleName = ctClass.getName();
		if(ruleName!=null &&
				ruleName.equals(COLLECT_RULE_SERVICE_CLASSNAME)){
			return true;
		}
		return false;
	}

}
