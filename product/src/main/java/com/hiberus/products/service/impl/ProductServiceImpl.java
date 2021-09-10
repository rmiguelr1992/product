package com.hiberus.products.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.hiberus.products.dto.ProductDto;
import com.hiberus.products.exception.ProductBadRequestException;
import com.hiberus.products.exception.ProductException;
import com.hiberus.products.exception.ProductNotFoundException;
import com.hiberus.products.model.Product;
import com.hiberus.products.repository.ProductRepository;
import com.hiberus.products.service.ProductService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository repository;

	@Override
	public ProductDto saveProduct(ProductDto product) {

		ModelMapper modelMapper = new ModelMapper();

		Product entity = modelMapper.map(product, Product.class);
		if (entity != null) {
			return modelMapper.map(repository.save(entity), ProductDto.class);
		}
		throw new ProductException(new StringBuilder("Ha ocurrido un problema al guardar el producto con id ")
				.append(product.getId()).toString());
	}

	@Override
	public ProductDto updateProduct(ProductDto product) {

		ModelMapper modelMapper = new ModelMapper();

		Product entityBBDD = repository.findById(product.getId()).orElseThrow(() -> new ProductNotFoundException(
				new StringBuilder("No se ha encontrado ningún producto para actualizar con id ").append(product.getId())
						.toString()));
		entityBBDD = modelMapper.map(product, Product.class);
		return modelMapper.map(repository.merge(entityBBDD), ProductDto.class);
	}

	@Override
	public void deleteProduct(Long idProduct) throws ProductException {
		repository.deleteById(idProduct);
	}

	@Override
	public List<ProductDto> findAll() {
		return getProductsList(repository.findAll().orElse(new ArrayList<>()));
	}

	@Override
	public List<ProductDto> findProduct(Long idProduct, String name) throws ProductException {

		if (idProduct != null) {
			ModelMapper modelMapper = new ModelMapper();
			Product entity = repository.findById(idProduct)
					.orElseThrow(() -> new ProductException(
							new StringBuilder("Ha ocurrido un problema al obtener el producto con id ")
									.append(idProduct).toString()));
			List<ProductDto> listDto = new ArrayList<>();
			listDto.add(modelMapper.map(entity, ProductDto.class));
			return listDto;
		} else if (StringUtils.isNotBlank(name)) {
			return findByName(name);
		}
		
		throw new ProductBadRequestException("Se debe informar el idProducto o el nombre");
	}

	@Override
	public List<ProductDto> findByName(String name) {

		List<Product> entities = repository.findByName(name);
		if(CollectionUtils.isEmpty(entities)) {
			throw new ProductException(
					new StringBuilder("Ha ocurrido un problema al obtener el producto por su name ").append(name)
							.toString());
		}
		return getProductsList(entities);
	}

	private List<ProductDto> getProductsList(List<Product> list) {

		ModelMapper modelMapper = new ModelMapper();
		List<ProductDto> products = new ArrayList<>();
		list.stream().forEach(p -> products.add(modelMapper.map(p, ProductDto.class)));
		if (CollectionUtils.isEmpty(products)) {
			throw new ProductNotFoundException("No se ha encontrado productos");
		}
		Collections.sort(products);
		return products;
	}

}
