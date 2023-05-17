package com.ecommerce.product.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class CreateProductInDtoTest {

	public CreateProductInDto buildCreateProductInDto(String name, String description, Integer quantity, double price,
			double discount, String manufacturer) {
		CreateProductInDto createProductInDto = new CreateProductInDto();
		createProductInDto.setDescription(description);
		createProductInDto.setDiscount(discount);
		createProductInDto.setManufacturer(manufacturer);
		createProductInDto.setName(name);
		createProductInDto.setPrice(price);
		createProductInDto.setQuantity(quantity);
		return createProductInDto;
	}
	
	@Test
	public void getterSetterTest() {

		CreateProductInDto createProductInDto = new CreateProductInDto();

		String name = "name";
		String description = "description";
		Integer quantity = 5;
		double price = 1.0;
		double discount = 1.0;
		String manufacturer = "manufacturer";

		assertNull(createProductInDto.getDescription());
		createProductInDto.setDescription(description);
		assertEquals(description, createProductInDto.getDescription());

		assertEquals(0.0, createProductInDto.getDiscount());
		createProductInDto.setDiscount(discount);
		assertEquals(discount, createProductInDto.getDiscount());

		assertNull(createProductInDto.getManufacturer());
		createProductInDto.setManufacturer(manufacturer);
		assertEquals(manufacturer, createProductInDto.getManufacturer());

		assertNull(createProductInDto.getName());
		createProductInDto.setName(name);
		assertEquals(name, createProductInDto.getName());

		assertEquals(0.0, createProductInDto.getPrice());
		createProductInDto.setPrice(price);
		assertEquals(price, createProductInDto.getPrice());

		assertNull(createProductInDto.getQuantity());
		createProductInDto.setQuantity(quantity);
		assertEquals(quantity, createProductInDto.getQuantity());

		System.out.println(createProductInDto);

	}

	@Test
	public void toStringTest() {

		String createProductInDtoString = "CreateProductInDto(name=name, description=description, quantity=5, price=1.0, discount=1.0, manufacturer=manufacturer)";

		String name = "name";
		String description = "description";
		Integer quantity = 5;
		double price = 1.0;
		double discount = 1.0;
		String manufacturer = "manufacturer";

		CreateProductInDto createProductInDto = buildCreateProductInDto(name, description, quantity, price, discount,
				manufacturer);

		assertEquals(createProductInDtoString, createProductInDto.toString());

	}

	@Test
	public void equalsAndHashCodeTest() {

		String name = "name";
		String description = "description";
		Integer quantity = 5;
		double price = 1.0;
		double discount = 1.0;
		String manufacturer = "manufacturer";

		CreateProductInDto createProductInDto = buildCreateProductInDto(name, description, quantity, price, discount,
				manufacturer);
		
		CreateProductInDto createProductInDto2 = buildCreateProductInDto(name, description, quantity, price, discount,
				manufacturer);

		assertEquals(createProductInDto, createProductInDto);
		assertEquals(createProductInDto.hashCode(), createProductInDto.hashCode());

		assertNotEquals(createProductInDto, new Object());
		assertNotEquals(createProductInDto2, null);

		assertEquals(createProductInDto2, createProductInDto2);
		assertEquals(createProductInDto2.hashCode(), createProductInDto2.hashCode());

		createProductInDto = buildCreateProductInDto("changed", description, quantity, price, discount,
				manufacturer);
		assertNotEquals(createProductInDto, createProductInDto2);

		createProductInDto = buildCreateProductInDto(name, "changed", quantity, price, discount,
				manufacturer);
		assertNotEquals(createProductInDto, createProductInDto2);
		
		createProductInDto = buildCreateProductInDto(name, description, 2, price, discount,
				manufacturer);
		assertNotEquals(createProductInDto, createProductInDto2);
	
		createProductInDto = buildCreateProductInDto(name, description, quantity, 2.0, discount,
				manufacturer);
		assertNotEquals(createProductInDto, createProductInDto2);
		
		createProductInDto = buildCreateProductInDto(name, description, quantity, price, 2.9,
				manufacturer);
		assertNotEquals(createProductInDto, createProductInDto2);
		
		createProductInDto = buildCreateProductInDto(name, description, quantity, price, discount,
				"changed");
		assertNotEquals(createProductInDto, createProductInDto2);

	}

}
