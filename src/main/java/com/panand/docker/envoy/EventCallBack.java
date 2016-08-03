package com.panand.docker.envoy;

import com.panand.docker.envoy.event.EventFactory;
import com.panand.docker.envoy.event.EventType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.dockerjava.api.model.Event;
import com.github.dockerjava.core.command.EventsResultCallback;

public class EventCallBack {

	private final static Logger logger = LoggerFactory.getLogger(EventCallBack.class);

	public static EventsResultCallback callback = new EventsResultCallback() {
		@Override
		public void onNext(Event event) {
			logger.info("Event: " + event);
			
			// TODO handle NPE for unknown event types 
			EventType eventType = EventFactory.createInstance(event.getType());
			eventType.generateDatum(event);
			
		}
	};
}
