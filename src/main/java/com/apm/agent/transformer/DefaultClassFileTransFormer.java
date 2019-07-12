package com.apm.agent.transformer;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.LoaderClassPath;
import javassist.NotFoundException;

import com.apm.agent.ApmContext;
import com.apm.agent.filter.ApmFilter;
import com.apm.agent.transformer.modifier.ByteCodeModifier;
import com.apm.agent.transformer.modifier.rule.IModifierRule;

public class DefaultClassFileTransFormer implements ClassFileTransformer{
	protected static Map<ClassLoader, ClassPool> classPoolMap = new ConcurrentHashMap<ClassLoader, ClassPool>();
    protected ApmContext apmContext =null;
	
    public DefaultClassFileTransFormer(ApmContext apmContext){
    	this.apmContext = apmContext;
    }
	@Override
	public byte[] transform(ClassLoader loader, String className,
			Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
			byte[] classfileBuffer) throws IllegalClassFormatException {
		try{
			if(!doFilters(className)){
				return null;
			}
			System.out.println("AbstractClassFileTransFormer execute transform:"+loader+":"+className);
			CtClass ctClass = toCtClass(loader,className);
			if(ctClass==null){
				return null;
			}
			if(ctClass.isInterface()){//如果是接口
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
	protected boolean doFilters(String className){
		for(ApmFilter apmFilter : apmContext.getApmFilters()){
			if(!apmFilter.doFilter(className.replaceAll("/", "."))){
				return false;
			}
		}
		return true;
	}
	private ByteCodeModifier chooseByteCodeModifier(CtClass ctClass){
		ByteCodeModifier chooseModifier = null;
		for(IModifierRule iModifierRule : apmContext.getModifierRules()){
			chooseModifier = iModifierRule.chooseModifier(ctClass);
			if(chooseModifier!=null){
				apmContext.registerByteCodeModifier(chooseModifier);
				break;
			}
		}
		return chooseModifier;
	}   
	
	private CtClass toCtClass(ClassLoader loader, String className) throws NotFoundException {
		if(loader==null){
			return null;
		}
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
