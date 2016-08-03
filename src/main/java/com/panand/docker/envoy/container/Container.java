package com.panand.docker.envoy.container;

import java.util.Map;

public class Container {

	/**
	 * Node name on which the event has occurred.
	 */
	private String nodeName;
	/**
	 * Container ID 
	 */
	private String containerId;
	private String fromImage;
	private String status;
	private Boolean oOMKilled;
	private Map<String, String> labels;
	private Map<String, String> hostExposedPorts;

	public Container(String containerId, String fromImage, String status,
					 Map<String, String> labels, Map<String, String> hostExposedPorts,
					 Boolean oOMKilled, String nodeName) {
		this.nodeName = nodeName;
		this.containerId = containerId;
		this.fromImage = fromImage;
		this.status = status;
		this.oOMKilled = oOMKilled;
		this.labels = labels;
		this.hostExposedPorts = hostExposedPorts;
	}

	/**
	 * @return {@link String} node name
	 */
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
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
	public Boolean getoOMKilled() {
		return oOMKilled;
	}
	public void setoOMKilled(Boolean oOMKilled) {
		this.oOMKilled = oOMKilled;
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
