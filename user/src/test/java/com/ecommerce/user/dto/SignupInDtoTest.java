package com.ecommerce.user.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class SignupInDtoTest {

    public SignupInDto buildSignupInDto(String firstName, String gender, String lastName, String phone,
            String countryCode, String email, String addressLine1, String addressLine2, String city, String state,
            String country, String postalCode, String role, char[] password) {
        SignupInDto signupInDtoTest = new SignupInDto();
        signupInDtoTest.setAddressLine1(addressLine1);
        signupInDtoTest.setAddressLine2(addressLine2);
        signupInDtoTest.setCity(city);
        signupInDtoTest.setCountry(country);
        signupInDtoTest.setCountryCode(countryCode);
        signupInDtoTest.setEmail(email);
        signupInDtoTest.setFirstName(firstName);
        signupInDtoTest.setGender(gender);
        signupInDtoTest.setLastName(lastName);
        signupInDtoTest.setPhone(phone);
        signupInDtoTest.setPostalCode(postalCode);
        signupInDtoTest.setRole(role);
        signupInDtoTest.setState(state);
        signupInDtoTest.setPassword(password);
        return signupInDtoTest;
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
        char[] password = { 'N', 'c' };

        SignupInDto signupInDto = new SignupInDto();

        assertNull(signupInDto.getPassword());
        signupInDto.setPassword(password);
        assertEquals(password, signupInDto.getPassword());

        assertNull(signupInDto.getAddressLine1());
        signupInDto.setAddressLine1(addressLine1);
        assertEquals(addressLine1, signupInDto.getAddressLine1());

        assertNull(signupInDto.getAddressLine2());
        signupInDto.setAddressLine2(addressLine2);
        assertEquals(addressLine2, signupInDto.getAddressLine2());

        assertNull(signupInDto.getCity());
        signupInDto.setCity(city);
        assertEquals(city, signupInDto.getCity());

        assertNull(signupInDto.getCountry());
        signupInDto.setCountry(country);
        assertEquals(country, signupInDto.getCountry());

        assertNull(signupInDto.getCountryCode());
        signupInDto.setCountryCode(countryCode);
        assertEquals(countryCode, signupInDto.getCountryCode());

        assertNull(signupInDto.getEmail());
        signupInDto.setEmail(email);
        assertEquals(email, signupInDto.getEmail());

        assertNull(signupInDto.getFirstName());
        signupInDto.setFirstName(firstName);
        assertEquals(firstName, signupInDto.getFirstName());

        assertNull(signupInDto.getGender());
        signupInDto.setGender(gender);
        assertEquals(gender, signupInDto.getGender());

        assertNull(signupInDto.getLastName());
        signupInDto.setLastName(lastName);
        assertEquals(lastName, signupInDto.getLastName());

        assertNull(signupInDto.getPhone());
        signupInDto.setPhone(phone);
        assertEquals(phone, signupInDto.getPhone());

        assertNull(signupInDto.getPostalCode());
        signupInDto.setPostalCode(postalCode);
        assertEquals(postalCode, signupInDto.getPostalCode());

        assertNull(signupInDto.getRole());
        signupInDto.setRole(role);
        assertEquals(role, signupInDto.getRole());

        assertNull(signupInDto.getState());
        signupInDto.setState(state);
        assertEquals(state, signupInDto.getState());

        System.out.println(signupInDto);
    }

    @Test
    public void toStringTest() {

        String signupInDtoTestString = "SignupInDto(firstName=firstName, gender=male, lastName=lastName, phone=9084883372, countryCode=+91, email=hari@nucleusteq.com, addressLine1=add 1, addressLine2=add 2, city=city, state=state, country=country, postalCode=postal code, role=role, password=[N, c])";
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
        char[] password = { 'N', 'c' };

        SignupInDto signupInDtoTest = buildSignupInDto(firstName, gender, lastName, phone, countryCode, email,
                addressLine1, addressLine2, city, state, country, postalCode, role, password);
        assertEquals(signupInDtoTestString, signupInDtoTest.toString());

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
        char[] password = { 'N', 'c' };

        SignupInDto signupInDtoTest = buildSignupInDto(firstName, gender, lastName, phone, countryCode, email,
                addressLine1, addressLine2, city, state, country, postalCode, role, password);
        SignupInDto signupInDtoTest2 = buildSignupInDto(firstName, gender, lastName, phone, countryCode, email,
                addressLine1, addressLine2, city, state, country, postalCode, role, password);

        assertEquals(signupInDtoTest, signupInDtoTest);
        assertEquals(signupInDtoTest.hashCode(), signupInDtoTest.hashCode());

        assertNotEquals(signupInDtoTest, new Object());
        assertNotEquals(signupInDtoTest2, null);

        assertEquals(signupInDtoTest2, signupInDtoTest2);
        assertEquals(signupInDtoTest2.hashCode(), signupInDtoTest2.hashCode());

        signupInDtoTest = buildSignupInDto("changed", gender, lastName, phone, countryCode, email, addressLine1,
                addressLine2, city, state, country, postalCode, role, password);
        assertNotEquals(signupInDtoTest2, signupInDtoTest);
        assertNotEquals(signupInDtoTest2.hashCode(), signupInDtoTest.hashCode());

        signupInDtoTest = buildSignupInDto(firstName, "changed", lastName, phone, countryCode, email, addressLine1,
                addressLine2, city, state, country, postalCode, role, password);
        assertNotEquals(signupInDtoTest2, signupInDtoTest);
        assertNotEquals(signupInDtoTest2.hashCode(), signupInDtoTest.hashCode());

        signupInDtoTest = buildSignupInDto(firstName, gender, "changed", phone, countryCode, email, addressLine1,
                addressLine2, city, state, country, postalCode, role, password);
        assertNotEquals(signupInDtoTest2, signupInDtoTest);
        assertNotEquals(signupInDtoTest2.hashCode(), signupInDtoTest.hashCode());

        signupInDtoTest = buildSignupInDto(firstName, gender, lastName, "changed", countryCode, email, addressLine1,
                addressLine2, city, state, country, postalCode, role, password);
        assertNotEquals(signupInDtoTest2, signupInDtoTest);
        assertNotEquals(signupInDtoTest2.hashCode(), signupInDtoTest.hashCode());

        signupInDtoTest = buildSignupInDto(firstName, gender, lastName, phone, "changed", email, addressLine1,
                addressLine2, city, state, country, postalCode, role, password);
        assertNotEquals(signupInDtoTest2, signupInDtoTest);
        assertNotEquals(signupInDtoTest2.hashCode(), signupInDtoTest.hashCode());

        signupInDtoTest = buildSignupInDto(firstName, gender, lastName, phone, countryCode, "changed", addressLine1,
                addressLine2, city, state, country, postalCode, role, password);
        assertNotEquals(signupInDtoTest2, signupInDtoTest);
        assertNotEquals(signupInDtoTest2.hashCode(), signupInDtoTest.hashCode());

        signupInDtoTest = buildSignupInDto(firstName, gender, lastName, phone, countryCode, email, "changed",
                addressLine2, city, state, country, postalCode, role, password);
        assertNotEquals(signupInDtoTest2, signupInDtoTest);
        assertNotEquals(signupInDtoTest2.hashCode(), signupInDtoTest.hashCode());

        signupInDtoTest = buildSignupInDto(firstName, gender, lastName, phone, countryCode, email, addressLine1,
                "changed", city, state, country, postalCode, role, password);
        assertNotEquals(signupInDtoTest2, signupInDtoTest);
        assertNotEquals(signupInDtoTest2.hashCode(), signupInDtoTest.hashCode());

        signupInDtoTest = buildSignupInDto(firstName, gender, lastName, phone, countryCode, email, addressLine1,
                addressLine2, "changed", state, country, postalCode, role, password);
        assertNotEquals(signupInDtoTest2, signupInDtoTest);
        assertNotEquals(signupInDtoTest2.hashCode(), signupInDtoTest.hashCode());

        signupInDtoTest = buildSignupInDto(firstName, gender, lastName, phone, countryCode, email, addressLine1,
                addressLine2, city, "changed", country, postalCode, role, password);
        assertNotEquals(signupInDtoTest2, signupInDtoTest);
        assertNotEquals(signupInDtoTest2.hashCode(), signupInDtoTest.hashCode());

        signupInDtoTest = buildSignupInDto(firstName, gender, lastName, phone, countryCode, email, addressLine1,
                addressLine2, city, state, "changed", postalCode, role, password);
        assertNotEquals(signupInDtoTest2, signupInDtoTest);
        assertNotEquals(signupInDtoTest2.hashCode(), signupInDtoTest.hashCode());

        signupInDtoTest = buildSignupInDto(firstName, gender, lastName, phone, countryCode, email, addressLine1,
                addressLine2, city, state, country, "changed", role, password);
        assertNotEquals(signupInDtoTest2, signupInDtoTest);
        assertNotEquals(signupInDtoTest2.hashCode(), signupInDtoTest.hashCode());

        signupInDtoTest = buildSignupInDto(firstName, gender, lastName, phone, countryCode, email, addressLine1,
                addressLine2, city, state, country, postalCode, "changed", password);
        assertNotEquals(signupInDtoTest2, signupInDtoTest);
        assertNotEquals(signupInDtoTest2.hashCode(), signupInDtoTest.hashCode());

        char[] newPass = { 'v' };
        signupInDtoTest = buildSignupInDto(firstName, gender, lastName, phone, countryCode, email, addressLine1,
                addressLine2, city, state, country, postalCode, role, newPass);
        assertNotEquals(signupInDtoTest2, signupInDtoTest);
        assertNotEquals(signupInDtoTest2.hashCode(), signupInDtoTest.hashCode());

    }

}
