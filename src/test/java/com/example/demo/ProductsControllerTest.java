package com.example.demo;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.controller.ProductsController;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)
class ProductsControllerTest {

	@InjectMocks
	ProductsController controller;
	@Mock
	ProductRepository productRepository;

	@Test
	void successfulUpdateTest() {
		Product productOld = new Product();
		Product productUpdated = new Product();
		productUpdated.setCategory("cat1");
		productUpdated.setDescription("desc");
		productUpdated.setId("95490f38-9675-4d32-9daf-29eb6fc63978");
		productUpdated.setName("prod1");
		productUpdated.setPrice(1.1);
		productUpdated.setType("type1");
		
		when(productRepository.findById(any())).thenReturn(Optional.of(productOld));
		controller.updateProduct(productUpdated, "95490f38-9675-4d32-9daf-29eb6fc63978");
		
		verify(productRepository, times(1)).save(any());
	}

	@Test
	void unsuccessfulUpdateTest() {
		Product productUpdated = new Product();
		productUpdated.setCategory("cat1");
		productUpdated.setDescription("desc");
		productUpdated.setId("95490f38-9675-4d32-9daf-29eb6fc63978");
		productUpdated.setName("prod1");
		productUpdated.setPrice(1.1);
		productUpdated.setType("type1");
		
		when(productRepository.findById(any())).thenReturn(Optional.empty());
		controller.updateProduct(productUpdated, "95490f38-9675-4d32-9daf-29eb6fc63978");
		
		verify(productRepository, times(0)).save(any());
	}
}
