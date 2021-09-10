package com.hiberus.products.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.hiberus.products.dto.ProductDto;
import com.hiberus.products.exception.ProductBadRequestException;
import com.hiberus.products.exception.ProductException;
import com.hiberus.products.exception.ProductNotFoundException;
import com.hiberus.products.model.Product;
import com.hiberus.products.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ProductServiceImplTest {

	@InjectMocks
	private ProductServiceImpl productService;
	
	@Mock
	private ProductRepository repository;
	
	@Test
	public void saveProduct() throws ProductException {
		
		Mockito.when(repository.save(Mockito.any())).thenReturn(new Product());

		ProductDto result = productService.saveProduct(new ProductDto());
		
		Assert.assertNotNull(result);
	}
	
	@Test
	public void updateProduct() throws ProductException {
		
		Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(new Product(1L,"1","1")));
		
		Mockito.when(repository.merge(Mockito.any())).thenReturn(new Product());

		ProductDto dto = new ProductDto();
		dto.setId(1L);
		ProductDto result = productService.updateProduct(dto);
		
		Assert.assertNotNull(result);
	}
	
	@Test
	public void updateProductException() throws ProductException {
		
		Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		
		ProductDto dto = new ProductDto();
		dto.setId(1L);
		
		Assertions.assertThrows(ProductNotFoundException.class, () -> {
			productService.updateProduct(dto);
		  });
	}

	@Test
	public void deleteProduct() throws ProductException {
		productService.deleteProduct(1L);
	}

	@Test
	public void findAll() {

		List<Product> products = new ArrayList<>();
		Product product = new Product();
		product.setDescription("1");
		product.setName("1");
		product.setId(1L);
		products.add(product);
		
		Mockito.when(repository.findAll()).thenReturn(Optional.of(products));
		
		List<ProductDto> result = productService.findAll();
		
		Assert.assertNotNull(result);
	}

	@Test
	public void findProductNull() throws ProductException {

		
		Assertions.assertThrows(ProductBadRequestException.class, () -> {
			productService.findProduct(null,null);
		  });
	}
	
	@Test
	public void findProductById() throws ProductException {

		Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(new Product(1L,"1","1")));
		
		List<ProductDto> result = productService.findProduct(1L,null);
		
		Assert.assertNotNull(result);
	}
	
	@Test
	public void findProductByIdNotFound() throws ProductException {

		Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		
		Assertions.assertThrows(ProductException.class, () -> {
			productService.findProduct(1L,null);
		  });
	}
	
	@Test
	public void findProductByName() throws ProductException {

		List<Product> list = new ArrayList<>();
		Product product = new Product(1L,"1","1");
		list.add(product);
		Mockito.when(repository.findByName(Mockito.anyString())).thenReturn(list);
		
		List<ProductDto> result = productService.findProduct(null,"1");
		
		Assert.assertNotNull(result);
	}
	
	@Test
	public void findProductByNameExcption() throws ProductException {

		Mockito.when(repository.findByName(Mockito.anyString())).thenReturn(null);
		
		Assertions.assertThrows(ProductException.class, () -> {
			productService.findProduct(null,"1");
		  });
	}

	@Test
	public void findByName() throws ProductException {

		List<Product> products = new ArrayList<>();
		Product product = new Product();
		product.setDescription("1");
		product.setName("1");
		product.setId(1L);
		products.add(product);
		
		Mockito.when(repository.findByName(Mockito.anyString())).thenReturn(products);
		
		List<ProductDto> result = productService.findByName("name");
		
		Assert.assertNotNull(result);
	}
}
