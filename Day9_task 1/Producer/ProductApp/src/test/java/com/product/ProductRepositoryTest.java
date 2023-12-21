package com.product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import com.product.model.Product;
import com.product.repository.ProductRepository;

@DataJpaTest
@AutoConfigureMockMvc
public class ProductRepositoryTest {

	@Autowired
	private ProductRepository productRepository;
	
	
private Product product = new Product();//real object
	
	@BeforeEach
	public void init()
	{
		product.setId(1);
		product.setName("Mobile");
		product.setPrice(50);
	}
	
	@Test
	public void saveProductSuccess() throws Exception
	{
		Product p1=null;
		productRepository.save(product);
		p1= productRepository.findById(product.getId()).get();
		System.out.println(p1);
		
		assertEquals(product.getName(), p1.getName());
	}
	
	@Test
	public void saveProductFailure() throws Exception
	{
		Product p1=null;
		if(productRepository.findAll().toString().isEmpty())
		{
			productRepository.save(product);
			p1 = productRepository.findById(product.getId()).get();
		}
		
		assertNull(p1);
		
	}
	

	
	
}
