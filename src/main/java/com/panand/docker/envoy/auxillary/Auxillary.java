package com.panand.docker.envoy.auxillary;

import com.panand.docker.envoy.event.Entity;

public class Auxillary extends Entity {

	private String eventType;
	private Long eventTime;
	private String id;
	private String fromImage;
	private String status;
	private String nodeName;
	
	public Auxillary(String eventType, Long eventTime, String id,
			String fromImage, String status, String nodeName) {
		
		this.eventType = eventType;
		this.eventTime = eventTime;
		this.id = id;
		this.fromImage = fromImage;
		this.status = status;
		this.nodeName = nodeName;
		
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public Long getEventTime() {
		return eventTime;
	}

	public void setEventTime(Long eventTime) {
		this.eventTime = eventTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFromImage() {
		return fromImage;
	}

	public void setFromImage(String fromImage) {
		this.fromImage = fromImage;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
}
