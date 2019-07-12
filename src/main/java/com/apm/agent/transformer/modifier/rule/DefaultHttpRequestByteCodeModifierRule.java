package com.apm.agent.transformer.modifier.rule;

import javassist.CtClass;

import com.apm.agent.common.cons.AgentConst;
import com.apm.agent.common.util.AgentPropertiesUtil;
import com.apm.agent.transformer.modifier.ByteCodeModifier;
import com.apm.agent.transformer.modifier.DefaultHttpRequestByteCodeModifier;

public class DefaultHttpRequestByteCodeModifierRule implements IModifierRule{

	private final static String MODIFIER_HTTPREQUEST_RULE_SUPERCLASSNAME = (String) AgentPropertiesUtil.getValue(AgentConst.MODIFIER_HTTPREQUEST_RULE_SUPERCLASSNAME);
	DefaultHttpRequestByteCodeModifier INSTANCE = new DefaultHttpRequestByteCodeModifier();
	@Override
	public ByteCodeModifier chooseModifier(CtClass ctClass) {
		if(MODIFIER_HTTPREQUEST_RULE_SUPERCLASSNAME==null ||
				ctClass==null){
			return null;
		}
		try{
			String superClass = ctClass.getSuperclass().getName();
			if(superClass.equals(MODIFIER_HTTPREQUEST_RULE_SUPERCLASSNAME)){
				return INSTANCE;
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return null;
	}

}
