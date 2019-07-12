package com.apm.agent.transformer.modifier.rule;

import javassist.CtClass;

import com.apm.agent.common.cons.AgentConst;
import com.apm.agent.common.util.AgentPropertiesUtil;
import com.apm.agent.transformer.modifier.ByteCodeModifier;
import com.apm.agent.transformer.modifier.DefaultBeanByteCodeModifier;

public class DefaultAnnotationModifierRule implements IModifierRule{

	private final static String MODIFIER_DEFAULT_RULE_ANNOTATION = (String) AgentPropertiesUtil.getValue(AgentConst.MODIFIER_DEFAULT_RULE_ANNOTATION);
	DefaultBeanByteCodeModifier INSTANCE = new DefaultBeanByteCodeModifier();
	@Override
	public ByteCodeModifier chooseModifier(CtClass ctClass) {
		if(MODIFIER_DEFAULT_RULE_ANNOTATION==null ||
				ctClass==null){
			return null;
		}
		try {
			Object[] annotations = ctClass.getAnnotations();
			if(annotations!=null){
				for(Object annotation :annotations){
					if(annotation.toString().contains(MODIFIER_DEFAULT_RULE_ANNOTATION)){
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
