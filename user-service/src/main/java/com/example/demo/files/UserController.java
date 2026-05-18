package com.example.demo.files;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

	private final UserService userService;

	@GetMapping("/getAll")
	public List<User> getAll() {

		return userService.getAll();
	}

	@GetMapping("/{id}")
	public User getUser(@PathVariable Long id) {
		System.out.println("yes!, controller is working..");

		return userService.getUser(id);
	}

	@GetMapping("/kafka/{id}")
	public User getUserKafka(@PathVariable Long id) {
		System.out.println("Controller working with Kafka..");
		return userService.getUserKafka(id);
	}
}