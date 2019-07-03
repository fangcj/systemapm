package com.apm.agent.transformer;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.LoaderClassPath;
import javassist.NotFoundException;

import com.apm.agent.transformer.strategy.TransformerStrategy;

public abstract class AbastractTransFormer implements ClassFileTransformer{
    private static Map<ClassLoader, ClassPool> classPoolMap = new ConcurrentHashMap<ClassLoader, ClassPool>();
	// 选取哪一个transformer
	private TransformerStrategy transformerStrategy = new TransformerStrategy();
	public AbastractTransFormer(Map<String,String> strategyBeanMap){
		TransformerStrategy.loadStrategy(strategyBeanMap);
	}
	public AbastractTransFormer(){
		registerTransFormerStrategy();
	}
	@Override
	public byte[] transform(ClassLoader loader, String className,
			Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
			byte[] classfileBuffer) throws IllegalClassFormatException {
		System.out.println("AbastractTransFormer execute transform");
		AbastractTransFormer transFormer = transformerStrategy.chooseTransFormer(className.replace("/", "."));
		if(transFormer!=null){
			return transFormer.transform(loader, className);
		}
		System.out.println("AbastractTransFormer end transform");
		return null;
	}
	/**
	 * 加载选择transformer的策略
	 */
	private void registerTransFormerStrategy() {
		Map<String,String> strategyBeanMap = new HashMap<String, String>();
		strategyBeanMap.put("com.apm.agent.service.UserServiceImpl", "com.apm.agent.transformer.ServiceTransFormer");
		TransformerStrategy.loadStrategy(strategyBeanMap);
	}
	abstract byte[] transform(ClassLoader loader, String className) throws IllegalClassFormatException ;
	
	protected static CtClass toCtClass(ClassLoader loader, String className) throws NotFoundException {
        if (!classPoolMap.containsKey(loader)) {
            ClassPool classPool = new ClassPool();
            classPool.insertClassPath(new LoaderClassPath(loader));
            classPoolMap.put(loader, classPool);
        }
        ClassPool cp = classPoolMap.get(loader);
        className = className.replaceAll("/", ".");
        return cp.get(className);
    }
}
