package com.apm.agent.model;

import java.util.Map;

/**
 * Created by Tommy on 2018/3/8.
 */
public class BaseStatistics implements java.io.Serializable {
    private long recordTime;
    private String recordModel;
    private String hostIp;
    private String hostName;
    private String traceId;
    private String requestUri;
	private String requestHost;
	private String sessionAttributes;
	private String requestSessionId;
    private Map<String,String[]> requestParameters;

    public long getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(long recordTime) {
        this.recordTime = recordTime;
    }

    public String getRecordModel() {
        return recordModel;
    }

    public void setRecordModel(String recordModel) {
        this.recordModel = recordModel;
    }

    public String getHostIp() {
        return hostIp;
    }

    public void setHostIp(String hostIp) {
        this.hostIp = hostIp;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getRequestUri() {
        return requestUri;
    }

    public void setRequestUri(String requestUri) {
        this.requestUri = requestUri;
    }

    public String getRequestHost() {
        return requestHost;
    }

    public void setRequestHost(String requestHost) {
        this.requestHost = requestHost;
    }

    public String getSessionAttributes() {
        return sessionAttributes;
    }

    public void setSessionAttributes(String sessionAttributes) {
        this.sessionAttributes = sessionAttributes;
    }

    public String getRequestSessionId() {
        return requestSessionId;
    }

    public void setRequestSessionId(String requestSessionId) {
        this.requestSessionId = requestSessionId;
    }

    public Map<String, String[]> getRequestParameters() {
        return requestParameters;
    }

    public void setRequestParameters(Map<String, String[]> requestParameters) {
        this.requestParameters = requestParameters;
    }
}
