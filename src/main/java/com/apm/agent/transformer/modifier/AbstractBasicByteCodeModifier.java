package com.apm.agent.transformer.modifier;

import javassist.CtClass;

public class AbstractBasicByteCodeModifier implements ByteCodeModifier {

	@Override
	public byte[] transform(CtClass ctClass) {
		return null;
	}
	
}
