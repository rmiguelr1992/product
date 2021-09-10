package com.hiberus.products.repository;

import java.util.List;
import java.util.Optional;

import com.hiberus.products.model.Product;

public interface ProductRepository  {

	Optional<Product> findById(Long id);
	
	List<Product> findByName(String name);

	Optional<List<Product>> findAll();

	void deleteById(Long idProduct);

	Product save(Product entityBBDD);

	Product merge(Product entityBBDD);
	
}
