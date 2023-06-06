package com.ecommerce.cart.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class CreateCartInDtoTest {

    public CreateCartInDto buildCreateCartInDto(Long userId, String productId, Integer quantity) {
        CreateCartInDto createCartInDto = new CreateCartInDto();
        createCartInDto.setProductId(productId);
        createCartInDto.setQuantity(quantity);
        createCartInDto.setUserId(userId);
        return createCartInDto;
    }

    @Test
    public void getterSetterTest() {

        Long userId = 1L;
        String productId = "id1";
        Integer quantity = 1;

        CreateCartInDto createCartInDto = new CreateCartInDto();

        assertNull(createCartInDto.getProductId());
        createCartInDto.setProductId(productId);
        assertEquals(productId, createCartInDto.getProductId());

        assertNull(createCartInDto.getQuantity());
        createCartInDto.setQuantity(quantity);
        assertEquals(quantity, createCartInDto.getQuantity());

        assertNull(createCartInDto.getUserId());
        createCartInDto.setUserId(userId);
        assertEquals(userId, createCartInDto.getUserId());

        System.out.println(createCartInDto);
    }

    @Test
    public void toStringTest() {

        String createCartInDtoString = "CreateCartInDto(userId=1, productId=id1, quantity=1)";
        Long userId = 1L;
        String productId = "id1";
        Integer quantity = 1;

        CreateCartInDto createCartInDto = buildCreateCartInDto(userId, productId, quantity);
        assertEquals(createCartInDtoString, createCartInDto.toString());

    }

    @Test
    public void equalsAndHashCodeTest() {

        Long userId = 1L;
        String productId = "id1";
        Integer quantity = 1;

        CreateCartInDto createCartInDto = buildCreateCartInDto(userId, productId, quantity);
        CreateCartInDto createCartInDto2 = buildCreateCartInDto(userId, productId, quantity);
        
        assertEquals(createCartInDto, createCartInDto);
        assertEquals(createCartInDto.hashCode(), createCartInDto.hashCode());

        assertNotEquals(createCartInDto, new Object());
        assertNotEquals(createCartInDto2, null);

        assertEquals(createCartInDto2, createCartInDto2);
        assertEquals(createCartInDto2.hashCode(), createCartInDto2.hashCode());

        createCartInDto = buildCreateCartInDto(2l, productId, quantity);
        assertNotEquals(createCartInDto2, createCartInDto);
        assertNotEquals(createCartInDto2.hashCode(), createCartInDto.hashCode());

        createCartInDto = buildCreateCartInDto(userId, "changed", quantity);
        assertNotEquals(createCartInDto2, createCartInDto);
        assertNotEquals(createCartInDto2.hashCode(), createCartInDto.hashCode());
        
        createCartInDto = buildCreateCartInDto(userId, productId, 3);
        assertNotEquals(createCartInDto2, createCartInDto);
        assertNotEquals(createCartInDto2.hashCode(), createCartInDto.hashCode());

    }

}
