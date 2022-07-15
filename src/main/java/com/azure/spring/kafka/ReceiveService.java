package com.azure.spring.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ReceiveService {

	private final Logger LOGGER = LoggerFactory.getLogger(ReceiveService.class);

	private static final String TOPIC = "YOUR_EVENTHUB_NAME";

	@KafkaListener(topics = TOPIC, id = "credential-free")
	public void receiveMessage(String message) {
		LOGGER.info("Received message from Event hub: {}", message);
	}
}
