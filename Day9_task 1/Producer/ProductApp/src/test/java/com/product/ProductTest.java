package com.product;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.product.model.Product;

public class ProductTest {

	@Test
	public void testGetName() {
		Product prodObj = Mockito.mock(Product.class); // creating mock object
		when(prodObj.getName()).thenReturn(null);

		System.out.println("Mocked object result before assignment is:: " + prodObj.getName());

		Product newObj = Mockito.mock(Product.class);

		when(newObj.getName()).thenReturn("Mobile");

	}

	@Test
	public void testGetPrice() {
		Product prodObj = Mockito.mock(Product.class); // creating mock object

		when(prodObj.getPrice()).thenReturn(6);

	}

	@Test
	public void testGetId() {
		

		Product newObj = Mockito.mock(Product.class);
		long pid = 20;
		Mockito.when(newObj.getId()).thenReturn(pid);

	}

	@Test
	public void testSetName() {
		Product prodObj = Mockito.mock(Product.class);
		doNothing().when(prodObj).setName("Mobile");
	}

	@Test
	public void testSetPrice() {
		Product prodObj = Mockito.mock(Product.class);
		doNothing().when(prodObj).setPrice(20);
	}

	@Test
	public void testSetId() {
		Product prodObj = Mockito.mock(Product.class);
		doNothing().when(prodObj).setId(20);
	}

}
