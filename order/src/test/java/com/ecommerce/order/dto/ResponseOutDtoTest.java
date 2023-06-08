package com.ecommerce.order.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class ResponseOutDtoTest {

    public ResponseOutDto buildResponseOutDto(String message) {
        ResponseOutDto responseOutDto = new ResponseOutDto();
        responseOutDto.setMessage(message);
        return responseOutDto;
    }

    @Test
    public void getterSetterTest() {

        String message = "message";

        ResponseOutDto responseOutDto = new ResponseOutDto();

        assertNull(responseOutDto.getMessage());
        responseOutDto.setMessage(message);
        assertEquals(message, responseOutDto.getMessage());

        System.out.println(responseOutDto);

    }

    @Test
    public void toStringTest() {

        String ResponseOutDtoString = "ResponseOutDto(message=message)";

        String message = "message";

        ResponseOutDto responseOutDto = buildResponseOutDto(message);

        assertEquals(ResponseOutDtoString, responseOutDto.toString());

    }

    @Test
    public void equalsAndHashCodeTest() {

        String message = "message";

        ResponseOutDto responseOutDto = buildResponseOutDto(message);

        ResponseOutDto responseOutDto2 = buildResponseOutDto(message);

        assertEquals(responseOutDto, responseOutDto);
        assertEquals(responseOutDto.hashCode(), responseOutDto.hashCode());

        assertNotEquals(responseOutDto, new Object());
        assertNotEquals(responseOutDto2, null);

        assertEquals(responseOutDto2, responseOutDto2);
        assertEquals(responseOutDto2.hashCode(), responseOutDto2.hashCode());

        responseOutDto = buildResponseOutDto("changed");
        assertNotEquals(responseOutDto2, responseOutDto);
        assertNotEquals(responseOutDto2.hashCode(), responseOutDto.hashCode());
    }

}
