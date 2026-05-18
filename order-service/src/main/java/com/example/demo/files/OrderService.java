package com.example.demo.files;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

	private final RestClient.Builder builder;

	public List<Order> getAll() {

		return List.of(new Order(1L, "Order-A"), new Order(2L, "Order-B"), new Order(3L, "Order-C"));
	}

//	restclient methods here...

	public OrderResponse getOrderDetails() {

		RestClient restClient = builder.build();

		User user = restClient.get().uri("http://USER-SERVICE/users/1").retrieve().body(User.class);

		Product product = restClient.get().uri("http://PRODUCT-SERVICE/products/1").retrieve().body(Product.class);
		
		return new OrderResponse(111L, user, product);

	}

}
