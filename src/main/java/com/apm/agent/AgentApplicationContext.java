package com.apm.agent;

import java.lang.instrument.Instrumentation;

import com.apm.agent.transformer.ServiceTransFormer;

public class AgentApplicationContext {
	private Instrumentation instrumentation;
	private String opts;

	public AgentApplicationContext(String opts,Instrumentation instrumentation){
		this.opts = opts;
		this.instrumentation = instrumentation;
	}
	
	public void init(){
		System.out.println("opts is:"+opts);
		instrumentation.addTransformer(new ServiceTransFormer());
	}
}
