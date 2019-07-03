package com.apm.agent;

import java.io.IOException;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.LoaderClassPath;
import javassist.NotFoundException;

import com.apm.agent.common.JsonUtil;
import com.apm.agent.common.util.SourceCodeCreator;
import com.apm.agent.repository.UserBean;
import com.apm.agent.service.IUserService;
import com.apm.agent.service.UserServiceImpl;

public class TestJavaassist {

	public static void main(String[] args) throws NotFoundException, CannotCompileException, IOException, InstantiationException, IllegalAccessException {
			//testJavassist();
		IUserService a = new UserServiceImpl();
		System.out.println(a.getAllUser());	
//		testJosnUitl();
	}
	private static void testJosnUitl(){
		System.out.println(JsonUtil.toJson(new UserBean("a","bb")));
	}
	private static void testJavassist() throws NotFoundException,
			CannotCompileException, IOException, InstantiationException,
			IllegalAccessException {
		//		ClassPool pool = new ClassPool();
		//        pool.appendSystemPath();
		//        pool.insertClassPath(new LoaderClassPath(Thread.currentThread().getContextClassLoader()));
		//        CtClass ctClass = pool.get("com.apm.agent.Hello");
		//        String methodName = "say";
		//        CtMethod ctMethod= ctClass.getDeclaredMethod(methodName);
		//        ctMethod.setName(methodName+"$agent");
		//        CtMethod copyMethod = CtNewMethod.copy(ctMethod, methodName, ctClass,null);
		//        StringBuffer sbfBody = new StringBuffer();
		//        sbfBody.append("{\n System.out.println(\" before \");\n");
		//        sbfBody.append(methodName+"$agent"+"($$);\n");
		//        sbfBody.append("System.out.println(\" end1 \");\n");
		//        sbfBody.append("}");
		//        copyMethod.setBody(sbfBody.toString());
		//        ctClass.addMethod(copyMethod);
		//        ctClass.writeFile();
		//        Hello h = new Hello();
		//        h.say();
		       
		        ClassPool pool = new ClassPool();
		        pool.insertClassPath(new LoaderClassPath(Thread.currentThread().getContextClassLoader()));
		        CtClass ctClass = pool.get("com.apm.agent.Hello");
		        SourceCodeCreator.SourceCodeBuild build = new SourceCodeCreator.SourceCodeBuild("append",ctClass);
		        build.buildBeforeSrc("System.out.println(\" before \");").buildEndSrc("System.out.println(\" end \");").buildMethod();
		        Hello x = (Hello) ctClass.toClass().newInstance();
		//        x.say();
		       System.out.println(x.append("a","c"));
		//		ClassPool pool = new ClassPool();
		//		pool.appendSystemPath();
		//		// 定义类
		//		CtClass userServiceClass = pool.get("com.apm.agent.Hello");
		//		// 需要修改的方法
		//		CtMethod method = userServiceClass.getDeclaredMethod("say");
		//		// 修改原有的方法
		//		method.setName("say$agent");
		//		// 创建新的方法，复制原来的方法
		//		CtMethod newMethod = CtNewMethod.copy(method, "say", userServiceClass, null);
		//		// 注入的代码
		//		StringBuffer body = new StringBuffer();
		// 
		//		body.append("{\nlong start = System.currentTimeMillis();\n");
		//		// 调用原有代码，类似于method();($$)表示所有的参数
		//		body.append("say$agent($$);\n");
		//		body.append("System.out.println(\" take \" +\n (System.currentTimeMillis()-start) + " + "\" ms.\");\n");
		// 
		//		body.append("}");
		//		newMethod.setBody(body.toString());
		//		// 增加新方法
		//		userServiceClass.addMethod(newMethod);
		// 
		//		Hello userServiceImpl = (Hello) userServiceClass.toClass().newInstance();
		//		userServiceImpl.say();
	}
}
class Hello{
	public void say(){
		System.out.println("hello say1");
	}
	public void say(String name,String age){
		System.out.println("hello:"+name + "age is:"+age);
	}
	public String append(String name,String age){
		return name+age;
	}
}
