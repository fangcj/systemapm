package com.apm.agent.common.util;

import java.io.IOException;

import javassist.CannotCompileException;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.NotFoundException;

public class SourceCodeCreator {
	private final static String VOID_SOUCE="{ %s\n"+
			"try{\n"+
					"%s$agent($$);\n"+
			"}catch(Throwable e){\n"+
				"%s\n"+
				 "throw e;\n"+ 
			"}finally{\n"+
				"%s\n"+
			"}"+
			"}";
	private final static String RETURN_SOUCE="{ %s\n"+
			"Object result=null;\n"+
			"try{\n"+
					"result=($w)%s$agent($$);\n"+
			"}catch(Throwable e){\n"+
				"%s\n"+
				"throw e;\n"+ 
			"}finally{\n"+
				"%s\n"+
			"}\n"+
			"return ($r)result;"+
			"}";
			;
	
	public static void updMethod(String beforeSrc,String errSrc,String endSrc,String methodName,CtClass ctClass) throws NotFoundException, CannotCompileException, IOException{
	   CtMethod ctMethod= ctClass.getDeclaredMethod(methodName);
       CtMethod copyMethod = CtNewMethod.copy(ctMethod, methodName, ctClass,null);
       copyMethod.setName(methodName+"$agent");
       String template = ctMethod.getReturnType().getName().equals("void") ? VOID_SOUCE : RETURN_SOUCE;
       beforeSrc = beforeSrc==null?"":beforeSrc;
       errSrc = errSrc==null?"":errSrc;
       endSrc = endSrc==null?"":endSrc;
       ctClass.addMethod(copyMethod);
       ctMethod.setBody(String.format(template,beforeSrc, methodName,errSrc,endSrc));
	}
	public static class SourceCodeBuild{
		String methodName;
		CtClass ctClass;
		String beforeSrc;
		String errSrc;
		String endSrc;
		public SourceCodeBuild(String methodName,CtClass ctClass){
			this.methodName=methodName;
			this.ctClass=ctClass;
		}
		public SourceCodeBuild buildBeforeSrc(String beforeSrc){
			this.beforeSrc = beforeSrc;
			return this;
		}
		public SourceCodeBuild buildErrSrc(String errSrc){
			this.errSrc = errSrc;
			return this;
		}
		public SourceCodeBuild buildEndSrc(String endSrc){
			this.endSrc = endSrc;
			return this;
		}
		
		public void buildMethod() throws NotFoundException, CannotCompileException, IOException{
		   CtMethod ctMethod= ctClass.getDeclaredMethod(methodName);
	       CtMethod copyMethod = CtNewMethod.copy(ctMethod, methodName, ctClass,null);
	       copyMethod.setName(methodName+"$agent");
	       String template = ctMethod.getReturnType().getName().equals("void") ? VOID_SOUCE : RETURN_SOUCE;
	       beforeSrc = beforeSrc==null?"":beforeSrc;
	       errSrc = errSrc==null?"":errSrc;
	       endSrc = endSrc==null?"":endSrc;
	       ctClass.addMethod(copyMethod);
	       ctMethod.setBody(String.format(template,beforeSrc, methodName,errSrc,endSrc));
		}
	}
}
