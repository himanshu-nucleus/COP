package com.ecommerce.product.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class CreateProductOutDtoTest {

	public CreateProductOutDto buildCreateProductOutDto(String id, String name, String description, Integer quantity,
	        double price, double discount, String manufacturer, Long userId) {
		CreateProductOutDto createProductOutDto = new CreateProductOutDto();
		createProductOutDto.setDescription(description);
		createProductOutDto.setDiscount(discount);
		createProductOutDto.setId(id);
		createProductOutDto.setManufacturer(manufacturer);
		createProductOutDto.setName(name);
		createProductOutDto.setPrice(price);
		createProductOutDto.setQuantity(quantity);
		createProductOutDto.setUserId(userId);
		return createProductOutDto;
	}

	@Test
	public void getterSetterTest() {

		String id = "id1";
		String name = "name";
		String description = "description";
		Integer quantity = 1;
		double price = 1.0;
		double discount = 1.0;
		String manufacturer = "manufacturer";
		Long userId = 1L;

		CreateProductOutDto createProductOutDto = new CreateProductOutDto();

		assertNull(createProductOutDto.getDescription());
		createProductOutDto.setDescription(description);
		assertEquals(description, createProductOutDto.getDescription());

		assertEquals(0.0, createProductOutDto.getDiscount());
		createProductOutDto.setDiscount(discount);
		assertEquals(discount, createProductOutDto.getDiscount());

		assertNull(createProductOutDto.getId());
		createProductOutDto.setId(id);
		assertEquals(id, createProductOutDto.getId());

		assertNull(createProductOutDto.getManufacturer());
		createProductOutDto.setManufacturer(manufacturer);
		assertEquals(manufacturer, createProductOutDto.getManufacturer());

		assertNull(createProductOutDto.getName());
		createProductOutDto.setName(name);
		assertEquals(name, createProductOutDto.getName());

		assertEquals(0.0, createProductOutDto.getPrice());
		createProductOutDto.setPrice(price);
		assertEquals(price, createProductOutDto.getPrice());

		assertNull(createProductOutDto.getQuantity());
		createProductOutDto.setQuantity(quantity);
		assertEquals(quantity, createProductOutDto.getQuantity());

		assertNull(createProductOutDto.getUserId());
		createProductOutDto.setUserId(userId);
		assertEquals(userId, createProductOutDto.getUserId());

		System.out.println(createProductOutDto);

	}

	@Test
	public void toStringTest() {

		String CreateProductOutDtoString = "CreateProductOutDto(id=id1, name=name, description=description, quantity=1, price=1.0, discount=1.0, manufacturer=manufacturer, userId=1)";

		String id = "id1";
		String name = "name";
		String description = "description";
		Integer quantity = 1;
		double price = 1.0;
		double discount = 1.0;
		String manufacturer = "manufacturer";
		Long userId = 1L;

		CreateProductOutDto createProductOutDto = buildCreateProductOutDto(id, name, description, quantity, price,
		        discount, manufacturer, userId);

		assertEquals(CreateProductOutDtoString, createProductOutDto.toString());

	}

	@Test
	public void equalsAndHashCodeTest() {

		String id = "id1";
		String name = "name";
		String description = "description";
		Integer quantity = 1;
		double price = 1.0;
		double discount = 1.0;
		String manufacturer = "manufacturer";
		Long userId = 1L;

		CreateProductOutDto createProductOutDto = buildCreateProductOutDto(id, name, description, quantity, price,
		        discount, manufacturer, userId);

		CreateProductOutDto createProductOutDto2 = buildCreateProductOutDto(id, name, description, quantity, price,
		        discount, manufacturer, userId);

		assertEquals(createProductOutDto, createProductOutDto);
		assertEquals(createProductOutDto.hashCode(), createProductOutDto.hashCode());

		assertNotEquals(createProductOutDto, new Object());
		assertNotEquals(createProductOutDto2, null);

		assertEquals(createProductOutDto2, createProductOutDto2);
		assertEquals(createProductOutDto2.hashCode(), createProductOutDto2.hashCode());

		createProductOutDto = buildCreateProductOutDto("2id", name, description, quantity, price,
		        discount, manufacturer, userId);

		createProductOutDto = buildCreateProductOutDto(id, "changed", description, quantity, price,
		        discount, manufacturer, userId);
		
		createProductOutDto = buildCreateProductOutDto(id, name, "changed", quantity, price,
		        discount, manufacturer, userId);
		
		createProductOutDto = buildCreateProductOutDto(id, name, description, 2, price,
		        discount, manufacturer, userId);
		
		createProductOutDto = buildCreateProductOutDto(id, name, description, quantity, 3.0,
		        discount, manufacturer, userId);
		
		createProductOutDto = buildCreateProductOutDto(id, name, description, quantity, price,
		        4.0, manufacturer, userId);
		
		createProductOutDto = buildCreateProductOutDto(id, name, description, quantity, price,
		        discount, "changed", userId);
		
		createProductOutDto = buildCreateProductOutDto(id, name, description, quantity, price,
		        discount, manufacturer, 3L);
	}

}
