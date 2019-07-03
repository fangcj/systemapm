package com.apm.agent.collect.rule;

import javassist.CtClass;

import com.apm.agent.common.cons.AgentConst;
import com.apm.agent.common.util.AgentPropertiesUtil;

public class InterfaceNameCollectRule implements CollectRule{

	private final static String COLLECT_RULE_SERVICE_INTERFACE = 
			(String) AgentPropertiesUtil.getValue(AgentConst.COLLECT_RULE_SERVICE_INTERFACE);
	@Override
	public boolean isNeedCollect(CtClass ctClass) {
		if(COLLECT_RULE_SERVICE_INTERFACE==null ||
				ctClass==null){
			return false;
		}
		try{
			if(isContaineInterface(ctClass.getInterfaces(),COLLECT_RULE_SERVICE_INTERFACE)){
				return true;
			}
		}catch(Exception e){
			
		}
		return false;
	}
	
	private boolean isContaineInterface(CtClass[] ctClass,String interfaceName){
		return false;
	}

}
