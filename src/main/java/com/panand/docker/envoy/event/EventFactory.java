package com.panand.docker.envoy.event;

/**
 * 
 * @author paritoshanand
 *
 */
public class EventFactory {

	public static EventType createInstance(String eventType) {

		if(eventType.equalsIgnoreCase("container")) {
			return new ContainerEvent();
		} else {
			return new AuxillaryEvent();
		}
	}

}
