
package com.example.product.controller;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.product.model.Product;
import com.example.product.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(value = ProductController.class)
class ProductControllerTest2 {
	
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	ProductService productService;
	

	
	Product product = new Product(12345, "watch","fossil watch",20000.0 , 9.7, true);
	List<Product> list = new ArrayList<>();

	@BeforeEach
	void beforeEach() {
		list.add(product);
	}

	@Test
	void testGetAllList() throws Exception{
		when(productService.getAll()).thenReturn(list);
		mockMvc.perform(MockMvcRequestBuilders.get("/getAllList")).andExpect(jsonPath("$", notNullValue()));
	}

	@Test
	void testGetProductById() throws Exception {
		when(productService.getByProductId(12345)).thenReturn(product);
		mockMvc.perform(MockMvcRequestBuilders.get("/getProductById/"+product.getProductId())).andExpect(status().isOk());

	}

	@Test
	void testGetProductByType() throws Exception {
		when(productService.getByProductType("watch")).thenReturn(list);
		mockMvc.perform(MockMvcRequestBuilders.get("/getProductByType/"+product.getProductType())).andExpect(status().isOk());
	}

	@Test
	void testGetProductByName() throws Exception {
		when(productService.getByProductName("fossil watch")).thenReturn(list);
		mockMvc.perform(MockMvcRequestBuilders.get("/getProductByName/"+product.getProductName())).andExpect(status().isOk());
	}

	@Test
	void testGetProductByPrice() throws Exception {
		when(productService.getByProductPrice(10000.0, 20000.0)).thenReturn(list);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/getProductByPrice/10000/20000")).andExpect(status().isOk());
	}

	@Test
	void testGetProductByRating() throws Exception {
		when(productService.getByProductRating(9.7)).thenReturn(list);
		mockMvc.perform(MockMvcRequestBuilders.get("/getProductByRating/"+product.getProductRating())).andExpect(status().isOk());
	}
		
	

	@Test
	void testCheckProductAvalibility() throws Exception {
		when(productService.getProductAvalibility("true")).thenReturn(true);
		mockMvc.perform(MockMvcRequestBuilders.get("/checkProductAvalibility/"+product.getProductAvalibility())).andExpect(status().isOk());
	}

	@Test
	void testAddProduct() throws Exception {
		when(productService.addProduct(product)).thenReturn(product);
		
		String bookJson = new ObjectMapper().writeValueAsString(product);

		 

		mockMvc.perform(
				MockMvcRequestBuilders.post("/addProduct").contentType(MediaType.APPLICATION_JSON).content(bookJson))
				.andExpect(status().isOk());
	}

	@Test
	void testUpdateProduct() throws Exception {
		when(productService.updateProduct(product)).thenReturn(product);
        when(productService.getByProductId(12345)).thenReturn(product);
		String bookJson = new ObjectMapper().writeValueAsString(product);

		 

		mockMvc.perform(
				MockMvcRequestBuilders.put("/updateProduct").contentType(MediaType.APPLICATION_JSON).content(bookJson))
				.andExpect(status().isOk());
	}
	@Test
	void testDeleteProduct() throws Exception{
		when(productService.getByProductId(12345)).thenReturn(product);
		when(productService.removeProduct(12345)).thenReturn(null);
		
		mockMvc.perform(MockMvcRequestBuilders.delete("/deleteProduct/12345" )).andExpect(status().isOk());
	}
		
		
		
	}

	
		
	


