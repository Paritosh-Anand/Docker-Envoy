package com.panand.docker.envoy.event;

public class EventFactory {

	public static EventType createInstance(String eventType) {
		
		if(eventType.equalsIgnoreCase("container")) {
			return new ContainerEvent();
		} else {
			throw new NullPointerException();
		}
	}

}
