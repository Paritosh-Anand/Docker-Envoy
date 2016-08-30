package com.panand.docker.envoy.herald;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
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
	
	/**
	 * Using {@link ByteBuffer} as per Apache Storm 1.x.x
	 */
	private static Producer<String, ByteBuffer> producer;
	
	/**
	 * creates {@link Producer} object based on {@link EnvoyProperties} 
	 * @throws IOException
	 */
	public static void createKafkaProducer() throws IOException {
		logger.info("creating kafka producer object based on envoy properties");
		Properties properties = EnvoyProperties.getEnvoyProperties();
		producer = new KafkaProducer<String, ByteBuffer>(properties);
	}
	
	/**
	 * {@link ProducerRecord} to send messages async to a kafka topic
	 * converts json message to {@link ByteBuffer} 
	 * @param topic
	 * @param key
	 * @param jsonMessage
	 */
	public static void sendEvent(String topic, String key, String jsonMessage) {
		ByteBuffer bBuffer = ByteBuffer.wrap(jsonMessage.getBytes(StandardCharsets.UTF_8));
		producer.send(new ProducerRecord<String, ByteBuffer>(topic, key, bBuffer));
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
