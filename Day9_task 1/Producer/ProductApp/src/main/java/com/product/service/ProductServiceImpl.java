package com.product.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.product.model.Product;
import com.product.repository.ProductRepository;

@Service
@Transactional
public class ProductServiceImpl  implements ProductService{

	 @Autowired
	    private ProductRepository productRepository;
	
	 @Override
	public Product createProduct(Product product) {
		// TODO Auto-generated method stub
		return productRepository.save(product);
	
	 }

	@Override
	public Product updateProduct(Product product) {
		// TODO Auto-generated method stub
		 Optional< Product > productDb = this.productRepository.findById(product.getId());

	        
	            Product productUpdate = productDb.get();
	           
	            productUpdate.setName(product.getName());
	            productUpdate.setPrice(product.getPrice());
	            productRepository.save(productUpdate);
	            return productUpdate;
	}

	@Override
	public List<Product> getAllProduct() {
		// TODO Auto-generated method stub
		 return this.productRepository.findAll();
	}

	@Override
	public Product getProductById(long productId) {
		// TODO Auto-generated method stub
		
		return this.productRepository.findById(productId).get();
	}

	@Override
	public void deleteProduct(long productId) {
		productRepository.deleteById(productId);
}
}
