package com.hiberus.products.dto.path;

public class ApiPath {

	/** The Constant PATH_PRODUCTS. */
	public static final String URL_PATH = "/api/v1";
	
	//PRODUCTS
	public static final String PATH_PRODUCTS = "/products";
	public static final String PATH_PRODUCTS_SEARCH = "/products/search";
	public static final String PATH_PRODUCTS_BY_ID = PATH_PRODUCTS + "/{idProduct}";
	
	//NO INSTANCIATE
	private ApiPath() {
		
	}
}
