package com.panand.docker.envoy.event;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.dockerjava.api.model.Event;
import com.panand.docker.envoy.EnvoyEnv;
import com.panand.docker.envoy.container.Container;
import com.panand.docker.envoy.container.InspectContainer;

public class ContainerEvent extends EventType {

	private final static Logger logger = LoggerFactory.getLogger(ContainerEvent.class);
	
	@Override
	public Entity generateDatum(Event event) {
		
		Container container = null;
		try {
			InspectContainer inspectContainer = new InspectContainer(event.getId());
			container = new Container(
					event.getType(), event.getTime(),
					event.getId(), event.getFrom(), event.getStatus(), inspectContainer.getLabels(),
					inspectContainer.getHostExposedPort(), inspectContainer.getOOMkilled(), EnvoyEnv.getHostName(event)
			);
			
		} catch (IOException ioe) {
			logger.error("Error in InspectContainer", ioe);
		} 
	
		return container;
	}

}
