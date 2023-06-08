package com.ecommerce.user.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class UserOutDtoTest {

    public UserOutDto buildUserOutDto(Long userId, String firstName, String gender, String lastName, String phone,
            String countryCode, String email, String addressLine1, String addressLine2, String city, String state,
            String country, String postalCode, String role) {

        UserOutDto userOutDtoTest = new UserOutDto();
        userOutDtoTest.setAddressLine1(addressLine1);
        userOutDtoTest.setAddressLine2(addressLine2);
        userOutDtoTest.setCity(city);
        userOutDtoTest.setCountry(country);
        userOutDtoTest.setCountryCode(countryCode);
        userOutDtoTest.setEmail(email);
        userOutDtoTest.setFirstName(firstName);
        userOutDtoTest.setGender(gender);
        userOutDtoTest.setLastName(lastName);
        userOutDtoTest.setPhone(phone);
        userOutDtoTest.setPostalCode(postalCode);
        userOutDtoTest.setRole(role);
        userOutDtoTest.setState(state);
        userOutDtoTest.setUserId(userId);
        return userOutDtoTest;
    }

    @Test
    public void getterSetterTest() {

        Long userId = 1L;
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

        UserOutDto userOutDto = new UserOutDto();

        assertNull(userOutDto.getUserId());
        userOutDto.setUserId(userId);
        assertEquals(userId, userOutDto.getUserId());

        assertNull(userOutDto.getAddressLine1());
        userOutDto.setAddressLine1(addressLine1);
        assertEquals(addressLine1, userOutDto.getAddressLine1());

        assertNull(userOutDto.getAddressLine2());
        userOutDto.setAddressLine2(addressLine2);
        assertEquals(addressLine2, userOutDto.getAddressLine2());

        assertNull(userOutDto.getCity());
        userOutDto.setCity(city);
        assertEquals(city, userOutDto.getCity());

        assertNull(userOutDto.getCountry());
        userOutDto.setCountry(country);
        assertEquals(country, userOutDto.getCountry());

        assertNull(userOutDto.getCountryCode());
        userOutDto.setCountryCode(countryCode);
        assertEquals(countryCode, userOutDto.getCountryCode());

        assertNull(userOutDto.getEmail());
        userOutDto.setEmail(email);
        assertEquals(email, userOutDto.getEmail());

        assertNull(userOutDto.getFirstName());
        userOutDto.setFirstName(firstName);
        assertEquals(firstName, userOutDto.getFirstName());

        assertNull(userOutDto.getGender());
        userOutDto.setGender(gender);
        assertEquals(gender, userOutDto.getGender());

        assertNull(userOutDto.getLastName());
        userOutDto.setLastName(lastName);
        assertEquals(lastName, userOutDto.getLastName());

        assertNull(userOutDto.getPhone());
        userOutDto.setPhone(phone);
        assertEquals(phone, userOutDto.getPhone());

        assertNull(userOutDto.getPostalCode());
        userOutDto.setPostalCode(postalCode);
        assertEquals(postalCode, userOutDto.getPostalCode());

        assertNull(userOutDto.getRole());
        userOutDto.setRole(role);
        assertEquals(role, userOutDto.getRole());

        assertNull(userOutDto.getState());
        userOutDto.setState(state);
        assertEquals(state, userOutDto.getState());

        System.out.println(userOutDto);
    }

    @Test
    public void toStringTest() {

        String userOutDtoTestString = "UserOutDto(userId=1, firstName=firstName, gender=male, lastName=lastName, phone=9084883372, countryCode=+91, email=hari@nucleusteq.com, addressLine1=add 1, addressLine2=add 2, city=city, state=state, country=country, postalCode=postal code, role=role)";

        Long userId = 1L;
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

        UserOutDto userOutDtoTest = buildUserOutDto(userId, firstName, gender, lastName, phone, countryCode, email,
                addressLine1, addressLine2, city, state, country, postalCode, role);
        assertEquals(userOutDtoTestString, userOutDtoTest.toString());

    }

    @Test
    public void equalsAndHashCodeTest() {

        Long userId = 1L;
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

        UserOutDto userOutDtoTest = buildUserOutDto(userId, firstName, gender, lastName, phone, countryCode, email,
                addressLine1, addressLine2, city, state, country, postalCode, role);
        UserOutDto userOutDtoTest2 = buildUserOutDto(userId, firstName, gender, lastName, phone, countryCode, email,
                addressLine1, addressLine2, city, state, country, postalCode, role);

        assertEquals(userOutDtoTest, userOutDtoTest);
        assertEquals(userOutDtoTest.hashCode(), userOutDtoTest.hashCode());

        assertNotEquals(userOutDtoTest, new Object());
        assertNotEquals(userOutDtoTest2, null);

        assertEquals(userOutDtoTest2, userOutDtoTest2);
        assertEquals(userOutDtoTest2.hashCode(), userOutDtoTest2.hashCode());

        userOutDtoTest = buildUserOutDto(3l, firstName, gender, lastName, phone, countryCode, email,
                addressLine1, addressLine2, city, state, country, postalCode, role);
        assertNotEquals(userOutDtoTest2, userOutDtoTest);
        assertNotEquals(userOutDtoTest2.hashCode(), userOutDtoTest.hashCode());

        userOutDtoTest = buildUserOutDto(userId, "changed", gender, lastName, phone, countryCode, email, addressLine1,
                addressLine2, city, state, country, postalCode, role);
        assertNotEquals(userOutDtoTest2, userOutDtoTest);
        assertNotEquals(userOutDtoTest2.hashCode(), userOutDtoTest.hashCode());

        userOutDtoTest = buildUserOutDto(userId, firstName, "changed", lastName, phone, countryCode, email,
                addressLine1, addressLine2, city, state, country, postalCode, role);
        assertNotEquals(userOutDtoTest2, userOutDtoTest);
        assertNotEquals(userOutDtoTest2.hashCode(), userOutDtoTest.hashCode());

        userOutDtoTest = buildUserOutDto(userId, firstName, gender, "changed", phone, countryCode, email, addressLine1,
                addressLine2, city, state, country, postalCode, role);
        assertNotEquals(userOutDtoTest2, userOutDtoTest);
        assertNotEquals(userOutDtoTest2.hashCode(), userOutDtoTest.hashCode());

        userOutDtoTest = buildUserOutDto(userId, firstName, gender, lastName, "changed", countryCode, email,
                addressLine1, addressLine2, city, state, country, postalCode, role);
        assertNotEquals(userOutDtoTest2, userOutDtoTest);
        assertNotEquals(userOutDtoTest2.hashCode(), userOutDtoTest.hashCode());

        userOutDtoTest = buildUserOutDto(userId, firstName, gender, lastName, phone, "changed", email, addressLine1,
                addressLine2, city, state, country, postalCode, role);
        assertNotEquals(userOutDtoTest2, userOutDtoTest);
        assertNotEquals(userOutDtoTest2.hashCode(), userOutDtoTest.hashCode());

        userOutDtoTest = buildUserOutDto(userId, firstName, gender, lastName, phone, countryCode, "changed",
                addressLine1, addressLine2, city, state, country, postalCode, role);
        assertNotEquals(userOutDtoTest2, userOutDtoTest);
        assertNotEquals(userOutDtoTest2.hashCode(), userOutDtoTest.hashCode());

        userOutDtoTest = buildUserOutDto(userId, firstName, gender, lastName, phone, countryCode, email, "changed",
                addressLine2, city, state, country, postalCode, role);
        assertNotEquals(userOutDtoTest2, userOutDtoTest);
        assertNotEquals(userOutDtoTest2.hashCode(), userOutDtoTest.hashCode());

        userOutDtoTest = buildUserOutDto(userId, firstName, gender, lastName, phone, countryCode, email, addressLine1,
                "changed", city, state, country, postalCode, role);
        assertNotEquals(userOutDtoTest2, userOutDtoTest);
        assertNotEquals(userOutDtoTest2.hashCode(), userOutDtoTest.hashCode());

        userOutDtoTest = buildUserOutDto(userId, firstName, gender, lastName, phone, countryCode, email, addressLine1,
                addressLine2, "changed", state, country, postalCode, role);
        assertNotEquals(userOutDtoTest2, userOutDtoTest);
        assertNotEquals(userOutDtoTest2.hashCode(), userOutDtoTest.hashCode());

        userOutDtoTest = buildUserOutDto(userId, firstName, gender, lastName, phone, countryCode, email, addressLine1,
                addressLine2, city, "changed", country, postalCode, role);
        assertNotEquals(userOutDtoTest2, userOutDtoTest);
        assertNotEquals(userOutDtoTest2.hashCode(), userOutDtoTest.hashCode());

        userOutDtoTest = buildUserOutDto(userId, firstName, gender, lastName, phone, countryCode, email, addressLine1,
                addressLine2, city, state, "changed", postalCode, role);
        assertNotEquals(userOutDtoTest2, userOutDtoTest);
        assertNotEquals(userOutDtoTest2.hashCode(), userOutDtoTest.hashCode());

        userOutDtoTest = buildUserOutDto(userId, firstName, gender, lastName, phone, countryCode, email, addressLine1,
                addressLine2, city, state, country, "changed", role);
        assertNotEquals(userOutDtoTest2, userOutDtoTest);
        assertNotEquals(userOutDtoTest2.hashCode(), userOutDtoTest.hashCode());

        userOutDtoTest = buildUserOutDto(userId, firstName, gender, lastName, phone, countryCode, email, addressLine1,
                addressLine2, city, state, country, postalCode, "changed");
        assertNotEquals(userOutDtoTest2, userOutDtoTest);
        assertNotEquals(userOutDtoTest2.hashCode(), userOutDtoTest.hashCode());

    }

}
