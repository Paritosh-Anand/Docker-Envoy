package com.panand.docker.envoy;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.dockerjava.api.DockerClient;
import com.panand.docker.envoy.herald.YieldEvent;

/**
 * Docker Event listener
 *
 */
public class EventListener 
{
	private final static Logger logger = LoggerFactory.getLogger(EventListener.class);
	
	private static final String CONFIG_FILE = "docker.properties";
	
	public static void main(String[] args) throws IOException, InterruptedException {
				
		Properties properties = new Properties();
		try {
			properties.load(EventListener.class.getClassLoader().getResourceAsStream(CONFIG_FILE));
		} catch (IOException ioException) {
            logger.error("property file not found " + CONFIG_FILE + "-", ioException);
            System.exit(0);
        } catch (NullPointerException npe) {
            logger.error("property file not found " + CONFIG_FILE + "-", npe);
            System.exit(0);
        } catch (Exception e) {
        	logger.error("not able to read property file " + CONFIG_FILE, e);
        }
		
		DockerClient dockerClient = Client.getDockerClient();

		logger.info("Listening to Docker(" + properties.getProperty("com.docker.envoy.mode") + ") "
				+ "events on " + properties.getProperty("com.docker.envoy.dockerUri"
				,"tcp://localhost:3376"));
		
		/**
		 * creating Kafka producer object.
		 */
		YieldEvent.createKafkaProducer(properties);

		dockerClient.eventsCmd().exec(EventCallBack.callback).awaitCompletion().close();

	}
}
