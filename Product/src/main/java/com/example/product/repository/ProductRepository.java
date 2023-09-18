package com.example.product.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.product.model.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
	public Product findByProductId(int id);

	public List<Product> findByProductType(String type);

	public List<Product> findByProductName(String name);

	public List<Product> findByProductPrice(Double price);

	public List<Product> findByProductRating(Double rating);
	
	public Product findByProductAvalibility(Boolean  avalibility);

}
