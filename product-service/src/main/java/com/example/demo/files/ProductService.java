package com.example.demo.files;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ProductService {

	public List<Product> getAll() {

		return List.of(new Product(1L, "Laptop", 56.90), new Product(2L, "Mouse", 88.123),
				new Product(3L, "Keyboard", 234.123));
	}

	public Product getProduct(Long id) {

		id = 1L;

		return new Product(id, "Laptop", 56.90);
	}

}
