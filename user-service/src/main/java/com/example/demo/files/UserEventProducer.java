package com.example.demo.files;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserEventProducer {

	private final KafkaTemplate<String, Object> kafkaTemplate;

	public UserEventProducer(KafkaTemplate<String, Object> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	public void publishUserCreated(UserCreatedEvent event) {

		kafkaTemplate.send("user-events", event);

		System.out.println("Published UserCreatedEvent: " + event);
	}

	public void publishUserAccess(Long userId, String userName) {

		UserCreatedEvent event = new UserCreatedEvent(userId, userName);

		kafkaTemplate.send("user-access-events", event);

		System.out.println("Published UserAccessEvent: " + event);
	}

}
