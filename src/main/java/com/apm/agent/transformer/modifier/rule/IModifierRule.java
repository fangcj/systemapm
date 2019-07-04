package com.apm.agent.transformer.modifier.rule;

import com.apm.agent.transformer.modifier.ByteCodeModifier;

import javassist.CtClass;

public interface IModifierRule{
	public ByteCodeModifier chooseModifier(CtClass ctClass);
}
