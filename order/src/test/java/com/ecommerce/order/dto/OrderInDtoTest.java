package com.ecommerce.order.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class OrderInDtoTest {

    public OrderInDto buildOrderInDto(Long userId, String cartId) {
        OrderInDto orderInDtoTest = new OrderInDto();
        orderInDtoTest.setCartId(cartId);
        orderInDtoTest.setUserId(userId);
        return orderInDtoTest;
    }

    @Test
    public void getterSetterTest() {

        Long userId = 1l;
        String cartId = "cartId";

        OrderInDto orderInDto = new OrderInDto();

        assertNull(orderInDto.getCartId());
        orderInDto.setCartId(cartId);
        assertEquals(cartId, orderInDto.getCartId());

        assertNull(orderInDto.getUserId());
        orderInDto.setUserId(userId);
        assertEquals(userId, orderInDto.getUserId());

        System.out.println(orderInDto);
    }

    @Test
    public void toStringTest() {

        String cartTestString = "OrderInDto(userId=1, cartId=cartId)";
        

        Long userId = 1l;
        String cartId = "cartId";

        OrderInDto orderInDtoTest = buildOrderInDto(userId, cartId);
        assertEquals(cartTestString, orderInDtoTest.toString());

    }

    @Test
    public void equalsAndHashCodeTest() {


        Long userId = 1l;
        String cartId = "cartId";

        OrderInDto orderInDtoTest = buildOrderInDto(userId, cartId);
        OrderInDto orderInDtoTest2 = buildOrderInDto(userId, cartId);

        assertEquals(orderInDtoTest, orderInDtoTest);
        assertEquals(orderInDtoTest.hashCode(), orderInDtoTest.hashCode());

        assertNotEquals(orderInDtoTest, new Object());
        assertNotEquals(orderInDtoTest2, null);

        assertEquals(orderInDtoTest2, orderInDtoTest2);
        assertEquals(orderInDtoTest2.hashCode(), orderInDtoTest2.hashCode());

        orderInDtoTest = buildOrderInDto(3l, cartId);
        assertNotEquals(orderInDtoTest2, orderInDtoTest);
        assertNotEquals(orderInDtoTest2.hashCode(), orderInDtoTest.hashCode());

        orderInDtoTest = buildOrderInDto(userId, "huntId");
        assertNotEquals(orderInDtoTest2, orderInDtoTest);
        assertNotEquals(orderInDtoTest2.hashCode(), orderInDtoTest.hashCode());

    }

}
