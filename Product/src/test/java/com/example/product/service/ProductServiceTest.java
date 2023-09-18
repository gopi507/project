package com.example.product.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.product.model.Product;
import com.example.product.repository.ProductRepository;

@WebMvcTest(value = ProductService.class)
class ProductServiceTest {

	@MockBean
	private ProductRepository productRepository;

	@Autowired
	ProductService productService;

//	private static List<Product> products = new ArrayList<>();

	@Test
	void testgetAll() throws Exception {
		List<Product> products = new ArrayList<>();
		when(productRepository.findAll()).thenReturn(products);

		assertNotNull(productService.getAll());

	}

	@Test
	void testgetByProductId() {

		Product product = new Product(12, null, null, null, null, null);
		when(productRepository.findByProductId(12)).thenReturn(product);
		assertNotNull(productService.getByProductId(12));

	}

	@Test
	void testgetByProductType() {

		List<Product> products = new ArrayList<>();
		Product product = new Product(12, "up", null, null, null, null);
		products.add(product);
		when(productRepository.findByProductType("u")).thenReturn(products);
		assertEquals(products, productService.getByProductType("u"));

	}

	@Test
	void testgetByProductName() {

		List<Product> products = new ArrayList<>();

		when(productRepository.findByProductName("u")).thenReturn(products);
		assertEquals(products, productService.getByProductName("u"));
	}

	@Test
	void testaddProduct() {

		Product product = new Product();

		when(productRepository.save(null)).thenReturn(product);

		assertNotNull(productService.addProduct(null));
	}

	@Test
	void testgetByProductPrice() {

		List<Product> product = new ArrayList<>();

		when(productRepository.findAll()).thenReturn(product);

		assertNotNull(productService.getByProductPrice(2.0, 3.0));
	}
	
	@Test
	void testgetByProductRating() {

		List<Product> product = new ArrayList<>();

		when(productRepository.findAll()).thenReturn(product);

		assertNotNull(productService.getByProductRating(2.0));
	}
	@Test
	void testupdateProduct() throws Exception {

		Product product = new Product();

		when(productRepository.save(null)).thenReturn(product);

		assertNotNull(productService.updateProduct(null));
	}


	@Test
	void testremoveProduct() throws Exception {
		
		when(productRepository.findByProductId(2)).thenReturn(null);
//		Mockito.when(productRepository.delete(null)).thenReturn(null);
		assertNotNull(productService.removeProduct(2));
		

	}

}
