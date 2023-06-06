package com.ecommerce.cart.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class DeleteCartProductInDtoTest {

    public DeleteCartProductInDto buildDeleteCartProductInDto(String cartId, Long userId, String productId) {
        DeleteCartProductInDto deleteCartProductInDto = new DeleteCartProductInDto();
        deleteCartProductInDto.setProductId(productId);
        deleteCartProductInDto.setCartId(cartId);
        deleteCartProductInDto.setUserId(userId);
        return deleteCartProductInDto;
    }

    @Test
    public void getterSetterTest() {

        Long userId = 1L;
        String productId = "id1";
        String cartId = "id1";

        DeleteCartProductInDto deleteCartProductInDto = new DeleteCartProductInDto();

        assertNull(deleteCartProductInDto.getProductId());
        deleteCartProductInDto.setProductId(productId);
        assertEquals(productId, deleteCartProductInDto.getProductId());

        assertNull(deleteCartProductInDto.getCartId());
        deleteCartProductInDto.setCartId(cartId);
        assertEquals(cartId, deleteCartProductInDto.getCartId());

        assertNull(deleteCartProductInDto.getUserId());
        deleteCartProductInDto.setUserId(userId);
        assertEquals(userId, deleteCartProductInDto.getUserId());

        System.out.println(deleteCartProductInDto);
    }

    @Test
    public void toStringTest() {

        String deleteCartProductInDtoString = "DeleteCartProductInDto(cartId=id1, userId=1, productId=id1)";
        
        Long userId = 1L;
        String productId = "id1";
        String cartId = "id1";

        DeleteCartProductInDto deleteCartProductInDto = buildDeleteCartProductInDto(cartId, userId, productId) ;
        assertEquals(deleteCartProductInDtoString, deleteCartProductInDto.toString());

    }

    @Test
    public void equalsAndHashCodeTest() {

        
        Long userId = 1L;
        String productId = "id1";
        String cartId = "id1";

        DeleteCartProductInDto deleteCartProductInDto = buildDeleteCartProductInDto(cartId, userId, productId) ;
        DeleteCartProductInDto deleteCartProductInDto2  = buildDeleteCartProductInDto(cartId, userId, productId) ;
        
        assertEquals(deleteCartProductInDto, deleteCartProductInDto);
        assertEquals(deleteCartProductInDto.hashCode(), deleteCartProductInDto.hashCode());

        assertNotEquals(deleteCartProductInDto, new Object());
        assertNotEquals(deleteCartProductInDto2, null);

        assertEquals(deleteCartProductInDto2, deleteCartProductInDto2);
        assertEquals(deleteCartProductInDto2.hashCode(), deleteCartProductInDto2.hashCode());

        deleteCartProductInDto = buildDeleteCartProductInDto("changed", userId, productId) ;
        assertNotEquals(deleteCartProductInDto2, deleteCartProductInDto);
        assertNotEquals(deleteCartProductInDto2.hashCode(), deleteCartProductInDto.hashCode());

        deleteCartProductInDto = buildDeleteCartProductInDto(cartId, 2l, productId) ;
        assertNotEquals(deleteCartProductInDto2, deleteCartProductInDto);
        assertNotEquals(deleteCartProductInDto2.hashCode(), deleteCartProductInDto.hashCode());
        
        deleteCartProductInDto = buildDeleteCartProductInDto(cartId, userId, "changed") ;
        assertNotEquals(deleteCartProductInDto2, deleteCartProductInDto);
        assertNotEquals(deleteCartProductInDto2.hashCode(), deleteCartProductInDto.hashCode());

    }

}
