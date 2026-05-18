package com.example.demo.files;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UserService {

	private final UserEventProducer userEventProducer;

	public UserService(UserEventProducer userEventProducer) {
		this.userEventProducer = userEventProducer;

	}

	public List<User> getAll() {

		return List.of(new User(1L, "Amar"), new User(2L, "Reddy"), new User(3L, "Spring"));
	}

	public User getUser(Long id) {

		id = 1L;

		return new User(id, "Amar");
	}

//	===================================== kafka methods =======================================

	public User createUser(User user) {
		User savedUser = new User(11L, "User bhai sher!");
		userEventProducer.publishUserCreated(new UserCreatedEvent(savedUser.getId(), savedUser.getName()));
		return savedUser;
	}

	// Get user and publish Kafka event
	public User getUserKafka(Long id) {
		// Fetch user (here static demo)
		User user = new User(id, "kafka amar");

		// Publish Kafka event
		userEventProducer.publishUserAccess(user.getId(), user.getName());

		// Return the user
		return user;
	}

}
