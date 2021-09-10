package com.hiberus.products.repository;

import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.hiberus.products.exception.ProductException;
import com.hiberus.products.model.Product;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductRepositoryImplTest {

	@Autowired
	private ProductRepositoryImpl repository;
	
	@Test
	@Order(value = 1)
	public void saveProduct() throws ProductException {
		
		Product product = new Product();
		product.setDescription("Desc Prueba 2");
		product.setId(2L);
		product.setName("Prueba 2");
		
		Product result = repository.save(product);
		
		Assert.assertNotNull(result);
	}
	
	@Test
	@Order(value = 2)
	public void updateProduct() throws ProductException {
		

		Product product = new Product();
		product.setDescription("Desc Prueba 2");
		product.setId(2L);
		product.setName("Prueba 2");
		
		Product result = repository.merge(product);
		
		Assert.assertNotNull(result);
	}
	

	@Test
	@Order(value = 6)
	public void deleteProduct() throws ProductException {
		repository.deleteById(2L);
	}

	@Test
	@Order(value = 3)
	public void findAll() {

		Optional<List<Product>> result = repository.findAll();
		
		Assert.assertTrue(result.isPresent());
	}

	
	@Test
	@Order(value = 4)
	public void findProductById() throws ProductException {

		
		Optional<Product> result = repository.findById(2L);
		
		Assert.assertTrue(result.isPresent());
	}
	
	@Test
	@Order(value = 5)
	public void findProductByName() throws ProductException {

		List<Product> result = repository.findByName("Prueba 1");
		
		Assert.assertNotNull(result);
	}
	
}
