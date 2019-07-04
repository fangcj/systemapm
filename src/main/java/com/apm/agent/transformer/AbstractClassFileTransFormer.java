package com.apm.agent.transformer;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;

import com.apm.agent.ApmContext;
import com.apm.agent.transformer.modifier.ByteCodeModifier;

public abstract class AbstractClassFileTransFormer implements ClassFileTransformer{
	protected static Map<ClassLoader, ClassPool> classPoolMap = new ConcurrentHashMap<ClassLoader, ClassPool>();
    protected ApmContext apmContext =null;
	
	@Override
	public byte[] transform(ClassLoader loader, String className,
			Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
			byte[] classfileBuffer) throws IllegalClassFormatException {
		System.out.println("AbstractClassFileTransFormer execute transform:"+loader+":"+className);
		try{
			CtClass ctClass = toCtClass(loader,className);
			if(ctClass==null){
				return null;
			}
			ByteCodeModifier byteCodeModifier = chooseByteCodeModifier(ctClass);
			if(byteCodeModifier!=null){
				return byteCodeModifier.transform(ctClass);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	protected abstract CtClass toCtClass(ClassLoader loader, String className) throws NotFoundException ;
	
	protected abstract ByteCodeModifier chooseByteCodeModifier(CtClass ctClass);
}
