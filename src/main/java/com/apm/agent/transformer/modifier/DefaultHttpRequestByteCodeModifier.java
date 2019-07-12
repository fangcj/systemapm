package com.apm.agent.transformer.modifier;

import javassist.CtClass;
import javassist.CtMethod;

import com.apm.agent.common.cons.AgentConst;
import com.apm.agent.common.util.SourceCodeCreator;

public class DefaultHttpRequestByteCodeModifier implements ByteCodeModifier {
	private static final String beginSrc;
    private static final String endSrc;
     
    static {
        StringBuilder sbuilder = new StringBuilder();
        sbuilder.append("com.apm.agent.collect.HttpSerletRequestCollect instance= ");
        sbuilder.append("com.apm.agent.collect.HttpSerletRequestCollect.INSTANCE;\r\n");
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
			// 继承了httpServlet的数据
			if(AgentConst.SERVLET_SUPERCLASS_NAME.equals(ctClass.getSuperclass().getName())){
				CtMethod doGetMethod = ctClass.getDeclaredMethod("doGet");
				if(doGetMethod!=null){
					 SourceCodeCreator.SourceCodeBuild build = new SourceCodeCreator.SourceCodeBuild(doGetMethod.getName(),ctClass);
			            build.buildBeforeSrc(beginSrc).buildEndSrc(endSrc).buildServletMethod();
				}
				CtMethod doPostMethod = ctClass.getDeclaredMethod("doPost");
				if(doPostMethod!=null){
					 SourceCodeCreator.SourceCodeBuild build = new SourceCodeCreator.SourceCodeBuild(doPostMethod.getName(),ctClass);
			            build.buildBeforeSrc(beginSrc).buildEndSrc(endSrc).buildServletMethod();
				}
			}
	        sourceByteCode = ctClass.toBytecode();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return sourceByteCode;
	}
}
