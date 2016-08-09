package com.panand.docker.envoy.herald;

import java.io.IOException;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import com.panand.docker.envoy.EnvoyProperties;

public class YieldEvent {

	Producer<String, String> producer;
	
	/**
	 * creates {@link Producer} object based on {@link EnvoyProperties} 
	 * @throws IOException
	 */
	public YieldEvent() throws IOException {
		Properties properties = EnvoyProperties.getEnvoyProperties();
		producer = new KafkaProducer<String, String>(properties);
	}
	
	public void sendEvent(String jsonMessage) {
		producer.send(new ProducerRecord<String, String>("ContainerEvent", "message-key", jsonMessage));
	}
	
}
