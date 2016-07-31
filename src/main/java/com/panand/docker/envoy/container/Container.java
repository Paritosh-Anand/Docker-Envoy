package com.panand.docker.envoy.container;

import java.util.Map;

public class Container {
	
	private String containerId;
	private String fromImage;
	private String status;
	private Map<String, String> labels;
	private Map<String, String> hostExposedPorts;
	
	public String getContainerId() {
		return containerId;
	}
	public void setContainerId(String containerId) {
		this.containerId = containerId;
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
	public Map<String, String> getLabels() {
		return labels;
	}
	public void setLabels(Map<String, String> labels) {
		this.labels = labels;
	}
	public Map<String, String> getHostExposedPorts() {
		return hostExposedPorts;
	}
	public void setHostExposedPorts(Map<String, String> hostExposedPorts) {
		this.hostExposedPorts = hostExposedPorts;
	}

}
