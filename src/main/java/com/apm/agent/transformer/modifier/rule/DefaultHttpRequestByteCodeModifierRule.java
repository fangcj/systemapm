package com.apm.agent.transformer.modifier.rule;

import javassist.CtClass;

import com.apm.agent.common.cons.AgentConst;
import com.apm.agent.common.util.AgentPropertiesUtil;
import com.apm.agent.transformer.modifier.ByteCodeModifier;
import com.apm.agent.transformer.modifier.DefaultHttpRequestByteCodeModifier;

public class DefaultHttpRequestByteCodeModifierRule implements IModifierRule{

	private final static String MODIFIER_HTTPREQUEST_RULE_INTERFACE = (String) AgentPropertiesUtil.getValue(AgentConst.MODIFIER_HTTPREQUEST_RULE_INTERFACE);
	DefaultHttpRequestByteCodeModifier INSTANCE = new DefaultHttpRequestByteCodeModifier();
	@Override
	public ByteCodeModifier chooseModifier(CtClass ctClass) {
		if(MODIFIER_HTTPREQUEST_RULE_INTERFACE==null ||
				ctClass==null){
			return null;
		}
		if(ctClass.getName().equals(MODIFIER_HTTPREQUEST_RULE_INTERFACE)){
			return INSTANCE;
		}
		
		return null;
	}

}
