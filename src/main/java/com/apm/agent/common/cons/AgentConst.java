package com.apm.agent.common.cons;

public class AgentConst {
	
	//httpRequestServlet的收集规则
	public final static String MODIFY_BASE_PACKAGE="modify.base.package";
	// 默认bean的收集规则
	//收集规则基于接口
	public final static String MODIFIER_DEFAULT_RULE_INTERFACE="modifier.default.rule.interface";
	//收集规则基于类名
	public final static String MODIFIER_DEFAULT_RULE_CLASSNAME="modifier.default.rule.className";
	//收集规则基于注解
	public final static String MODIFIER_DEFAULT_RULE_ANNOTATION="modifier.default.rule.annotation";
	//收集规则基于正则表达式
	public final static String MODIFIER_DEFAULT_RULE_REGEXPRESS="modifier.default.rule.regExpress";
	
	//httpRequestServlet的收集规则
	public final static String MODIFIER_HTTPREQUEST_RULE_SUPERCLASSNAME="modifier.httprequest.rule.superclassName";
	//httpRequestServlet的收集规则
	public final static String SERVLET_SUPERCLASS_NAME="javax.servlet.http.HttpServlet";
	//httpRequestServlet的收集规则
	public final static String SERVLET_INTERCEPTOR_GET="doGet";
	//httpRequestServlet的收集规则
	public final static String SERVLET_INTERCEPTOR_POST="doPost";
	//获取requestparameters的方法
	public final static String REQUEST_METHOD_GETPARAMETERMAP="getParameterMap";

}
