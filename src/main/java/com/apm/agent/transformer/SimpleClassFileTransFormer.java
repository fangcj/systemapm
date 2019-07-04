package com.apm.agent.transformer;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.LoaderClassPath;
import javassist.NotFoundException;

import com.apm.agent.ApmContext;
import com.apm.agent.transformer.modifier.ByteCodeModifier;
import com.apm.agent.transformer.modifier.rule.IModifierRule;

public class SimpleClassFileTransFormer extends AbstractClassFileTransFormer{
	
	public SimpleClassFileTransFormer(ApmContext apmContext) {
		this.apmContext = apmContext;
	}
	
	@Override
	protected ByteCodeModifier chooseByteCodeModifier(CtClass ctClass){
		ByteCodeModifier chooseModifier = null;
		for(IModifierRule iModifierRule : apmContext.getModifierRules()){
			chooseModifier = iModifierRule.chooseModifier(ctClass);
			apmContext.registerByteCodeModifier(chooseModifier);
		}
		return chooseModifier;
	}   
	
	protected CtClass toCtClass(ClassLoader loader, String className) throws NotFoundException {
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
