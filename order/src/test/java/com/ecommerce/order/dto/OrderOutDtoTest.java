package com.ecommerce.order.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.Instant;

import org.junit.jupiter.api.Test;

public class OrderOutDtoTest {

    Instant instant = Instant.now();

    public OrderOutDto buildOrderOutDto(String id, Instant createDt, double totalPrice, String status, String cardNo) {
        OrderOutDto orderOutDtoTest = new OrderOutDto();
        orderOutDtoTest.setCardNo(cardNo);
        orderOutDtoTest.setCreateDt(createDt);
        orderOutDtoTest.setId(id);
        orderOutDtoTest.setStatus(status);
        orderOutDtoTest.setTotalPrice(totalPrice);
        return orderOutDtoTest;
    }

    @Test
    public void getterSetterTest() {

        String id = "id1";
        double totalPrice = 1.0;
        String status = "placed";
        String cardNo = "cardNo";
        Instant createDt = instant;

        OrderOutDto orderOutDto = new OrderOutDto();

        assertNull(orderOutDto.getId());
        orderOutDto.setId(id);
        assertEquals(id, orderOutDto.getId());

        assertNull(orderOutDto.getCardNo());
        orderOutDto.setCardNo(cardNo);
        assertEquals(cardNo, orderOutDto.getCardNo());

        assertNull(orderOutDto.getCreateDt());
        orderOutDto.setCreateDt(createDt);
        assertEquals(createDt, orderOutDto.getCreateDt());

        assertNull(orderOutDto.getStatus());
        orderOutDto.setStatus(status);
        assertEquals(status, orderOutDto.getStatus());

        assertEquals(0, orderOutDto.getTotalPrice());
        orderOutDto.setTotalPrice(totalPrice);
        assertEquals(totalPrice, orderOutDto.getTotalPrice());

        System.out.println(orderOutDto);
    }

    @Test
    public void toStringTest() {

        String orderOutDtoTestString = "OrderOutDto(id=id1, createDt=2023-06-08T12:29:55.137796300Z, totalPrice=1.0, status=placed, cardNo=cardNo)";
        
        String id = "id1";
        double totalPrice = 1.0;
        String status = "placed";
        String cardNo = "cardNo";
        Instant createDt = Instant.parse("2023-06-08T12:29:55.137796300Z");

        OrderOutDto orderOutDtoTest = buildOrderOutDto(id, createDt, totalPrice, status, cardNo);
        assertEquals(orderOutDtoTestString, orderOutDtoTest.toString());

    }

    @Test
    public void equalsAndHashCodeTest() {
        String id = "id1";
        double totalPrice = 1.0;
        String status = "placed";
        String cardNo = "cardNo";
        Instant createDt = instant;

        OrderOutDto orderOutDtoTest = buildOrderOutDto(id, createDt, totalPrice, status, cardNo);
        OrderOutDto orderOutDtoTest2 = buildOrderOutDto(id, createDt, totalPrice, status, cardNo);

        assertEquals(orderOutDtoTest, orderOutDtoTest);
        assertEquals(orderOutDtoTest.hashCode(), orderOutDtoTest.hashCode());

        assertNotEquals(orderOutDtoTest, new Object());
        assertNotEquals(orderOutDtoTest2, null);

        assertEquals(orderOutDtoTest2, orderOutDtoTest2);
        assertEquals(orderOutDtoTest2.hashCode(), orderOutDtoTest2.hashCode());

        orderOutDtoTest = buildOrderOutDto("changed", createDt, totalPrice, status, cardNo);
        assertNotEquals(orderOutDtoTest2, orderOutDtoTest);
        assertNotEquals(orderOutDtoTest2.hashCode(), orderOutDtoTest.hashCode());

        orderOutDtoTest = buildOrderOutDto(id, createDt, 53.0, status, cardNo);
        assertNotEquals(orderOutDtoTest2, orderOutDtoTest);
        assertNotEquals(orderOutDtoTest2.hashCode(), orderOutDtoTest.hashCode());
        
        orderOutDtoTest = buildOrderOutDto(id, createDt, totalPrice, "changed", cardNo);
        assertNotEquals(orderOutDtoTest2, orderOutDtoTest);
        assertNotEquals(orderOutDtoTest2.hashCode(), orderOutDtoTest.hashCode());
        
        orderOutDtoTest = buildOrderOutDto(id, createDt, totalPrice, status, "changed");
        assertNotEquals(orderOutDtoTest2, orderOutDtoTest);
        assertNotEquals(orderOutDtoTest2.hashCode(), orderOutDtoTest.hashCode());
        
        Instant ins = Instant.MAX;
        orderOutDtoTest = buildOrderOutDto(id, ins, totalPrice, status, cardNo);
        assertNotEquals(orderOutDtoTest2, orderOutDtoTest);
        assertNotEquals(orderOutDtoTest2.hashCode(), orderOutDtoTest.hashCode());

    }

}
