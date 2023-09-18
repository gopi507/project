package com.example.product.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter

@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "product")
public class Product {
	@Id
	public int productId;
	public String productType;
	public String productName;
	public Double productPrice;
	public Double productRating;
    public Boolean productAvalibility;
}
