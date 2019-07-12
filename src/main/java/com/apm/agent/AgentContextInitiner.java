package com.apm.agent;

import java.lang.instrument.Instrumentation;

import com.apm.agent.filter.DefaultApmFilter;
import com.apm.agent.transformer.DefaultClassFileTransFormer;
import com.apm.agent.transformer.modifier.rule.DefaultAnnotationModifierRule;
import com.apm.agent.transformer.modifier.rule.DefaultHttpRequestByteCodeModifierRule;

public class AgentContextInitiner {
	private Instrumentation instrumentation;
	private String opts;
	private ApmContext apmContext = new ApmContext();
	
	public AgentContextInitiner(String opts,Instrumentation instrumentation){
		this.instrumentation = instrumentation;
		this.opts = opts;
		apmContext.setOpts(opts);
		apmContext.setInstrumentation(instrumentation);
	}
	
	public void init(){
		loadApmFilters();// 过滤器
		loadModifierRule();
		instrumentation.addTransformer(new DefaultClassFileTransFormer(apmContext));
	}
	public void loadModifierRule(){
		apmContext.registerModifierRules(InstancePro.newInstance(DefaultAnnotationModifierRule.class));
		apmContext.registerModifierRules(InstancePro.newInstance(DefaultHttpRequestByteCodeModifierRule.class));
	}
	public void loadApmFilters(){
		apmContext.registerApmFilters(InstancePro.newInstance(DefaultApmFilter.class));
	}
}
