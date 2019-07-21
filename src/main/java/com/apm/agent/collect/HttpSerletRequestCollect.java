package com.apm.agent.collect;

import com.apm.agent.common.util.AgentReflectUtil;
import com.apm.agent.model.ServiceStatistics;

import java.util.Map;

public class HttpSerletRequestCollect extends AbstractCollect implements ICollect {
	public static HttpSerletRequestCollect INSTANCE = new HttpSerletRequestCollect();
	
	public HttpSerletRequestCollect collectRequestParameter(Object requestObj,ServiceStatistics serviceStatistics){
		AgentReflectUtil wrapperequestObj = new AgentReflectUtil(requestObj);
		Map<String,String[]> parameters = wrapperequestObj.getRequestParameters();
		serviceStatistics.setRequestParameters(parameters);
	}

	@Override
	public ServiceStatistics start(String className, String methodName) {
		System.out.println("HttpSerletRequest start execute:");
		ServiceStatistics serviceStatistics = new ServiceStatistics();
		serviceStatistics.setBegin(System.currentTimeMillis());
		return serviceStatistics;
	}

	@Override
	public void end(ServiceStatistics serviceStatistics) {
		System.out.println("HttpSerletRequest end execute:");
		System.out.println("HttpSerletRequest execute:"+serviceStatistics.getBegin());
		serviceStatistics.setEnd(System.currentTimeMillis());
		System.out.println(serviceStatistics);
	}
	@Override
	public void err(ServiceStatistics serviceStatistics) {
		// TODO Auto-generated method stub
		
	}
}
