package com.apm.agent.transformer.modifier.rule;

import javassist.CtClass;

import com.apm.agent.common.cons.AgentConst;
import com.apm.agent.common.util.AgentPropertiesUtil;
import com.apm.agent.transformer.modifier.ByteCodeModifier;
import com.apm.agent.transformer.modifier.DefaultByteCodeModifier;

public class DefaultAnnotationModifierRule implements IModifierRule{

	private final static String MODIFIER_DEFAULT_RULE_INTERFACE = (String) AgentPropertiesUtil.getValue(AgentConst.MODIFIER_DEFAULT_RULE_INTERFACE);
	DefaultByteCodeModifier INSTANCE = new DefaultByteCodeModifier();
	@Override
	public ByteCodeModifier chooseModifier(CtClass ctClass) {
		if(MODIFIER_DEFAULT_RULE_INTERFACE==null ||
				ctClass==null){
			return null;
		}
		try {
			Object[] annotations = ctClass.getAnnotations();
			if(annotations!=null){
				for(Object annotation :annotations){
					if(annotation.toString().contains(MODIFIER_DEFAULT_RULE_INTERFACE)){
						return INSTANCE;
					}
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
