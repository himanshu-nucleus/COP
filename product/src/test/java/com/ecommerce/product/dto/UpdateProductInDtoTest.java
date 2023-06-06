package com.ecommerce.product.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class UpdateProductInDtoTest {

    public UpdateProductInDto buildUpdateProductInDto(String name, String description, Integer quantity, double price,
            double discount, String manufacturer) {
        UpdateProductInDto updateProductInDto = new UpdateProductInDto();
        updateProductInDto.setDescription(description);
        updateProductInDto.setDiscount(discount);
        updateProductInDto.setManufacturer(manufacturer);
        updateProductInDto.setName(name);
        updateProductInDto.setPrice(price);
        updateProductInDto.setQuantity(quantity);
        return updateProductInDto;
    }

    @Test
    public void getterSetterTest() {

        UpdateProductInDto updateProductInDto = new UpdateProductInDto();

        String name = "name";
        String description = "description";
        Integer quantity = 5;
        double price = 1.0;
        double discount = 1.0;
        String manufacturer = "manufacturer";

        assertNull(updateProductInDto.getDescription());
        updateProductInDto.setDescription(description);
        assertEquals(description, updateProductInDto.getDescription());

        assertEquals(0.0, updateProductInDto.getDiscount());
        updateProductInDto.setDiscount(discount);
        assertEquals(discount, updateProductInDto.getDiscount());

        assertNull(updateProductInDto.getManufacturer());
        updateProductInDto.setManufacturer(manufacturer);
        assertEquals(manufacturer, updateProductInDto.getManufacturer());

        assertNull(updateProductInDto.getName());
        updateProductInDto.setName(name);
        assertEquals(name, updateProductInDto.getName());

        assertEquals(0.0, updateProductInDto.getPrice());
        updateProductInDto.setPrice(price);
        assertEquals(price, updateProductInDto.getPrice());

        assertNull(updateProductInDto.getQuantity());
        updateProductInDto.setQuantity(quantity);
        assertEquals(quantity, updateProductInDto.getQuantity());

        System.out.println(updateProductInDto);

    }

    @Test
    public void toStringTest() {

        String updateProductInDtoString = "UpdateProductInDto(name=name, description=description, quantity=5, price=1.0, discount=1.0, manufacturer=manufacturer)";

        String name = "name";
        String description = "description";
        Integer quantity = 5;
        double price = 1.0;
        double discount = 1.0;
        String manufacturer = "manufacturer";

        UpdateProductInDto updateProductInDto = buildUpdateProductInDto(name, description, quantity, price, discount,
                manufacturer);

        assertEquals(updateProductInDtoString, updateProductInDto.toString());

    }

    @Test
    public void equalsAndHashCodeTest() {

        String name = "name";
        String description = "description";
        Integer quantity = 5;
        double price = 1.0;
        double discount = 1.0;
        String manufacturer = "manufacturer";

        UpdateProductInDto updateProductInDto = buildUpdateProductInDto(name, description, quantity, price, discount,
                manufacturer);

        UpdateProductInDto updateProductInDto2 = buildUpdateProductInDto(name, description, quantity, price, discount,
                manufacturer);

        assertEquals(updateProductInDto, updateProductInDto);
        assertEquals(updateProductInDto.hashCode(), updateProductInDto.hashCode());

        assertNotEquals(updateProductInDto, new Object());
        assertNotEquals(updateProductInDto2, null);

        assertEquals(updateProductInDto2, updateProductInDto2);
        assertEquals(updateProductInDto2.hashCode(), updateProductInDto2.hashCode());

        updateProductInDto = buildUpdateProductInDto("changed", description, quantity, price, discount, manufacturer);
        assertNotEquals(updateProductInDto, updateProductInDto2);
        assertNotEquals(updateProductInDto2, updateProductInDto);
        assertNotEquals(updateProductInDto2.hashCode(), updateProductInDto.hashCode());

        updateProductInDto = buildUpdateProductInDto(name, "changed", quantity, price, discount, manufacturer);
        assertNotEquals(updateProductInDto, updateProductInDto2);
        assertNotEquals(updateProductInDto2, updateProductInDto);
        assertNotEquals(updateProductInDto2.hashCode(), updateProductInDto.hashCode());

        updateProductInDto = buildUpdateProductInDto(name, description, 2, price, discount, manufacturer);
        assertNotEquals(updateProductInDto, updateProductInDto2);
        assertNotEquals(updateProductInDto2, updateProductInDto);
        assertNotEquals(updateProductInDto2.hashCode(), updateProductInDto.hashCode());

        updateProductInDto = buildUpdateProductInDto(name, description, quantity, 2.0, discount, manufacturer);
        assertNotEquals(updateProductInDto, updateProductInDto2);
        assertNotEquals(updateProductInDto2, updateProductInDto);
        assertNotEquals(updateProductInDto2.hashCode(), updateProductInDto.hashCode());

        updateProductInDto = buildUpdateProductInDto(name, description, quantity, price, 2.9, manufacturer);
        assertNotEquals(updateProductInDto, updateProductInDto2);
        assertNotEquals(updateProductInDto2, updateProductInDto);
        assertNotEquals(updateProductInDto2.hashCode(), updateProductInDto.hashCode());

        updateProductInDto = buildUpdateProductInDto(name, description, quantity, price, discount, "changed");
        assertNotEquals(updateProductInDto, updateProductInDto2);
        assertNotEquals(updateProductInDto2, updateProductInDto);
        assertNotEquals(updateProductInDto2.hashCode(), updateProductInDto.hashCode());

    }

}
