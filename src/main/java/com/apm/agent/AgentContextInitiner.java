package com.apm.agent;

import java.lang.instrument.Instrumentation;

import com.apm.agent.transformer.DefaultBeanClassFileTransFormer;
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
		loadModifierRule();
		instrumentation.addTransformer(new DefaultBeanClassFileTransFormer(apmContext));
	}
	public void loadModifierRule(){
		apmContext.registerModifierRules(InstancePro.newInstance(DefaultAnnotationModifierRule.class));
		apmContext.registerModifierRules(InstancePro.newInstance(DefaultHttpRequestByteCodeModifierRule.class));
	}
}
