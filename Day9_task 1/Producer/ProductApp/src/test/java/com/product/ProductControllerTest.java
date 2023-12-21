package com.product;




import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.product.controller.ProductController;
import com.product.model.Product;
import com.product.service.ProductServiceImpl;

@AutoConfigureMockMvc
@SpringBootTest
public class  ProductControllerTest
{
	@Mock
	private ProductServiceImpl ps;
	
	@InjectMocks
	private ProductController pc;
	
	@Autowired
	private MockMvc mockmvc;
	
	
	
	@BeforeEach
	public void init()
	{
		MockitoAnnotations.openMocks(this);
		mockmvc = MockMvcBuilders.standaloneSetup(pc).build();
	}
	
private List<Product> prodList = new ArrayList<>();
	
	@Test
	public void getAllProductSuccess() throws Exception
	{
		Product userObj = new Product ();
		
		userObj.setId(101);
		userObj.setName("Mobile");
		userObj.setPrice(30);
		
		prodList.add(userObj);
		when(ps.getAllProduct()).thenReturn(prodList);
		
		List<Product > plist = ps.getAllProduct();//redundant
		
		assertEquals(prodList, plist);// redundant
		assertEquals(1, prodList.size());
		
	mockmvc.perform(MockMvcRequestBuilders.get("/getAll").contentType(MediaType.APPLICATION_JSON))
						.andExpect(MockMvcResultMatchers.status().isOk());
		
	}
	
	
	@Test
	public void getAllProductFailure() throws Exception
	{
	
		prodList.clear();
		when(ps.getAllProduct()).thenReturn(prodList);//pass null
		
		
		
		assertEquals(0, prodList.size());
		
	mockmvc.perform(MockMvcRequestBuilders.get("/getAll").contentType(MediaType.APPLICATION_JSON))
						.andExpect(MockMvcResultMatchers.status().isOk());
		
	}
	
	
	@Test
	public void createProductSuccess() throws Exception
	{
		Product pObj = new Product();
		
		pObj.setId(101);
		pObj.setName("Mobile");
		pObj.setPrice(20);
		
		prodList.add(pObj);
		when(ps.createProduct(any())).thenReturn(pObj);
		
		Product p1 = ps.createProduct(pObj);
		
		
		assertEquals(1, prodList.size());
		
	mockmvc.perform(MockMvcRequestBuilders.post("/add").contentType(MediaType.APPLICATION_JSON)
			.content(new ObjectMapper().writeValueAsString(pObj)))
						.andExpect(MockMvcResultMatchers.status().isOk());
		
	}
	
	
	@Test
	public void createProductFailure() throws Exception
	{
		
		when(ps.createProduct(any())).thenReturn(null);
		
		Product  p1 = ps.createProduct(null);
		
		
		assertNull(p1);
		
	mockmvc.perform(MockMvcRequestBuilders.post("/add").contentType(MediaType.APPLICATION_JSON)
			.content(new ObjectMapper().writeValueAsString(null)))
						.andExpect(MockMvcResultMatchers.status().isBadRequest());
		
	}
	
	@Test
	public void updateProductSuccess() throws Exception
	{
		Product pObj = new Product ();
		
				
		pObj.setId(101);
		pObj.setName("Mobile");
		pObj.setPrice(20);
		
				prodList.add(pObj);
		when(ps.updateProduct(any())).thenReturn(pObj);
		

		Product p1 = ps.createProduct(pObj);
		
		
		assertEquals(1, prodList.size());
		
	mockmvc.perform(MockMvcRequestBuilders.put("/update/101").contentType(MediaType.APPLICATION_JSON)
			.content(new ObjectMapper().writeValueAsString(pObj)))
						.andExpect(MockMvcResultMatchers.status().isOk());
				
	}
	

	
	@Test
	public void updateProductFailure() throws Exception
	{
when(ps.updateProduct(any())).thenReturn(null);
		
		Product  p1 = ps.updateProduct(null);
		
		
		assertNull(p1);
		
	mockmvc.perform(MockMvcRequestBuilders.put("/update/101").contentType(MediaType.APPLICATION_JSON)
			.content(new ObjectMapper().writeValueAsString(null)))
						.andExpect(MockMvcResultMatchers.status().isBadRequest());
		

		
	}
	

	@Test
	public void deleteProductSuccess() throws Exception
	{
		Product pObj = new Product ();
		pObj.setId(101);;
		
				prodList.add(pObj);
				doNothing().when(ps).deleteProduct(pObj.getId());
				ps.deleteProduct(101);
				verify(ps).deleteProduct(pObj.getId());
		
		
				
	mockmvc.perform(MockMvcRequestBuilders.delete("/delete/101").contentType(MediaType.APPLICATION_JSON))
						.andExpect(MockMvcResultMatchers.status().isOk());
		
	}
	@Test
	public void deleteProductFailure() throws Exception
	{
		Product pObj = new Product ();
		
				doNothing().when(ps).deleteProduct(0);
				ps.deleteProduct(0);
				verify(ps).deleteProduct(pObj.getId());
		
		
				
	mockmvc.perform(MockMvcRequestBuilders.delete("/delete/101").contentType(MediaType.APPLICATION_JSON))
						.andExpect(MockMvcResultMatchers.status().isOk());
		
	}

}







