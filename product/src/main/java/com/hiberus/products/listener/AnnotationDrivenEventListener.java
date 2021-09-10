package com.hiberus.products.listener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.hiberus.products.dto.ProductDto;
import com.hiberus.products.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AnnotationDrivenEventListener {

	@Autowired
	private ProductService productService;

	@EventListener
	@Async
	public void handle(ApplicationStartedEvent cse) {

		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		InputStream is = classloader.getResourceAsStream("productos.txt");

		if (is != null) {
			readAndSetProductsInContext(is);
		}
	}

	private void readAndSetProductsInContext(InputStream is) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
			List<ProductDto> products = new ArrayList<>();
			goThroughInfoFile(products, br);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
	}

	private void goThroughInfoFile(List<ProductDto> products, BufferedReader br)
			throws NumberFormatException, IOException {
		String read;
		while ((read = br.readLine()) != null) {
			if (StringUtils.isNotBlank(read)) {
				String[] splitInfo = read.split(",");
				if (splitInfo != null && splitInfo.length > 0) {
					ProductDto product = new ProductDto();
					product.setId(Long.valueOf(splitInfo[0]));
					product.setName(splitInfo[1]);
					product.setDescription(splitInfo[2]);
					products.add(product);
					productService.saveProduct(product);
					log.info(new StringBuilder("Product loaded --> ").append(product.toString()).toString());
				}
			}
		}
	}
}