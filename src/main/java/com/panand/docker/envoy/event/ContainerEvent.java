package com.panand.docker.envoy.event;

import java.io.IOException;
import java.net.InetAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.dockerjava.api.model.Event;
import com.panand.docker.envoy.container.Container;
import com.panand.docker.envoy.container.InspectContainer;

public class ContainerEvent extends EventType {

	private final static Logger logger = LoggerFactory.getLogger(ContainerEvent.class);
	
	@Override
	public Entity generateDatum(Event event) {
		
		Container container = null;
		try {
			InspectContainer inspectContainer = new InspectContainer(event.getId());
			String nodeName = InetAddress.getLocalHost().getHostName();
			logger.info("node name -- " + nodeName + " node name from docker event -- " + event.getNode());
			container = new Container(
					event.getType(), event.getTime(),
					event.getId(), event.getFrom(), event.getStatus(), inspectContainer.getLabels(),
					inspectContainer.getHostExposedPort(), inspectContainer.getOOMkilled(), nodeName
			);

			logger.debug("event-" + event.getType() + " container id-" + container.getContainerId() + 
					" ports- " + container.getHostExposedPorts() + " status-" + event.getStatus());
			
		} catch (IOException ioe) {
			logger.error("Error in InspectContainer", ioe);
		} 
	
		return container;
	}

}
