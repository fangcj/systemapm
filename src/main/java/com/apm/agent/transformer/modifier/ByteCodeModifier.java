package com.apm.agent.transformer.modifier;

import javassist.CtClass;

public interface ByteCodeModifier {
	byte[] transform(CtClass ctClass);
}
