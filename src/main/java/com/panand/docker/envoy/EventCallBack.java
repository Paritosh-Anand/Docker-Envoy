package com.panand.docker.envoy;

import com.panand.docker.envoy.event.EventFactory;
import com.panand.docker.envoy.event.EventType;
import com.panand.docker.envoy.event.Module;
import com.panand.docker.envoy.herald.YieldEvent;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.dockerjava.api.model.Event;
import com.github.dockerjava.core.command.EventsResultCallback;

public class EventCallBack {

	private final static Logger logger = LoggerFactory.getLogger(EventCallBack.class);

	public static EventsResultCallback callback = new EventsResultCallback() {
		@Override
		public void onNext(Event event) {
			try{
				logger.info("Event: " + event);
				EventType eventType = EventFactory.createInstance(event.getType());
				Module module = eventType.generateDatum(event);
				
				// TODO convert module pojo to json.
				ObjectMapper mapper = new ObjectMapper();

				String jsonMessage = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(module);
				logger.info("json string for module object ---" + jsonMessage);
				
				// send message to kafka 
				YieldEvent yieldEvent = new YieldEvent();
				yieldEvent.sendEvent(jsonMessage);
				
			} catch(NullPointerException npe) {
				logger.error("caught npe for event type -- " + event.getType(), npe);
			} catch (JsonProcessingException e) {
				logger.error("caught error while json processing -- " + event.getType(), e);
			} catch (IOException e) {
				logger.error("caught error while sending message to kafka -- " + event.getType(), e);
			}
			
		}
	};
}
