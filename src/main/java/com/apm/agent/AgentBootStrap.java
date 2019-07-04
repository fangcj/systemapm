package com.apm.agent;

import java.lang.instrument.Instrumentation;

public class AgentBootStrap {
	public static void premain(String opts,Instrumentation instrumentation){
		System.out.println("start premain");
		new AgentContextInitiner(opts,instrumentation).init();
		System.out.println("end premain");
	}
}
