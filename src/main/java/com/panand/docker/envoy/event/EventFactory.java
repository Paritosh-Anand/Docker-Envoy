package com.panand.docker.envoy.event;

/**
 * 
 * @author paritoshanand
 *
 */
public class EventFactory {

	/**
	 * 
	 * @param eventType
	 * @return {@link EventType}
	 */
	public static EventType createInstance(String eventType) {

		if(eventType.equalsIgnoreCase("container")) {
			return new ContainerEvent();
		} else {
			return new AuxillaryEvent();
		}
	}

}
