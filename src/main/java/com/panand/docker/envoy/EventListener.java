package com.panand.docker.envoy;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.exception.DockerClientException;
import com.github.dockerjava.core.DockerClientBuilder;
/**
 * Docker Event listener
 *
 */
public class EventListener 
{
	private final static Logger logger = LoggerFactory.getLogger(EventListener.class);
	
    public static void main(String[] args) throws IOException, InterruptedException, DockerClientException
    {
    	Properties properties = new Properties();
    	FileReader rd = new FileReader("/Users/paritoshanand/Documents/projects/Docker-Envoy/docker.properties");
        properties.load(rd);	
    	
    	logger.info("Listening to Docker events on " + properties.getProperty("com.docker.envoy.dockerUri"));
    	ConfigBuilder configBuilder = new ConfigBuilder(properties);
    	
    	DockerClient dockerClient = DockerClientBuilder.getInstance(configBuilder.generateConfigs())
    			.withDockerCmdExecFactory(configBuilder.generateCmdExecFactory())
    			.build();
    	
    	dockerClient.eventsCmd().exec(EventCallBack.callback).awaitCompletion().close();
    }
}
