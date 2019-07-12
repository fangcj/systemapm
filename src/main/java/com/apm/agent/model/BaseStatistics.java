package com.apm.agent.model;

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
	private String requestPramater;
	private String requestAttributes;
	private String sessionAttributes;
	private String requestSessionId;
	
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
}
