package com.panand.docker.envoy;

import com.panand.docker.envoy.container.Container;
import com.panand.docker.envoy.container.InspectContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.dockerjava.api.model.Event;
import com.github.dockerjava.core.command.EventsResultCallback;

import java.io.IOException;

public class EventCallBack {

	private final static Logger logger = LoggerFactory.getLogger(EventCallBack.class);

	public static EventsResultCallback callback = new EventsResultCallback() {
		@Override
		public void onNext(Event event) {
			logger.info("Event: " + event);
			if(event.getType().equalsIgnoreCase("container")) {
				try {
					InspectContainer inspectContainer = new InspectContainer(event.getId());
					Container container = new Container(
							event.getId(), event.getFrom(), event.getStatus(), inspectContainer.getLabels(),
							inspectContainer.getHostExposedPort(), inspectContainer.getOOMkilled(),
							event.getNode().getName()
					);

					logger.info("container id-" + container.getContainerId() + " ports- " + container.getHostExposedPorts());
				} catch (IOException ioe) {
					logger.error("Error in InspectContainer", ioe);
				}
			}
		}
	};
}
