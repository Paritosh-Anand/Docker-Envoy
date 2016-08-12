package com.panand.docker.envoy.herald;

import java.io.IOException;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.panand.docker.envoy.EnvoyProperties;

/**
 * This class handles all transactions happening 
 * with Kafka.
 * @author paritoshanand
 *
 */
public class YieldEvent {

	private final static Logger logger = LoggerFactory.getLogger(YieldEvent.class);
	
	private static Producer<String, String> producer;
	
	/**
	 * creates {@link Producer} object based on {@link EnvoyProperties} 
	 * @throws IOException
	 */
	public static void createKafkaProducer() throws IOException {
		logger.info("creating kafka producer object based on envoy properties");
		Properties properties = EnvoyProperties.getEnvoyProperties();
		producer = new KafkaProducer<String, String>(properties);
	}
	
	/**
	 * {@link ProducerRecord} to send messages to a kafka topic
	 * @param topic
	 * @param key
	 * @param jsonMessage
	 */
	public static void sendEvent(String topic, String key, String jsonMessage) {
		producer.send(new ProducerRecord<String, String>(topic, key, jsonMessage));
	}
	
	/**
	 * close {@link KafkaProducer} connection object.
	 */
	public void closeKafkaConnection() {
		if(producer != null) {
			logger.info("destroying kafka producer object");
			producer.close();
		}
	}
	
}
