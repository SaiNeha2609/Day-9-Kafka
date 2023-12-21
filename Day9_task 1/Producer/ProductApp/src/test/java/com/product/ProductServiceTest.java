package com.product;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.product.model.Product;
import com.product.repository.ProductRepository;
import com.product.service.ProductServiceImpl;

public class ProductServiceTest {
	@Mock
	private ProductRepository productRepository;
	
	@InjectMocks
	private ProductServiceImpl productService;
	
	@Autowired
	private MockMvc mockmvc;
	
	@BeforeEach
	public void init()
	{
		MockitoAnnotations.openMocks(this);
		mockmvc =MockMvcBuilders.standaloneSetup(productService).build();
	}
	
	private List<Product> prodList = new ArrayList<>();
	
	@Test
	public void getAllProductSuccess() throws Exception
	{
		Product prodObj = new Product();
		
		prodObj.setId(101);;
		prodObj.setName("Mobile");
		prodObj.setPrice(30);
		
		prodList.add(prodObj);
		when(productRepository.findAll()).thenReturn(prodList);
		
		List<Product> plist = productService.getAllProduct();
		
		assertEquals(prodList, plist);
		
	}
	
	
	@Test
	public void getAllProductFailue() throws Exception
	{
		
		
		
		when(productRepository.findAll()).thenReturn(null);
		
		List<Product> plist = productService.getAllProduct();
		
		assertNull(plist);
		
	}
	
	
	@Test
	public void createProductSuccess() throws Exception
	{
		Product pObj = new Product();
		
		pObj.setId(101);
		pObj.setName("Mobile");
		pObj.setPrice(30);
		
		prodList.add(pObj);
		when(productRepository.save(any())).thenReturn(pObj);
		
		Product p1 = productService.createProduct(pObj);
		
		assertEquals(pObj, p1);
		
	}
	
	
	@Test
	public void createProductFailure() throws Exception
	{
		Product pObj = new Product();
		
		pObj.setId(101);
		pObj.setName("Mobile");
		pObj.setPrice(20);
		
		prodList.add(pObj);
		when(productRepository.save(any())).thenReturn(null);
		
		Product u1 = productService.createProduct(pObj);
		
		assertNull( u1);
		
	}
	@Test
	public void updateProductSuccess() throws Exception
	{
		Product pObj = new Product();
		
		pObj.setId(101);
		pObj.setName("Mobile");
		pObj.setPrice(20);
		
		prodList.add(pObj);
		when(productRepository.save(any())).thenReturn(pObj);
		
		Product p1 = productService.updateProduct(pObj);
		
		assertEquals(pObj, p1);
		
	}
	@Test
	public void updateProductFailure() throws Exception
	{
		Product pObj = new Product();
		
		pObj.setId(101);
		pObj.setName("Mobile");
		pObj.setPrice(20);
		
		prodList.add(pObj);
		when(productRepository.save(any())).thenReturn(null);
		
		Product u1 = productService.updateProduct(pObj);
		
		assertNull( u1);
		
	}
	
	@Test
	public void deleteProductSuccess() throws Exception{
Product pObj = new Product();
		
		pObj.setId(101);
		
		doNothing().when(productRepository).deleteById(pObj.getId());
		productService.deleteProduct(101);
		verify(productRepository).deleteById(pObj.getId());
			}
	
	@Test
	public void deleteProductFailure() throws Exception{
Product pObj = new Product();
		
		doNothing().when(productRepository).deleteById(null);
		productService.deleteProduct(0);
		verify(productRepository).deleteById(pObj.getId());
		
	}

	
}
