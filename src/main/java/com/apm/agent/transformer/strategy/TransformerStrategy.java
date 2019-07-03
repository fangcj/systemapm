package com.apm.agent.transformer.strategy;

import java.util.HashMap;
import java.util.Map;

import com.apm.agent.transformer.AbastractTransFormer;

public class TransformerStrategy {
	private static Map<String,String> strategyBeanMap = new HashMap<String,String>();

	public static void loadStrategy(Map<String,String> strategyBeanMap){
		TransformerStrategy.strategyBeanMap = strategyBeanMap;
	}
	
	public AbastractTransFormer chooseTransFormer(String chooseCondition){
		AbastractTransFormer transFormer = null;
		try {
			String className = strategyBeanMap.get(chooseCondition);
			if(strategyBeanMap.containsKey(chooseCondition)){
				transFormer = (AbastractTransFormer) Class.forName(className).newInstance();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return transFormer;
	}
}
