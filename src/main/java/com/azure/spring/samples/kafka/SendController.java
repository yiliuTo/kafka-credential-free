package com.azure.spring.samples.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SendController.class);

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@PostMapping("/messages")
	public String postMessage(@RequestParam String message) {
		LOGGER.info("Sending message");
		kafkaTemplate.sendDefault(message);
		return message;
	}
}
