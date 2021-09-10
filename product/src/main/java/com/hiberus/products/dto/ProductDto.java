package com.hiberus.products.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class ProductDto implements Comparable {

	private Long id;
	@NotBlank(message = "Name can not be empty")
	private String name;
	@NotBlank(message = "Description can not be empty")
	private String description;
	
	
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description=" + description + "]";
	}


	@Override
	public int compareTo(Object o) {
		ProductDto dto = (ProductDto) o;
		return Long.compare(this.id,dto.getId());
	}
}
