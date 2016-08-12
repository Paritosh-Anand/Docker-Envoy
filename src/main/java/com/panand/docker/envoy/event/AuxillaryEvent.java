package com.panand.docker.envoy.event;

import com.github.dockerjava.api.model.Event;
import com.panand.docker.envoy.EnvoyEnv;
import com.panand.docker.envoy.auxillary.Auxillary;

public class AuxillaryEvent extends EventType {

	@Override
	public Entity generateDatum(Event event) {
		
		Auxillary auxillary = new Auxillary(event.getType(), event.getTime(), event.getId(), 
				event.getFrom(), event.getStatus(), EnvoyEnv.getHostName(event));
		
		return auxillary;
	}
	
}
