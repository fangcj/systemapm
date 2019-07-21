package com.apm.agent.common.util;

import com.apm.agent.common.cons.AgentConst;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class AgentReflectUtil {

    private Object invokeObj;
    private Map<String,Method> methods = new HashMap<String, Method>();

    public AgentReflectUtil(Object invokeObj){
        this.invokeObj = invokeObj;
        for(Method method : invokeObj.getClass().getDeclaredMethods()){
            methods.put(method.getName(),method);
        }
    }

    public Map<String,String[]> getRequestParameters(){
        return (Map<String, String[]>) invokeMethod(AgentConst.REQUEST_METHOD_GETPARAMETERMAP);
    }
    public Object invokeMethod(String methodName,Object... params){
       Method invokeMethod = methods.get(methodName);
        Object obj = null;
        try {
            obj = invokeMethod.invoke(invokeObj,params);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return  obj;
    }
}
