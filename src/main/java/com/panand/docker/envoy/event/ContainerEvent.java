package com.panand.docker.envoy.event;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.dockerjava.api.model.Event;
import com.panand.docker.envoy.EnvoyEnv;
import com.panand.docker.envoy.container.Container;
import com.panand.docker.envoy.container.InspectContainer;

public class ContainerEvent extends EventType {

	private final static Logger logger = LoggerFactory.getLogger(ContainerEvent.class);
	
	private Map<String, String> labels;
	private HashMap<String, String> hostPorts = new HashMap<String, String>();
	private HashMap<String, String> hostExposedPorts = new HashMap<String, String>();
	private Boolean oOMkilled = false;
	
	@Override
	public Entity generateDatum(Event event) {
		
		Container container = null;
		
		try {
			InspectContainer inspectContainer = new InspectContainer(event.getId());
			
			this.labels = inspectContainer.getLabels();
			this.hostPorts = inspectContainer.getHostPort();
			this.hostExposedPorts = inspectContainer.getHostExposedPort();
			this.oOMkilled = inspectContainer.getOOMkilled();
			
		} catch (Exception e) {
			logger.error("Error in InspectContainer", e);
		}
		
		container = new Container(
			event.getType(), event.getTime(), event.getId(),
			event.getFrom(), event.getStatus(), labels,
			hostPorts, hostExposedPorts, oOMkilled, EnvoyEnv.getHostName(event)
		);
	
		return container;
	}

}
