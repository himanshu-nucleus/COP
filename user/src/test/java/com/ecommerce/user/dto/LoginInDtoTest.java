package com.ecommerce.user.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class LoginInDtoTest {

    public LoginInDto buildLogin(String email, char[] password) {
        LoginInDto loginInDto = new LoginInDto();
        loginInDto.setEmail(email);
        loginInDto.setPassword(password);
        return loginInDto;
    }

    @Test
    public void getterSetterTest() {

        String email = "hari@nucleusteq.com";
        char[] password = { 'N', 'c' };

        LoginInDto loginInDto = new LoginInDto();

        assertNull(loginInDto.getEmail());
        loginInDto.setEmail(email);
        assertEquals(email, loginInDto.getEmail());

        assertNull(loginInDto.getPassword());
        loginInDto.setPassword(password);
        assertEquals(password, loginInDto.getPassword());

        System.out.println(loginInDto);
    }

    @Test
    public void toStringTest() {

        String loginInDtoString = "LoginInDto(email=hari@nucleusteq.com, password=[N, c])";
        String email = "hari@nucleusteq.com";
        char[] password = { 'N', 'c' };

        LoginInDto loginInDto = buildLogin(email, password);

        assertEquals(loginInDtoString, loginInDto.toString());

    }

    @Test
    public void equalsAndHashCodeTest() {

        String email = "hari@nucleusteq.com";
        char[] password = { 'N', 'c' };

        LoginInDto loginInDto = buildLogin(email, password);
        LoginInDto loginInDto2 = buildLogin(email, password);

        assertEquals(loginInDto, loginInDto);
        assertEquals(loginInDto.hashCode(), loginInDto.hashCode());

        assertNotEquals(loginInDto, new Object());
        assertNotEquals(loginInDto2, null);

        assertEquals(loginInDto2, loginInDto2);
        assertEquals(loginInDto2.hashCode(), loginInDto2.hashCode());

        loginInDto2 = buildLogin("changed", password);
        assertNotEquals(loginInDto2, loginInDto);
        assertNotEquals(loginInDto2.hashCode(), loginInDto.hashCode());

        char[] changedPass = { 'N', 'c', 'd'};
        loginInDto2 = buildLogin(email, changedPass);
        assertNotEquals(loginInDto2, loginInDto);
        assertNotEquals(loginInDto2.hashCode(), loginInDto.hashCode());

    }

}
