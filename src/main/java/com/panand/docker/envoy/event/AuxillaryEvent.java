package com.panand.docker.envoy.event;

import com.github.dockerjava.api.model.Event;
import com.panand.docker.auxillary.Auxillary;
import com.panand.docker.envoy.EnvoyEnv;

public class AuxillaryEvent extends EventType {

	@Override
	public Entity generateDatum(Event event) {
		
		Auxillary auxillary = new Auxillary(event.getType(), event.getTime(), event.getId(), 
				event.getFrom(), event.getStatus(), EnvoyEnv.getHostName(event));
		
		return auxillary;
	}
	
}
