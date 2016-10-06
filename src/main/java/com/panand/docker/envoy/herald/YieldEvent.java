package com.panand.docker.envoy.herald;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class handles all transactions happening 
 * with Kafka version 0.8.2.1
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
	public static void createKafkaProducer(Properties properties) throws IOException {
		logger.info("creating kafka producer object based on envoy properties");
		ProducerConfig config = new ProducerConfig(properties);
		
		producer = new Producer<String, String>(config);
	}
	
	/**
	 * {@link KeyedMessage} to send messages async to a kafka topic
	 * converts JSON message to {@link ByteBuffer} 
	 * @param topic
	 * @param key
	 * @param jsonMessage
	 */
	public static void sendEvent(String topic, String key, String jsonMessage) {
		KeyedMessage<String, String> data = new KeyedMessage<String, String>(topic, key, jsonMessage);
		producer.send(data);
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
