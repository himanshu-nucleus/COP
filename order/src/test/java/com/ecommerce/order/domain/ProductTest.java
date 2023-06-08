package com.ecommerce.order.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class ProductTest {

	public Product buildProduct(String id, String name, String description, Integer quantity, double price,
	        double discount, String manufacturer) {
		Product productTest = new Product();
		productTest.setDescription(description);
		productTest.setDiscount(discount);
		productTest.setId(id);
		productTest.setManufacturer(manufacturer);
		productTest.setName(name);
		productTest.setPrice(price);
		productTest.setQuantity(quantity);
		return productTest;
	}

	@Test
	public void getterSetterTest() {

		String id = "id1";
		String name = "name";
		String description = "description";
		Integer quantity = 2;
		double price = 1.0;
		double discount = 1.0;
		String manufacturer = "manufacturer";

		Product product = new Product();

		assertNull(product.getDescription());
		product.setDescription(description);
		assertEquals(description, product.getDescription());

		assertEquals(0.0, product.getDiscount());
		product.setDiscount(discount);
		assertEquals(discount, product.getDiscount());

		assertNull(product.getId());
		product.setId(id);
		assertEquals(id, product.getId());

		assertNull(product.getManufacturer());
		product.setManufacturer(manufacturer);
		assertEquals(manufacturer, product.getManufacturer());

		assertNull(product.getName());
		product.setName(name);
		assertEquals(name, product.getName());

		assertEquals(0.0, product.getPrice());
		product.setPrice(price);
		assertEquals(price, product.getPrice());

		assertEquals(0, product.getQuantity());
		product.setQuantity(quantity);
		assertEquals(quantity, product.getQuantity());

		System.out.println(product);
	}

	@Test
	public void toStringTest() {

		String productTestString = "Product(id=id1, name=name, description=description, quantity=2, price=1.0, discount=1.0, manufacturer=manufacturer)";
		String id = "id1";
		String name = "name";
		String description = "description";
		Integer quantity = 2;
		double price = 1.0;
		double discount = 1.0;
		String manufacturer = "manufacturer";

		Product productTest = buildProduct(id, name, description, quantity, price, discount, manufacturer);

		assertEquals(productTestString, productTest.toString());

	}

	@Test
	public void equalsAndHashCodeTest() {

		String id = "id1";
		String name = "name";
		String description = "description";
		Integer quantity = 2;
		double price = 1.0;
		double discount = 1.0;
		String manufacturer = "manufacturer";

		Product productTest = buildProduct(id, name, description, quantity, price, discount, manufacturer);
		Product productTest2 = buildProduct(id, name, description, quantity, price, discount, manufacturer);

		assertEquals(productTest, productTest);
		assertEquals(productTest.hashCode(), productTest.hashCode());

		assertNotEquals(productTest, new Object());
		assertNotEquals(productTest2, null);

		assertEquals(productTest2, productTest2);
		assertEquals(productTest2.hashCode(), productTest2.hashCode());

		productTest = buildProduct("changed", name, description, quantity, price, discount, manufacturer);
		assertNotEquals(productTest2, productTest);
		assertNotEquals(productTest2.hashCode(), productTest.hashCode());
		
		productTest = buildProduct(id, "changed", description, quantity, price, discount, manufacturer);
        assertNotEquals(productTest2, productTest);
        assertNotEquals(productTest2.hashCode(), productTest.hashCode());
        
		productTest = buildProduct(id, name, "changed", quantity, price, discount, manufacturer);
        assertNotEquals(productTest2, productTest);
        assertNotEquals(productTest2.hashCode(), productTest.hashCode());
        
		productTest = buildProduct(id, name, description, 3, price, discount, manufacturer);
        assertNotEquals(productTest2, productTest);
        assertNotEquals(productTest2.hashCode(), productTest.hashCode());
       
		productTest = buildProduct(id, name, description, quantity, 3.0, discount, manufacturer);
        assertNotEquals(productTest2, productTest);
        assertNotEquals(productTest2.hashCode(), productTest.hashCode());
        
		productTest = buildProduct(id, name, description, quantity, price, 4.9, manufacturer);
        assertNotEquals(productTest2, productTest);
        assertNotEquals(productTest2.hashCode(), productTest.hashCode());
        
		productTest = buildProduct(id, name, description, quantity, price, discount, "changed");
        assertNotEquals(productTest2, productTest);
        assertNotEquals(productTest2.hashCode(), productTest.hashCode());
        
	}

}
