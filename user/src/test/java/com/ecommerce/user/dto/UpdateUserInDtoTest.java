package com.ecommerce.user.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class UpdateUserInDtoTest {

    public UpdateUserInDto buildUpdateUserInDto(String firstName, String gender, String lastName, String phone,
            String countryCode, String email, String addressLine1, String addressLine2, String city, String state,
            String country, String postalCode, String role) {

        UpdateUserInDto updateUserInDtoTest = new UpdateUserInDto();
        updateUserInDtoTest.setAddressLine1(addressLine1);
        updateUserInDtoTest.setAddressLine2(addressLine2);
        updateUserInDtoTest.setCity(city);
        updateUserInDtoTest.setCountry(country);
        updateUserInDtoTest.setCountryCode(countryCode);
        updateUserInDtoTest.setEmail(email);
        updateUserInDtoTest.setFirstName(firstName);
        updateUserInDtoTest.setGender(gender);
        updateUserInDtoTest.setLastName(lastName);
        updateUserInDtoTest.setPhone(phone);
        updateUserInDtoTest.setPostalCode(postalCode);
        updateUserInDtoTest.setRole(role);
        updateUserInDtoTest.setState(state);
        return updateUserInDtoTest;
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

        UpdateUserInDto updateUserInDto = new UpdateUserInDto();

        assertNull(updateUserInDto.getFirstName());
        updateUserInDto.setFirstName(firstName);
        assertEquals(firstName, updateUserInDto.getFirstName());

        assertNull(updateUserInDto.getGender());
        updateUserInDto.setGender(gender);
        assertEquals(gender, updateUserInDto.getGender());

        assertNull(updateUserInDto.getLastName());
        updateUserInDto.setLastName(lastName);
        assertEquals(lastName, updateUserInDto.getLastName());

        assertNull(updateUserInDto.getPhone());
        updateUserInDto.setPhone(phone);
        assertEquals(phone, updateUserInDto.getPhone());

        assertNull(updateUserInDto.getCountryCode());
        updateUserInDto.setCountryCode(countryCode);
        assertEquals(countryCode, updateUserInDto.getCountryCode());

        assertNull(updateUserInDto.getEmail());
        updateUserInDto.setEmail(email);
        assertEquals(email, updateUserInDto.getEmail());

        assertNull(updateUserInDto.getAddressLine1());
        updateUserInDto.setAddressLine1(addressLine1);
        assertEquals(addressLine1, updateUserInDto.getAddressLine1());

        assertNull(updateUserInDto.getAddressLine2());
        updateUserInDto.setAddressLine2(addressLine2);
        assertEquals(addressLine2, updateUserInDto.getAddressLine2());

        assertNull(updateUserInDto.getCity());
        updateUserInDto.setCity(city);
        assertEquals(city, updateUserInDto.getCity());

        assertNull(updateUserInDto.getState());
        updateUserInDto.setState(state);
        assertEquals(state, updateUserInDto.getState());

        assertNull(updateUserInDto.getCountry());
        updateUserInDto.setCountry(country);
        assertEquals(country, updateUserInDto.getCountry());

        assertNull(updateUserInDto.getPostalCode());
        updateUserInDto.setPostalCode(postalCode);
        assertEquals(postalCode, updateUserInDto.getPostalCode());

        assertNull(updateUserInDto.getRole());
        updateUserInDto.setRole(role);
        assertEquals(role, updateUserInDto.getRole());

        System.out.println(updateUserInDto);
    }

    @Test
    public void toStringTest() {

        String updateUserInDtoTestString = "UpdateUserInDto(firstName=firstName, gender=male, lastName=lastName, phone=9084883372, countryCode=+91, email=hari@nucleusteq.com, addressLine1=add 1, addressLine2=add 2, city=city, state=state, country=country, postalCode=postal code, role=role)";

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

        UpdateUserInDto updateUserInDtoTest = buildUpdateUserInDto(firstName, gender, lastName, phone, countryCode, email,
                addressLine1, addressLine2, city, state, country, postalCode, role);
        assertEquals(updateUserInDtoTestString, updateUserInDtoTest.toString());

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

        UpdateUserInDto updateUserInDtoTest = buildUpdateUserInDto(firstName, gender, lastName, phone, countryCode, email,
                addressLine1, addressLine2, city, state, country, postalCode, role);
        UpdateUserInDto updateUserInDtoTest2 = buildUpdateUserInDto(firstName, gender, lastName, phone, countryCode, email,
                addressLine1, addressLine2, city, state, country, postalCode, role);

        assertEquals(updateUserInDtoTest, updateUserInDtoTest);
        assertEquals(updateUserInDtoTest.hashCode(), updateUserInDtoTest.hashCode());

        assertNotEquals(updateUserInDtoTest, new Object());
        assertNotEquals(updateUserInDtoTest2, null);

        assertEquals(updateUserInDtoTest2, updateUserInDtoTest2);
        assertEquals(updateUserInDtoTest2.hashCode(), updateUserInDtoTest2.hashCode());

        updateUserInDtoTest = buildUpdateUserInDto("changed", gender, lastName, phone, countryCode, email, addressLine1,
                addressLine2, city, state, country, postalCode, role);
        assertNotEquals(updateUserInDtoTest2, updateUserInDtoTest);
        assertNotEquals(updateUserInDtoTest2.hashCode(), updateUserInDtoTest.hashCode());

        updateUserInDtoTest = buildUpdateUserInDto(firstName, "changed", lastName, phone, countryCode, email, addressLine1,
                addressLine2, city, state, country, postalCode, role);
        assertNotEquals(updateUserInDtoTest2, updateUserInDtoTest);
        assertNotEquals(updateUserInDtoTest2.hashCode(), updateUserInDtoTest.hashCode());

        updateUserInDtoTest = buildUpdateUserInDto(firstName, gender, "changed", phone, countryCode, email, addressLine1,
                addressLine2, city, state, country, postalCode, role);
        assertNotEquals(updateUserInDtoTest2, updateUserInDtoTest);
        assertNotEquals(updateUserInDtoTest2.hashCode(), updateUserInDtoTest.hashCode());

        updateUserInDtoTest = buildUpdateUserInDto(firstName, gender, lastName, "changed", countryCode, email, addressLine1,
                addressLine2, city, state, country, postalCode, role);
        assertNotEquals(updateUserInDtoTest2, updateUserInDtoTest);
        assertNotEquals(updateUserInDtoTest2.hashCode(), updateUserInDtoTest.hashCode());

        updateUserInDtoTest = buildUpdateUserInDto(firstName, gender, lastName, phone, "changed", email, addressLine1,
                addressLine2, city, state, country, postalCode, role);
        assertNotEquals(updateUserInDtoTest2, updateUserInDtoTest);
        assertNotEquals(updateUserInDtoTest2.hashCode(), updateUserInDtoTest.hashCode());

        updateUserInDtoTest = buildUpdateUserInDto(firstName, gender, lastName, phone, countryCode, "changed", addressLine1,
                addressLine2, city, state, country, postalCode, role);
        assertNotEquals(updateUserInDtoTest2, updateUserInDtoTest);
        assertNotEquals(updateUserInDtoTest2.hashCode(), updateUserInDtoTest.hashCode());

        updateUserInDtoTest = buildUpdateUserInDto(firstName, gender, lastName, phone, countryCode, email, "changed",
                addressLine2, city, state, country, postalCode, role);
        assertNotEquals(updateUserInDtoTest2, updateUserInDtoTest);
        assertNotEquals(updateUserInDtoTest2.hashCode(), updateUserInDtoTest.hashCode());

        updateUserInDtoTest = buildUpdateUserInDto(firstName, gender, lastName, phone, countryCode, email, addressLine1,
                "changed", city, state, country, postalCode, role);
        assertNotEquals(updateUserInDtoTest2, updateUserInDtoTest);
        assertNotEquals(updateUserInDtoTest2.hashCode(), updateUserInDtoTest.hashCode());

        updateUserInDtoTest = buildUpdateUserInDto(firstName, gender, lastName, phone, countryCode, email, addressLine1,
                addressLine2, "changed", state, country, postalCode, role);
        assertNotEquals(updateUserInDtoTest2, updateUserInDtoTest);
        assertNotEquals(updateUserInDtoTest2.hashCode(), updateUserInDtoTest.hashCode());

        updateUserInDtoTest = buildUpdateUserInDto(firstName, gender, lastName, phone, countryCode, email, addressLine1,
                addressLine2, city, "changed", country, postalCode, role);
        assertNotEquals(updateUserInDtoTest2, updateUserInDtoTest);
        assertNotEquals(updateUserInDtoTest2.hashCode(), updateUserInDtoTest.hashCode());

        updateUserInDtoTest = buildUpdateUserInDto(firstName, gender, lastName, phone, countryCode, email, addressLine1,
                addressLine2, city, state, "changed", postalCode, role);
        assertNotEquals(updateUserInDtoTest2, updateUserInDtoTest);
        assertNotEquals(updateUserInDtoTest2.hashCode(), updateUserInDtoTest.hashCode());

        updateUserInDtoTest = buildUpdateUserInDto(firstName, gender, lastName, phone, countryCode, email, addressLine1,
                addressLine2, city, state, country, "changed", role);
        assertNotEquals(updateUserInDtoTest2, updateUserInDtoTest);
        assertNotEquals(updateUserInDtoTest2.hashCode(), updateUserInDtoTest.hashCode());

        updateUserInDtoTest = buildUpdateUserInDto(firstName, gender, lastName, phone, countryCode, email, addressLine1,
                addressLine2, city, state, country, postalCode, "changed");
        assertNotEquals(updateUserInDtoTest2, updateUserInDtoTest);
        assertNotEquals(updateUserInDtoTest2.hashCode(), updateUserInDtoTest.hashCode());

    }

}
