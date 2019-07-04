package com.apm.agent.transformer.modifier;

import javassist.CtClass;
import javassist.CtMethod;
import javassist.Modifier;

import com.apm.agent.common.util.SourceCodeCreator;

public class DefaultByteCodeModifier implements ByteCodeModifier {
	private static final String beginSrc;
    private static final String endSrc;
     
    static {
        StringBuilder sbuilder = new StringBuilder();
        sbuilder.append("com.apm.agent.collect.ServiceCollect instance= ");
        sbuilder.append("com.apm.agent.collect.ServiceCollect.INSTANCE;\r\n");
        sbuilder.append("com.apm.agent.model.ServiceStatistics statistic =instance.start(\"%s\",\"%s\");");
        beginSrc = sbuilder.toString();
        sbuilder = new StringBuilder();
        sbuilder.append("instance.end(statistic);");
        endSrc = sbuilder.toString();
    }
	@Override
	public byte[] transform(CtClass ctClass){
		byte[] sourceByteCode=null;
		try {
			if(ctClass.isInterface()){//如果是接口
				return null;
			}
			CtMethod[] methods = ctClass.getDeclaredMethods();
	        for (CtMethod m : methods) {
	        	// 屏蔽非公共方法
	            if (!Modifier.isPublic(m.getModifiers())) {
	                continue;
	            }
	            // 屏蔽静态方法
	            if (Modifier.isStatic(m.getModifiers())) {
	                continue;
	            }
	            // 屏蔽本地方法
	            if (Modifier.isNative(m.getModifiers())) {
	                continue;
	            }
	            SourceCodeCreator.SourceCodeBuild build = new SourceCodeCreator.SourceCodeBuild(m.getName(),ctClass);
	            build.buildBeforeSrc(beginSrc).buildEndSrc(endSrc).buildMethod();
	        }
	        sourceByteCode = ctClass.toBytecode();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return sourceByteCode;
	}
}
