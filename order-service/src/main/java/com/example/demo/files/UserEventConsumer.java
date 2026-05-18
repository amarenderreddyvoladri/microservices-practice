package com.example.demo.files;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class UserEventConsumer {

	@KafkaListener(topics = "user-access-events", groupId = "order-service-group")
	public void consumeUserAccess(UserAccessEvent event) {

		System.out.println("Order-Service received UserAccessEvent: " + event);

		// Here you can add business logic:
		// - Logging
		// - Analytics
		// - Trigger other processes
	}
}