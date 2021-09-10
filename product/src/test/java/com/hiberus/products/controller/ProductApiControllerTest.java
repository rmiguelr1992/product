package com.hiberus.products.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hiberus.products.dto.ProductDto;
import com.hiberus.products.dto.path.ApiPath;
import com.hiberus.products.service.ProductService;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductApiControllerTest {

	@Autowired
	private MockMvc mockMvc;
	

	@MockBean
	private ProductService productService;


	@Test
	public void findProducts() throws Exception {

		List<ProductDto> products = new ArrayList<>();
		ProductDto product = new ProductDto();
		product.setDescription("1");
		product.setName("1");
		product.setId(1L);
		products.add(product);
		
		Mockito.when(productService.findAll()).thenReturn(products);
		
		
		mockMvc.perform(MockMvcRequestBuilders
			      .get(ApiPath.URL_PATH+ApiPath.PATH_PRODUCTS)
			      .accept(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk());

	}

	
	@Test
	public void findProduct() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders
			      .get(ApiPath.URL_PATH+ApiPath.PATH_PRODUCTS_SEARCH,1)
			      .accept(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk());

	}
	
	@Test
	public void findProductByName() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders
			      .get(ApiPath.URL_PATH+ApiPath.PATH_PRODUCTS_SEARCH,"name")
			      .accept(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk());

	}
	
	@Test
	public void addProduct() throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		ProductDto product = new ProductDto();
		product.setDescription("1");
		product.setName("1");
		product.setId(1L);
		
		String body = mapper.writeValueAsString(product);
		
		mockMvc.perform(MockMvcRequestBuilders
			      .post(ApiPath.URL_PATH+ApiPath.PATH_PRODUCTS)
			      .contentType(MediaType.APPLICATION_JSON_UTF8)
			      .content(body))
			      .andExpect(status().isCreated());

	}
	
	@Test
	public void updateProduct() throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		ProductDto product = new ProductDto();
		product.setDescription("1");
		product.setName("1");
		product.setId(1L);
		
		String body = mapper.writeValueAsString(product);
		
		mockMvc.perform(MockMvcRequestBuilders
			      .put(ApiPath.URL_PATH+ApiPath.PATH_PRODUCTS_BY_ID,1L)
			      .contentType(MediaType.APPLICATION_JSON_UTF8)
			      .content(body))
			      .andExpect(status().isOk());

	}
	
	@Test
	public void deleteProduct() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders
			      .delete(ApiPath.URL_PATH+ApiPath.PATH_PRODUCTS_BY_ID,1L))
			      .andExpect(status().isOk());

	}

}
