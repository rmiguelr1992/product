package com.hiberus.products.exception;

public class ProductBadRequestException extends RuntimeException{


	/**
	 * 
	 */
	private static final long serialVersionUID = -3846688762205639268L;

	public ProductBadRequestException() {
		super();
	}

	public ProductBadRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ProductBadRequestException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProductBadRequestException(String message) {
		super(message);
	}

	public ProductBadRequestException(Throwable cause) {
		super(cause);
	}


}
