package com.apm.agent.filter;

import com.apm.agent.common.cons.AgentConst;
import com.apm.agent.common.util.AgentPropertiesUtil;

public class DefaultApmFilter implements ApmFilter{
	private final static String MODIFY_BASE_PACKAGE = (String) AgentPropertiesUtil.getValue(AgentConst.MODIFY_BASE_PACKAGE);

	@Override
	public boolean doFilter(String className) {
		for(String scanName : MODIFY_BASE_PACKAGE.split(",")){
			if(className.startsWith(scanName)){
				return true;
			}
		}
		return false;
	}

}
