package com.example.demo.files;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

	private final ProductService productService;

	@GetMapping("/getAll")
	public List<Product> getAll() {

		return productService.getAll();
	}

	@GetMapping("/{id}")
	public Product getProduct(@PathVariable Long id) {

		return productService.getProduct(id);
	}

}
