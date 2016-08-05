package com.panand.docker.envoy.herald;

import java.io.IOException;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import com.panand.docker.envoy.EnvoyProperties;
import com.panand.docker.envoy.event.EventType;

public class YieldEvent {

	Producer<String, EventType> producer;
	
	/**
	 * create {@link Producer} object 
	 * @throws IOException
	 */
	public YieldEvent() throws IOException {
		Properties properties = EnvoyProperties.getEnvoyProperties();
		producer = new KafkaProducer<>(properties);
	}
	
	public void sendEvent(EventType event) {
		producer.send(new ProducerRecord<String, EventType>("ContainerEvent", "message-key", event));
	}
	
}
