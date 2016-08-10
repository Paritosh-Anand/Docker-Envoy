package com.panand.docker.envoy;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.dockerjava.api.DockerClient;

/**
 * Docker Event listener
 *
 */
public class EventListener 
{
	private final static Logger logger = LoggerFactory.getLogger(EventListener.class);
	public static void main(String[] args) throws IOException, InterruptedException {
		
		String mode = "single-node";
		if(args.length > 0){ 
			mode = args[0];
		}

		Properties properties = EnvoyProperties.getEnvoyProperties();
		DockerClient dockerClient = Client.getDockerClient();

		logger.info("Listening to Docker(" + mode + ") events on " + properties.getProperty("com.docker.envoy.dockerUri"
				,"tcp://localhost:3376"));
		dockerClient.eventsCmd().exec(EventCallBack.callback).awaitCompletion().close();

	}
}
