package com.example.demo.files;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

	private final OrderService orderService;

	@GetMapping("/getAll")
	public List<Order> getAll() {

		return orderService.getAll();
	}

	@GetMapping("/{id}")
	public Map<String, Object> getOrder(@PathVariable Long id) {

		return Map.of("orderId", id, "orderName", "Wodden Items Parcel");
	}

//    ============================ restclient methods =======================

	@GetMapping("/client")
	public OrderResponse getOrderDetails() {

		return orderService.getOrderDetails();
	}

}
