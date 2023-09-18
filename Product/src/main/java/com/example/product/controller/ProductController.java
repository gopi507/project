package com.example.product.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.product.exception.ProductException;
import com.example.product.model.Product;
import com.example.product.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	ProductService pService;

	@GetMapping("/getAllList")
	public List<Product> getAllList() {
		return pService.getAll();

	}

	@GetMapping("/getProductById/{id}")
	public Product getProductById(@PathVariable("id") int id) throws ProductException {
		Product integer=pService.getByProductId(id);
		if(integer==null) {
			throw new ProductException("id not avaliable");
		}
		return pService.getByProductId(id);
	}

	@GetMapping("/getProductByType/{type}")
	
	public List<Product> getProductByType(@PathVariable("type") String type) throws ProductException{
		List<Product> productType=pService.getByProductType(type);
		if(productType.isEmpty()) {
			throw new ProductException("you given type as a input is not avaliable");
		}
		return pService.getByProductType(type);
	}

	@GetMapping("/getProductByName/{name}")
	
	public List<Product> getProductByName(@PathVariable("name") String name) throws ProductException {
		List<Product> productName=pService.getByProductName(name);
		if(productName.isEmpty()) {
			throw new ProductException("you given type as a input is not avaliable");
		}
		return pService.getByProductName(name);
	}

	@GetMapping("/getProductByPrice/{low}/{high}")
	public List<Product> getProductByPrice(@PathVariable("low") Double lowprice,@PathVariable("high") Double highprice)
	{
		return pService.getByProductPrice(lowprice,highprice);
	}

	@GetMapping("/getProductByRating/{rating}")
	public List<Product> getProductByRating(@PathVariable("rating") Double rating) {
		return pService.getByProductRating(rating);
	}
	@GetMapping("/checkProductAvalibility/{name}")
	public Boolean  checkProductAvalibility(@PathVariable("name") String productName) {
		return pService.getProductAvalibility(productName);
	}

	@PostMapping("/addProduct")
	public Product addProduct(@RequestBody Product product) {
		return pService.addProduct(product);
	}
	
	

	@PutMapping("/updateProduct")
	public Product updateProduct(@RequestBody Product product) throws ProductException {
		
		return pService.updateProduct(product);
	}

	@DeleteMapping("/deleteProduct/{id}")
	
	public String deleteProduct(@PathVariable("id") int id) throws ProductException {
		
		Product productDelete=pService.getByProductId(id);
		if(productDelete==null) {
			throw new ProductException("id is not avaliable");
		}
		return pService.removeProduct(id);
	}

}
