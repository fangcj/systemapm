package com.apm.agent.out;

public class DefinedOutput {
	
	public void print(Object o){
		if(o==null){
			System.out.println("统计信息为空");
		}else{
			System.out.print(o);
		}
	}

}
