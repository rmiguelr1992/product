package com.hiberus.products.repository;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.hiberus.products.exception.ProductException;
import com.hiberus.products.exception.ProductNotFoundException;
import com.hiberus.products.model.Product;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

	private Set<Product> products;

	public ProductRepositoryImpl() {

		// Inicializamos los productos con un hashset ya que es el mejor objeto para
		// manejar datos
		products = new HashSet<>();
	}

	@Override
	public Optional<List<Product>> findAll() {

		return CollectionUtils.isNotEmpty(products) ? Optional.of(products.stream().collect(Collectors.toList()))
				: Optional.empty();
	}

	@Override
	public Optional<Product> findById(Long id) {

		if (CollectionUtils.isNotEmpty(products)) {
			return products.stream().filter(product -> Long.compare(id, product.getId()) == 0).findFirst();
		}
		return Optional.empty();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> findByName(String name) {

		if (CollectionUtils.isNotEmpty(products)) {
			return products.stream().filter(product -> StringUtils.equals(name, product.getName()))
					.collect(Collectors.toList());
		}
		return Collections.EMPTY_LIST;
	}

	@Override
	public void deleteById(Long idProduct) {

		if (products != null) {
			boolean removed = products.removeIf(product -> Long.compare(idProduct, product.getId()) == 0);
			if (BooleanUtils.isFalse(removed)) {
				throw new ProductNotFoundException(
						new StringBuilder("No se ha encontrado ningún producto el identificador ").append(idProduct)
								.toString());
			}
		}
	}

	@Override
	public Product save(Product entityBBDD) {

		if (products == null) {
			products = new HashSet<>();
		}
		Product p = products.stream().filter(product -> Long.compare(entityBBDD.getId(), product.getId()) == 0)
				.findFirst().orElse(null);
		if(p == null) {
			products.add(entityBBDD);
			return entityBBDD;
		}
		throw new ProductException("El producto que intenta guardar ya existe");
	}

	@Override
	public Product merge(Product entityBBDD) {
		if (products == null) {
			products = new HashSet<>();
		}
		Product p = products.stream().filter(product -> Long.compare(entityBBDD.getId(), product.getId()) == 0)
				.findFirst().orElse(null);
		if(p != null) {
			deleteById(p.getId());
			products.add(entityBBDD);
			return entityBBDD;
		}
		throw new ProductException("El producto que intenta actualizar no existe");
	}

}
