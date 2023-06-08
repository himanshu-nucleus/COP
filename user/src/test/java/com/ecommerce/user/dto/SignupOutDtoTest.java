package com.ecommerce.user.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class SignupOutDtoTest {

    public SignupOutDto buildSignupOutDto(Long userId, String firstName, String gender, String lastName, String phone,
            String countryCode, String email, String addressLine1, String addressLine2, String city, String state,
            String country, String postalCode, String role) {
        SignupOutDto signupOutDtoTest = new SignupOutDto();
        signupOutDtoTest.setAddressLine1(addressLine1);
        signupOutDtoTest.setAddressLine2(addressLine2);
        signupOutDtoTest.setCity(city);
        signupOutDtoTest.setCountry(country);
        signupOutDtoTest.setCountryCode(countryCode);
        signupOutDtoTest.setEmail(email);
        signupOutDtoTest.setFirstName(firstName);
        signupOutDtoTest.setGender(gender);
        signupOutDtoTest.setLastName(lastName);
        signupOutDtoTest.setPhone(phone);
        signupOutDtoTest.setPostalCode(postalCode);
        signupOutDtoTest.setRole(role);
        signupOutDtoTest.setState(state);
        signupOutDtoTest.setUserId(userId);
        return signupOutDtoTest;
    }

    @Test
    public void getterSetterTest() {

        String firstName = "firstName";
        String gender = "male";
        String lastName = "lastName";
        String phone = "9084883372";
        String countryCode = "+91";
        String email = "hari@nucleusteq.com";
        String addressLine1 = "add 1";
        String addressLine2 = "add 2";
        String city = "city";
        String state = "state";
        String country = "country";
        String postalCode = "postal code";
        String role = "role";
        Long userId = 1L;

        SignupOutDto signupOutDto = new SignupOutDto();

        assertNull(signupOutDto.getUserId());
        signupOutDto.setUserId(userId);
        assertEquals(userId, signupOutDto.getUserId());

        assertNull(signupOutDto.getAddressLine1());
        signupOutDto.setAddressLine1(addressLine1);
        assertEquals(addressLine1, signupOutDto.getAddressLine1());

        assertNull(signupOutDto.getAddressLine2());
        signupOutDto.setAddressLine2(addressLine2);
        assertEquals(addressLine2, signupOutDto.getAddressLine2());

        assertNull(signupOutDto.getCity());
        signupOutDto.setCity(city);
        assertEquals(city, signupOutDto.getCity());

        assertNull(signupOutDto.getCountry());
        signupOutDto.setCountry(country);
        assertEquals(country, signupOutDto.getCountry());

        assertNull(signupOutDto.getCountryCode());
        signupOutDto.setCountryCode(countryCode);
        assertEquals(countryCode, signupOutDto.getCountryCode());

        assertNull(signupOutDto.getEmail());
        signupOutDto.setEmail(email);
        assertEquals(email, signupOutDto.getEmail());

        assertNull(signupOutDto.getFirstName());
        signupOutDto.setFirstName(firstName);
        assertEquals(firstName, signupOutDto.getFirstName());

        assertNull(signupOutDto.getGender());
        signupOutDto.setGender(gender);
        assertEquals(gender, signupOutDto.getGender());

        assertNull(signupOutDto.getLastName());
        signupOutDto.setLastName(lastName);
        assertEquals(lastName, signupOutDto.getLastName());

        assertNull(signupOutDto.getPhone());
        signupOutDto.setPhone(phone);
        assertEquals(phone, signupOutDto.getPhone());

        assertNull(signupOutDto.getPostalCode());
        signupOutDto.setPostalCode(postalCode);
        assertEquals(postalCode, signupOutDto.getPostalCode());

        assertNull(signupOutDto.getRole());
        signupOutDto.setRole(role);
        assertEquals(role, signupOutDto.getRole());

        assertNull(signupOutDto.getState());
        signupOutDto.setState(state);
        assertEquals(state, signupOutDto.getState());

        System.out.println(signupOutDto);
    }

    @Test
    public void toStringTest() {

        String signupOutDtoTestString = "SignupOutDto(userId=1, firstName=firstName, gender=male, lastName=lastName, phone=9084883372, countryCode=+91, email=hari@nucleusteq.com, addressLine1=add 1, addressLine2=add 2, city=city, state=state, country=country, postalCode=postal code, role=role)";

        String firstName = "firstName";
        String gender = "male";
        String lastName = "lastName";
        String phone = "9084883372";
        String countryCode = "+91";
        String email = "hari@nucleusteq.com";
        String addressLine1 = "add 1";
        String addressLine2 = "add 2";
        String city = "city";
        String state = "state";
        String country = "country";
        String postalCode = "postal code";
        String role = "role";
        Long userId = 1L;

        SignupOutDto signupOutDtoTest = buildSignupOutDto(userId, firstName, gender, lastName, phone, countryCode,
                email, addressLine1, addressLine2, city, state, country, postalCode, role);
        assertEquals(signupOutDtoTestString, signupOutDtoTest.toString());

    }

    @Test
    public void equalsAndHashCodeTest() {
        String firstName = "firstName";
        String gender = "male";
        String lastName = "lastName";
        String phone = "9084883372";
        String countryCode = "+91";
        String email = "hari@nucleusteq.com";
        String addressLine1 = "add 1";
        String addressLine2 = "add 2";
        String city = "city";
        String state = "state";
        String country = "country";
        String postalCode = "postal code";
        String role = "role";
        Long userId = 1L;

        SignupOutDto signupOutDtoTest = buildSignupOutDto(userId, firstName, gender, lastName, phone, countryCode,
                email, addressLine1, addressLine2, city, state, country, postalCode, role);
        SignupOutDto signupOutDtoTest2 = buildSignupOutDto(userId, firstName, gender, lastName, phone, countryCode,
                email, addressLine1, addressLine2, city, state, country, postalCode, role);

        assertEquals(signupOutDtoTest, signupOutDtoTest);
        assertEquals(signupOutDtoTest.hashCode(), signupOutDtoTest.hashCode());

        assertNotEquals(signupOutDtoTest, new Object());
        assertNotEquals(signupOutDtoTest2, null);

        assertEquals(signupOutDtoTest2, signupOutDtoTest2);
        assertEquals(signupOutDtoTest2.hashCode(), signupOutDtoTest2.hashCode());

        signupOutDtoTest = buildSignupOutDto(2l, firstName, gender, lastName, phone, countryCode,
                email, addressLine1, addressLine2, city, state, country, postalCode, role);
        assertNotEquals(signupOutDtoTest2, signupOutDtoTest);
        assertNotEquals(signupOutDtoTest2.hashCode(), signupOutDtoTest.hashCode());

        signupOutDtoTest = buildSignupOutDto(userId, "changed", gender, lastName, phone, countryCode,
                email, addressLine1, addressLine2, city, state, country, postalCode, role);
        assertNotEquals(signupOutDtoTest2, signupOutDtoTest);
        assertNotEquals(signupOutDtoTest2.hashCode(), signupOutDtoTest.hashCode());
        
        signupOutDtoTest = buildSignupOutDto(userId, firstName, "changed", lastName, phone, countryCode,
                email, addressLine1, addressLine2, city, state, country, postalCode, role);
        assertNotEquals(signupOutDtoTest2, signupOutDtoTest);
        assertNotEquals(signupOutDtoTest2.hashCode(), signupOutDtoTest.hashCode());
        
        signupOutDtoTest = buildSignupOutDto(userId, firstName, gender, "changed", phone, countryCode,
                email, addressLine1, addressLine2, city, state, country, postalCode, role);
        assertNotEquals(signupOutDtoTest2, signupOutDtoTest);
        assertNotEquals(signupOutDtoTest2.hashCode(), signupOutDtoTest.hashCode());
        
        signupOutDtoTest = buildSignupOutDto(userId, firstName, gender, lastName, "changed", countryCode,
                email, addressLine1, addressLine2, city, state, country, postalCode, role);
        assertNotEquals(signupOutDtoTest2, signupOutDtoTest);
        assertNotEquals(signupOutDtoTest2.hashCode(), signupOutDtoTest.hashCode());
        
        signupOutDtoTest = buildSignupOutDto(userId, firstName, gender, lastName, phone, "changed",
                email, addressLine1, addressLine2, city, state, country, postalCode, role);
        assertNotEquals(signupOutDtoTest2, signupOutDtoTest);
        assertNotEquals(signupOutDtoTest2.hashCode(), signupOutDtoTest.hashCode());
        
        signupOutDtoTest = buildSignupOutDto(userId, firstName, gender, lastName, phone, countryCode,
                "changed", addressLine1, addressLine2, city, state, country, postalCode, role);
        assertNotEquals(signupOutDtoTest2, signupOutDtoTest);
        assertNotEquals(signupOutDtoTest2.hashCode(), signupOutDtoTest.hashCode());
        
        signupOutDtoTest = buildSignupOutDto(userId, firstName, gender, lastName, phone, countryCode,
                email, "changed", addressLine2, city, state, country, postalCode, role);
        assertNotEquals(signupOutDtoTest2, signupOutDtoTest);
        assertNotEquals(signupOutDtoTest2.hashCode(), signupOutDtoTest.hashCode());
        
        signupOutDtoTest = buildSignupOutDto(userId, firstName, gender, lastName, phone, countryCode,
                email, addressLine1, "changed", city, state, country, postalCode, role);
        assertNotEquals(signupOutDtoTest2, signupOutDtoTest);
        assertNotEquals(signupOutDtoTest2.hashCode(), signupOutDtoTest.hashCode());
        
        signupOutDtoTest = buildSignupOutDto(userId, firstName, gender, lastName, phone, countryCode,
                email, addressLine1, addressLine2, "changed", state, country, postalCode, role);
        assertNotEquals(signupOutDtoTest2, signupOutDtoTest);
        assertNotEquals(signupOutDtoTest2.hashCode(), signupOutDtoTest.hashCode());
        
        signupOutDtoTest = buildSignupOutDto(userId, firstName, gender, lastName, phone, countryCode,
                email, addressLine1, addressLine2, city, "changed", country, postalCode, role);
        assertNotEquals(signupOutDtoTest2, signupOutDtoTest);
        assertNotEquals(signupOutDtoTest2.hashCode(), signupOutDtoTest.hashCode());
        
        signupOutDtoTest = buildSignupOutDto(userId, firstName, gender, lastName, phone, countryCode,
                email, addressLine1, addressLine2, city, state, "changed", postalCode, role);
        assertNotEquals(signupOutDtoTest2, signupOutDtoTest);
        assertNotEquals(signupOutDtoTest2.hashCode(), signupOutDtoTest.hashCode());
        
        signupOutDtoTest = buildSignupOutDto(userId, firstName, gender, lastName, phone, countryCode,
                email, addressLine1, addressLine2, city, state, country, "changed", role);
        assertNotEquals(signupOutDtoTest2, signupOutDtoTest);
        assertNotEquals(signupOutDtoTest2.hashCode(), signupOutDtoTest.hashCode());

        signupOutDtoTest = buildSignupOutDto(userId, firstName, gender, lastName, phone, countryCode,
                email, addressLine1, addressLine2, city, state, country, postalCode, "changed");
        assertNotEquals(signupOutDtoTest2, signupOutDtoTest);
        assertNotEquals(signupOutDtoTest2.hashCode(), signupOutDtoTest.hashCode());

    }

}
