package com.example.demo.files;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderResponse {

	private Long orderId;
	private User user;
	private Product product;

}
