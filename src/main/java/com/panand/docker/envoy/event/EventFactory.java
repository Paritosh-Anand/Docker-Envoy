package com.panand.docker.envoy.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventFactory {

	private final static Logger logger = LoggerFactory.getLogger(EventFactory.class);
	
	public static EventType createInstance(String eventType) {

		logger.info("captured event type -- " + eventType);
		if(eventType.equalsIgnoreCase("container")) {
			return new ContainerEvent();
		} else {
			throw new NullPointerException();
		}
	}

}
