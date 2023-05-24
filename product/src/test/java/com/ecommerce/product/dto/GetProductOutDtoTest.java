package com.ecommerce.product.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class GetProductOutDtoTest {

	public GetProductOutDto buildGetProductOutDto(String id, String name, String description, Integer quantity,
	        double price, double discount, String manufacturer) {
		GetProductOutDto getProductOutDto = new GetProductOutDto();
		getProductOutDto.setDescription(description);
		getProductOutDto.setDiscount(discount);
		getProductOutDto.setId(id);
		getProductOutDto.setManufacturer(manufacturer);
		getProductOutDto.setName(name);
		getProductOutDto.setPrice(price);
		getProductOutDto.setQuantity(quantity);
		return getProductOutDto;
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

		GetProductOutDto getProductOutDto = new GetProductOutDto();

		assertNull(getProductOutDto.getDescription());
		getProductOutDto.setDescription(description);
		assertEquals(description, getProductOutDto.getDescription());

		assertEquals(0.0, getProductOutDto.getDiscount());
		getProductOutDto.setDiscount(discount);
		assertEquals(discount, getProductOutDto.getDiscount());

		assertNull(getProductOutDto.getId());
		getProductOutDto.setId(id);
		assertEquals(id, getProductOutDto.getId());

		assertNull(getProductOutDto.getManufacturer());
		getProductOutDto.setManufacturer(manufacturer);
		assertEquals(manufacturer, getProductOutDto.getManufacturer());

		assertNull(getProductOutDto.getName());
		getProductOutDto.setName(name);
		assertEquals(name, getProductOutDto.getName());

		assertEquals(0.0, getProductOutDto.getPrice());
		getProductOutDto.setPrice(price);
		assertEquals(price, getProductOutDto.getPrice());

		assertNull(getProductOutDto.getQuantity());
		getProductOutDto.setQuantity(quantity);
		assertEquals(quantity, getProductOutDto.getQuantity());

		System.out.println(getProductOutDto);

	}

	@Test
	public void toStringTest() {

		String GetProductOutDtoString = "GetProductOutDto(id=id1, name=name, description=description, quantity=1, price=1.0, discount=1.0, manufacturer=manufacturer)";

		String id = "id1";
		String name = "name";
		String description = "description";
		Integer quantity = 1;
		double price = 1.0;
		double discount = 1.0;
		String manufacturer = "manufacturer";

		GetProductOutDto getProductOutDto = buildGetProductOutDto(id, name, description, quantity, price, discount,
		        manufacturer);

		assertEquals(GetProductOutDtoString, getProductOutDto.toString());

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

		GetProductOutDto getProductOutDto = buildGetProductOutDto(id, name, description, quantity, price, discount,
		        manufacturer);

		GetProductOutDto getProductOutDto2 = buildGetProductOutDto(id, name, description, quantity, price, discount,
		        manufacturer);

		assertEquals(getProductOutDto, getProductOutDto);
		assertEquals(getProductOutDto.hashCode(), getProductOutDto.hashCode());

		assertNotEquals(getProductOutDto, new Object());
		assertNotEquals(getProductOutDto2, null);

		assertEquals(getProductOutDto2, getProductOutDto2);
		assertEquals(getProductOutDto2.hashCode(), getProductOutDto2.hashCode());

		getProductOutDto = buildGetProductOutDto("2id", name, description, quantity, price, discount, manufacturer);

		getProductOutDto = buildGetProductOutDto(id, "changed", description, quantity, price, discount, manufacturer);

		getProductOutDto = buildGetProductOutDto(id, name, "changed", quantity, price, discount, manufacturer);

		getProductOutDto = buildGetProductOutDto(id, name, description, 2, price, discount, manufacturer);

		getProductOutDto = buildGetProductOutDto(id, name, description, quantity, 3.0, discount, manufacturer);

		getProductOutDto = buildGetProductOutDto(id, name, description, quantity, price, 4.0, manufacturer);

		getProductOutDto = buildGetProductOutDto(id, name, description, quantity, price, discount, "changed");

		getProductOutDto = buildGetProductOutDto(id, name, description, quantity, price, discount, manufacturer);
	}

}
