package com.panand.docker.envoy;

import com.panand.docker.envoy.event.EventFactory;
import com.panand.docker.envoy.event.EventType;
import com.panand.docker.envoy.event.Entity;
import com.panand.docker.envoy.herald.YieldEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.dockerjava.api.model.Event;
import com.github.dockerjava.core.command.EventsResultCallback;

public class EventCallBack {

	private final static Logger logger = LoggerFactory.getLogger(EventCallBack.class);

	/**
	 * All of the events captured will pass through this callback
	 */
	public static EventsResultCallback callback = new EventsResultCallback() {
		@Override
		public void onNext(Event event) {
			try{
				logger.info("Event: " + event);
				EventType eventType = EventFactory.createInstance(event.getType());
				Entity entity = eventType.generateDatum(event);
				
				// convert module to JSON string.
				ObjectMapper mapper = new ObjectMapper();

				String jsonMessage = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(entity);
				logger.info("json string for module object ---" + jsonMessage);
				
				// send message to kafka 
				YieldEvent.sendEvent(event.getType(), event.getId(), jsonMessage);
				
			} catch(NullPointerException npe) {
				logger.error("caught npe for event type -- " + event.getType(), npe);
			} catch (JsonProcessingException e) {
				logger.error("caught error while json processing -- " + event.getType(), e);
			}
			
		}
	};
}
