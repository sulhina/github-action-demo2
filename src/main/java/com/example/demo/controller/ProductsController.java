package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;

@RestController
@RequestMapping(path = "/api/products/")
public class ProductsController {
	
	private ProductRepository productRepository;
	private Logger LOG = LoggerFactory.getLogger(ProductsController.class);
	
	public void productRepository(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	@GetMapping(path = "{id}")
	public Product getProduct(@PathVariable(name = "id") String id) {
		return productRepository.findById(id).orElse(null);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public Product saveProduct(@RequestBody Product productToSave) {
		return productRepository.save(productToSave);
	}
	
	@PutMapping(path = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Product updateProduct(
			@RequestBody Product productToUpdate,
			@PathVariable(name = "id") String id) {
		
		Product foundProduct = productRepository.findById(id).orElse(null);
		
		if (foundProduct != null) {
			foundProduct.setName(productToUpdate.getName());
			foundProduct.setType(productToUpdate.getType());
			foundProduct.setCategory(productToUpdate.getCategory());
			foundProduct.setDescription(productToUpdate.getDescription());
			foundProduct.setPrice(productToUpdate.getPrice());
			return productRepository.save(foundProduct);
		} else {
			LOG.info("No products found with id: {}", id);
			return productToUpdate;
		}
	}
}
