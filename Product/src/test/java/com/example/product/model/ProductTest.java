package com.example.product.model;

import org.junit.jupiter.api.Test;

class ProductTest {

	@Test
	void testProduct() {
		Product product = new Product();
		product.getProductId();
		product.getProductName();
		product.getProductPrice();
		product.getProductRating();
		product.getProductType();
		product.getProductAvalibility();

		product.setProductId(0);
		product.setProductName(null);
		product.setProductPrice(null);
		product.setProductRating(null);
		product.setProductType(null);
		product.setProductAvalibility(null);
	}
}
