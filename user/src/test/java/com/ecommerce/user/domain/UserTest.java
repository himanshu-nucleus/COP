package com.ecommerce.user.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.Instant;

import org.junit.jupiter.api.Test;

public class UserTest {

    Instant instant = Instant.now();

    public User buildUser(Long userId, String firstName, String gender, String lastName, String phone,
            String countryCode, String email, Instant createdDt, String addressLine1, String addressLine2, String city,
            String state, String country, String postalCode, String role, char[] password, Instant updatedDate) {
        User userTest = new User();
        userTest.setAddressLine1(addressLine1);
        userTest.setAddressLine2(addressLine2);
        userTest.setCity(city);
        userTest.setCountry(country);
        userTest.setCountryCode(countryCode);
        userTest.setCreatedDt(createdDt);
        userTest.setEmail(email);
        userTest.setFirstName(firstName);
        userTest.setGender(gender);
        userTest.setLastName(lastName);
        userTest.setPassword(password);
        userTest.setPhone(phone);
        userTest.setPostalCode(postalCode);
        userTest.setRole(role);
        userTest.setState(state);
        userTest.setUpdatedDate(updatedDate);
        userTest.setUserId(userId);
        return userTest;
    }

    @Test
    public void getterSetterTest() {

        Long userId = 1l;
        String firstName = "firstName";
        String gender = "male";
        String lastName = "lastName";
        String phone = "9084883372";
        String countryCode = "+91";
        String email = "hari@nucleusteq.com";
        Instant createdDt = instant;
        String addressLine1 = "add 1";
        String addressLine2 = "add 2";
        String city = "city";
        String state = "state";
        String country = "country";
        String postalCode = "postal code";
        String role = "role";
        char[] password = { 'N', 'c' };
        Instant updatedDate = instant;

        User user = new User();

        assertNull(user.getAddressLine1());
        user.setAddressLine1(addressLine1);
        assertEquals(addressLine1, user.getAddressLine1());

        assertNull(user.getAddressLine2());
        user.setAddressLine2(addressLine2);
        assertEquals(addressLine2, user.getAddressLine2());

        assertNull(user.getCity());
        user.setCity(city);
        assertEquals(city, user.getCity());

        assertNull(user.getCountry());
        user.setCountry(country);
        assertEquals(country, user.getCountry());

        assertNull(user.getCountryCode());
        user.setCountryCode(countryCode);
        assertEquals(countryCode, user.getCountryCode());

        assertNull(user.getCreatedDt());
        user.setCreatedDt(createdDt);
        assertEquals(createdDt, user.getCreatedDt());

        assertNull(user.getEmail());
        user.setEmail(email);
        assertEquals(email, user.getEmail());

        assertNull(user.getFirstName());
        user.setFirstName(firstName);
        assertEquals(firstName, user.getFirstName());

        assertNull(user.getGender());
        user.setGender(gender);
        assertEquals(gender, user.getGender());

        assertNull(user.getLastName());
        user.setLastName(lastName);
        assertEquals(lastName, user.getLastName());

        assertNull(user.getPassword());
        user.setPassword(password);
        assertEquals(password, user.getPassword());

        assertNull(user.getPhone());
        user.setPhone(phone);
        assertEquals(phone, user.getPhone());

        assertNull(user.getPostalCode());
        user.setPostalCode(postalCode);
        assertEquals(postalCode, user.getPostalCode());

        assertNull(user.getRole());
        user.setRole(role);
        assertEquals(role, user.getRole());

        assertNull(user.getState());
        user.setState(state);
        assertEquals(state, user.getState());

        assertNull(user.getUpdatedDate());
        user.setUpdatedDate(updatedDate);
        assertEquals(updatedDate, user.getUpdatedDate());

        assertNull(user.getUserId());
        user.setUserId(userId);
        assertEquals(userId, user.getUserId());

        System.out.println(user);
    }

    @Test
    public void toStringTest() {

        String userTestString = "User(userId=1, firstName=firstName, gender=male, lastName=lastName, phone=9084883372, countryCode=+91, email=hari@nucleusteq.com, createdDt=2023-06-08T07:01:43.253777800Z, addressLine1=add 1, addressLine2=add 2, city=city, state=state, country=country, postalCode=postal code, role=role, password=[N, c], updatedDate=2023-06-08T07:01:43.253777800Z)";

        Long userId = 1l;
        String firstName = "firstName";
        String gender = "male";
        String lastName = "lastName";
        String phone = "9084883372";
        String countryCode = "+91";
        String email = "hari@nucleusteq.com";
        Instant createdDt = Instant.parse("2023-06-08T07:01:43.253777800Z");
        String addressLine1 = "add 1";
        String addressLine2 = "add 2";
        String city = "city";
        String state = "state";
        String country = "country";
        String postalCode = "postal code";
        String role = "role";
        char[] password = { 'N', 'c' };
        Instant updatedDate = Instant.parse("2023-06-08T07:01:43.253777800Z");

        User userTest = buildUser(userId, firstName, gender, lastName, phone, countryCode, email, createdDt,
                addressLine1, addressLine2, city, state, country, postalCode, role, password, updatedDate);
        assertEquals(userTestString, userTest.toString());

    }

    @Test
    public void equalsAndHashCodeTest() {


        Long userId = 1l;
        String firstName = "firstName";
        String gender = "male";
        String lastName = "lastName";
        String phone = "9084883372";
        String countryCode = "+91";
        String email = "hari@nucleusteq.com";
        Instant createdDt = instant;
        String addressLine1 = "add 1";
        String addressLine2 = "add 2";
        String city = "city";
        String state = "state";
        String country = "country";
        String postalCode = "postal code";
        String role = "role";
        char[] password = { 'N', 'c' };
        Instant updatedDate = instant;

        User userTest = buildUser(userId, firstName, gender, lastName, phone, countryCode, email, createdDt,
                addressLine1, addressLine2, city, state, country, postalCode, role, password, updatedDate);
        User userTest2 = buildUser(userId, firstName, gender, lastName, phone, countryCode, email, createdDt,
                addressLine1, addressLine2, city, state, country, postalCode, role, password, updatedDate);

        assertEquals(userTest, userTest);
        assertEquals(userTest.hashCode(), userTest.hashCode());

        assertNotEquals(userTest, new Object());
        assertNotEquals(userTest2, null);

        assertEquals(userTest2, userTest2);
        assertEquals(userTest2.hashCode(), userTest2.hashCode());

        userTest = buildUser(2l, firstName, gender, lastName, phone, countryCode, email, createdDt,
                addressLine1, addressLine2, city, state, country, postalCode, role, password, updatedDate);
        assertNotEquals(userTest2, userTest);
        assertNotEquals(userTest2.hashCode(), userTest.hashCode());

        userTest = buildUser(userId, "changed", gender, lastName, phone, countryCode, email, createdDt,
                addressLine1, addressLine2, city, state, country, postalCode, role, password, updatedDate);
        assertNotEquals(userTest2, userTest);
        assertNotEquals(userTest2.hashCode(), userTest.hashCode());
        
        userTest = buildUser(userId, firstName, "changed", lastName, phone, countryCode, email, createdDt,
                addressLine1, addressLine2, city, state, country, postalCode, role, password, updatedDate);
        assertNotEquals(userTest2, userTest);
        assertNotEquals(userTest2.hashCode(), userTest.hashCode());
        
        userTest = buildUser(userId, firstName, gender, "changed", phone, countryCode, email, createdDt,
                addressLine1, addressLine2, city, state, country, postalCode, role, password, updatedDate);
        assertNotEquals(userTest2, userTest);
        assertNotEquals(userTest2.hashCode(), userTest.hashCode());
        
        userTest = buildUser(userId, firstName, gender, lastName, "changed", countryCode, email, createdDt,
                addressLine1, addressLine2, city, state, country, postalCode, role, password, updatedDate);
        assertNotEquals(userTest2, userTest);
        assertNotEquals(userTest2.hashCode(), userTest.hashCode());
        
        userTest = buildUser(userId, firstName, gender, lastName, phone, "changed", email, createdDt,
                addressLine1, addressLine2, city, state, country, postalCode, role, password, updatedDate);
        assertNotEquals(userTest2, userTest);
        assertNotEquals(userTest2.hashCode(), userTest.hashCode());
        
        userTest = buildUser(userId, firstName, gender, lastName, phone, countryCode, "changed", createdDt,
                addressLine1, addressLine2, city, state, country, postalCode, role, password, updatedDate);
        assertNotEquals(userTest2, userTest);
        assertNotEquals(userTest2.hashCode(), userTest.hashCode());
        
        Instant newInstant = Instant.now();
        userTest = buildUser(userId, firstName, gender, lastName, phone, countryCode, email, newInstant,
                addressLine1, addressLine2, city, state, country, postalCode, role, password, updatedDate);
        assertNotEquals(userTest2, userTest);
        assertNotEquals(userTest2.hashCode(), userTest.hashCode());
        
        userTest = buildUser(userId, firstName, gender, lastName, phone, countryCode, email, createdDt,
                "changed", addressLine2, city, state, country, postalCode, role, password, updatedDate);
        assertNotEquals(userTest2, userTest);
        assertNotEquals(userTest2.hashCode(), userTest.hashCode());
        
        userTest = buildUser(userId, firstName, gender, lastName, phone, countryCode, email, createdDt,
                addressLine1, "changed", city, state, country, postalCode, role, password, updatedDate);
        assertNotEquals(userTest2, userTest);
        assertNotEquals(userTest2.hashCode(), userTest.hashCode());
        
        userTest = buildUser(userId, firstName, gender, lastName, phone, countryCode, email, createdDt,
                addressLine1, addressLine2, "changed", state, country, postalCode, role, password, updatedDate);
        assertNotEquals(userTest2, userTest);
        assertNotEquals(userTest2.hashCode(), userTest.hashCode());

        userTest = buildUser(userId, firstName, gender, lastName, phone, countryCode, email, createdDt,
                addressLine1, addressLine2, city, "changed", country, postalCode, role, password, updatedDate);
        assertNotEquals(userTest2, userTest);
        assertNotEquals(userTest2.hashCode(), userTest.hashCode());
        
        userTest = buildUser(userId, firstName, gender, lastName, phone, countryCode, email, createdDt,
                addressLine1, addressLine2, city, state, "changed", postalCode, role, password, updatedDate);
        assertNotEquals(userTest2, userTest);
        assertNotEquals(userTest2.hashCode(), userTest.hashCode());
        
        userTest = buildUser(userId, firstName, gender, lastName, phone, countryCode, email, createdDt,
                addressLine1, addressLine2, city, state, country, "changed", role, password, updatedDate);
        assertNotEquals(userTest2, userTest);
        assertNotEquals(userTest2.hashCode(), userTest.hashCode());
        
        userTest = buildUser(userId, firstName, gender, lastName, phone, countryCode, email, createdDt,
                addressLine1, addressLine2, city, state, country, postalCode, "changed", password, updatedDate);
        assertNotEquals(userTest2, userTest);
        assertNotEquals(userTest2.hashCode(), userTest.hashCode());
        
        char[] newPass = {'n','b'};
        userTest = buildUser(userId, firstName, gender, lastName, phone, countryCode, email, createdDt,
                addressLine1, addressLine2, city, state, country, postalCode, role, newPass, updatedDate);
        assertNotEquals(userTest2, userTest);
        assertNotEquals(userTest2.hashCode(), userTest.hashCode());
        
        userTest = buildUser(userId, firstName, gender, lastName, phone, countryCode, email, createdDt,
                addressLine1, addressLine2, city, state, country, postalCode, role, password, newInstant);
        assertNotEquals(userTest2, userTest);
        assertNotEquals(userTest2.hashCode(), userTest.hashCode());
        
    }

}
