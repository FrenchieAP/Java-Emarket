package com.antoine.ecommerce.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.antoine.ecommerce.models.Category;
import com.antoine.ecommerce.models.Product;
import com.antoine.ecommerce.repositories.ProductRepository;

@Service
public class ProductService {
	private final ProductRepository productRepo;
	
	public ProductService(ProductRepository productRepo) {
		this.productRepo = productRepo;
	}
	
	public List<Product> allProducts(){
		return productRepo.findAll();
	}
	
	public List<Product> getAssignedCategories(Category category){
		return productRepo.findAllByCategories(category);
	}
	
	public List<Product> getUnassignedCategories(Category category){
		return productRepo.findByCategoriesNotContains(category);
	}
	
	public Product findById(Long id) {
		Optional<Product> optionalProduct = productRepo.findById(id);
		if(optionalProduct.isPresent()) {
			return optionalProduct.get();
		}else {
			return null;
		}
	}
	
	public Product addProduct(Product product) {
		return productRepo.save(product);
	}
	
	public Product updateProduct(Product product) {
		return productRepo.save(product);
	}
	
	public void deleteProduct(Product product) {
		productRepo.delete(product);
	}
	
	
}