package com.panand.docker.envoy.event;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.dockerjava.api.model.Event;
import com.panand.docker.envoy.container.Container;
import com.panand.docker.envoy.container.InspectContainer;

public class ContainerEvent extends EventType {

	private final static Logger logger = LoggerFactory.getLogger(ContainerEvent.class);
	
	@Override
	public Module generateDatum(Event event) {
		
		Container container = null;
		try {
			InspectContainer inspectContainer = new InspectContainer(event.getId());
			//String nodeName = (event.getNode().getName() != null) ? event.getNode().getName() : "default";
			String nodeName = "default";
			container = new Container(
					event.getType(),
					event.getId(), event.getFrom(), event.getStatus(), inspectContainer.getLabels(),
					inspectContainer.getHostExposedPort(), inspectContainer.getOOMkilled(), nodeName
			);

			logger.info("event-" + event.getType() + " container id-" + container.getContainerId() + 
					" ports- " + container.getHostExposedPorts() + " status-" + event.getStatus());
			
		} catch (IOException ioe) {
			logger.error("Error in InspectContainer", ioe);
		} 
	
		return container;
	}

}
