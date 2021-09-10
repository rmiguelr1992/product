package com.hiberus.products.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hiberus.products.dto.ProductDto;
import com.hiberus.products.dto.path.ApiPath;
import com.hiberus.products.service.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;

@RestController
@Api(value = "api-products", tags = "Products")
@RequestMapping(path = ApiPath.URL_PATH)
public class ProductApiController {

	@Autowired
	private ProductService productService;

	/**
	 * Método que se encarga de obtener el listado completo de productos
	 *
	 * @return the response entity
	 */
	@GetMapping(ApiPath.PATH_PRODUCTS)
	@ResponseStatus(HttpStatus.OK)
	public List<ProductDto> findProducts() {
		return productService.findAll();
	}

	/**
	 * Método que se encarga de buscar un producto por su identificador
	 *
	 * @param idProduct the id product
	 * @return the product dto
	 */
	@GetMapping(ApiPath.PATH_PRODUCTS_SEARCH)
	@ResponseStatus(HttpStatus.OK)
	public List<ProductDto> findProduct(@ApiParam(value = "idProduct", required = false) Long idProduct,
			@ApiParam(value = "name", required = false) String name) {

		return productService.findProduct(idProduct, name);
	}

	/**
	 * Método que se encarga de la creación de un producto
	 *
	 * @param product the product
	 * @return the product dto
	 */
	@PostMapping(ApiPath.PATH_PRODUCTS)
	@ResponseStatus(HttpStatus.CREATED)
	public ProductDto addProduct(@ApiParam(value = "product", required = true) @Valid @RequestBody ProductDto product) {
		return productService.saveProduct(product);
	}

	/**
	 * Método que se encarga de la actualización de un producto
	 *
	 * @param idProduct the id product
	 * @param product   the product
	 * @return the product dto
	 */
	@PutMapping(ApiPath.PATH_PRODUCTS_BY_ID)
	@ResponseStatus(HttpStatus.OK)
	public ProductDto updateProduct(@PathVariable(value = "idProduct", required = true) Long idProduct,
			@ApiParam(value = "product", required = true) @RequestBody ProductDto product) {
		return productService.updateProduct(product);
	}

	/**
	 * Método que se encarga del borrado de un producto
	 *
	 * @param idProduct the id product
	 * @return the response entity
	 */
	@DeleteMapping(ApiPath.PATH_PRODUCTS_BY_ID)
	@ResponseStatus(HttpStatus.OK)
	public void deleteProduct(@PathVariable(value = "idProduct", required = true) Long idProduct) {
		productService.deleteProduct(idProduct);
	}
}
