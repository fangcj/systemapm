package com.apm.agent;

import java.lang.instrument.Instrumentation;

import com.apm.agent.transformer.SimpleClassFileTransFormer;
import com.apm.agent.transformer.modifier.rule.DefaultAnnotationModifierRule;

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
		instrumentation.addTransformer(new SimpleClassFileTransFormer(apmContext));
	}
	public void loadModifierRule(){
		apmContext.registerModifierRules(InstancePro.newInstance(DefaultAnnotationModifierRule.class));
	}
}
