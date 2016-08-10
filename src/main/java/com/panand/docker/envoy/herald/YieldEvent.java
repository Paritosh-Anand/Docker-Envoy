package com.panand.docker.envoy.herald;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.Future;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import com.panand.docker.envoy.EnvoyProperties;

/**
 * This class handles all transactions happening 
 * with Kafka.
 * @author paritoshanand
 *
 */
public class YieldEvent {

	private Producer<String, String> producer;
	
	/**
	 * creates {@link Producer} object based on {@link EnvoyProperties} 
	 * @throws IOException
	 */
	public YieldEvent() throws IOException {
		Properties properties = EnvoyProperties.getEnvoyProperties();
		producer = new KafkaProducer<String, String>(properties);
	}
	
	/**
	 * {@link ProducerRecord} to send messages to a kafka topic
	 * @param topic
	 * @param key
	 * @param jsonMessage
	 */
	public void sendEvent(String topic, String key, String jsonMessage) {
		
		Future<RecordMetadata> future = producer.send(new ProducerRecord<String, String>(topic, key, jsonMessage));
		
	}
	
	/**
	 * close {@link KafkaProducer} connection object.
	 */
	public void closeKafkaConnection() {
		if(this.producer != null) {
			producer.close();
		}
	}
	
}
