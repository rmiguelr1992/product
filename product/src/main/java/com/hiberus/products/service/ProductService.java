package com.hiberus.products.service;

import java.util.List;

import com.hiberus.products.dto.ProductDto;
import com.hiberus.products.exception.ProductException;

public interface ProductService {

	/**
	 * Save product.
	 *
	 * @param product the product
	 * @return the product dto
	 * @throws ProductException 
	 */
	public ProductDto saveProduct(ProductDto product);
	
	/**
	 * Update product.
	 *
	 * @param product the product
	 * @return the product dto
	 * @throws ProductException the product exception
	 */
	public ProductDto updateProduct(ProductDto product);
	
	/**
	 * Delete product.
	 *
	 * @param idProduct the product
	 */
	public void deleteProduct(Long idProduct);
	
	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<ProductDto> findAll();

	/**
	 * Find product.
	 *
	 * @param idProduct the id product
	 * @param name the name
	 * @return the list
	 */
	public List<ProductDto> findProduct(Long idProduct, String name);

	/**
	 * Find by name.
	 *
	 * @param name the name
	 * @return the list
	 */
	List<ProductDto> findByName(String name);

}
