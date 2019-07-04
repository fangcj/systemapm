package com.apm.agent;

import java.lang.instrument.Instrumentation;
import java.util.ArrayList;
import java.util.List;

import com.apm.agent.transformer.modifier.ByteCodeModifier;
import com.apm.agent.transformer.modifier.rule.IModifierRule;

public class ApmContext {
	private Instrumentation instrumentation;
	private String opts;
    private List<IModifierRule> modifierRules= new ArrayList<IModifierRule>();
    private List<ByteCodeModifier> byteCodeModifiers = new ArrayList<ByteCodeModifier>();
	
    public Instrumentation getInstrumentation() {
		return instrumentation;
	}
	public void setInstrumentation(Instrumentation instrumentation) {
		this.instrumentation = instrumentation;
	}
	public String getOpts() {
		return opts;
	}
	public void setOpts(String opts) {
		this.opts = opts;
	}
	public void registerModifierRules(IModifierRule iModifierRule) {
		modifierRules.add(iModifierRule);
	}
	public List<IModifierRule> getModifierRules() {
		return modifierRules;
	}
	public void registerByteCodeModifier(ByteCodeModifier byteCodeModifier) {
		byteCodeModifiers.add(byteCodeModifier);
	}
	public List<ByteCodeModifier> getByteCodeModifiers() {
		return byteCodeModifiers;
	}
}
