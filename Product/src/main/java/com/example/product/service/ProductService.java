package com.example.product.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.product.exception.ProductException;
import com.example.product.model.Product;
import com.example.product.repository.ProductRepository;

@Service
public class ProductService {
	
	
	

	@Autowired
	ProductRepository productRepository;

	public List<Product> getAll() {
		
		return productRepository.findAll();
		
	}

	public Product getByProductId(int id) {

		return productRepository.findByProductId(id);

	}

	public List<Product> getByProductType(String type) {
		return productRepository.findByProductType(type);

	}

	public List<Product> getByProductName(String name) {
     
		return productRepository.findByProductName(name);

	}
	
	

	public List<Product> getByProductPrice(Double lowPrice,Double highPrice ) {
		
		List<Product> products=productRepository.findAll();
		
		List<Product> priceList=new ArrayList<>();
		products.stream().filter(p->p.productPrice<=highPrice&&p.productPrice>=lowPrice).forEach(p->priceList.add(p));

		return priceList;
//		for (Product product : products) {
//			
//			if(product.productPrice<=highPrice&&product.productPrice>=lowPrice) {
//				priceList.add(product);
//			}
//			
//			
//		}
//		return priceList;
		
	}

	public List<Product> getByProductRating(Double Rating) {
		List<Product> findProducts=productRepository.findAll();
		List<Product> productsRating=new ArrayList<>();
		
		findProducts.stream().filter(p->p.productRating>=Rating).forEach(p->productsRating.add(p));
		
		return productsRating;

	}
	public Boolean getProductAvalibility(String productName) {
		
		List<Product> products = productRepository.findByProductName(productName);
		if(products.isEmpty()) {
			return false;
		}
		else {
			return true;
		}
		
		
		
		
		
	}

	public Product addProduct(Product product) {
		return productRepository.save(product);
	}

	public Product updateProduct(Product product) throws ProductException {
		Product product2 = productRepository.findByProductId(product.getProductId());
		if(product2==null) {
			throw new ProductException("given id is not avaliable in your database");
		}
		product2.setProductType(product.getProductType());
		product2.setProductName(product.getProductName());

		product2.setProductPrice(product.getProductPrice());
		product2.setProductRating(product.getProductRating());
		
		return productRepository.save(product2);
	}

	public String removeProduct(int id) {
		Product product = productRepository.findByProductId(id);
		productRepository.delete(product);
		return "product deleted successfully";

	}

}
