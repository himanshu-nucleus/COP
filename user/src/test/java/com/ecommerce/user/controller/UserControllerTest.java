package com.ecommerce.user.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ecommerce.user.constants.ResponseConstants;
import com.ecommerce.user.dto.LoginInDto;
import com.ecommerce.user.dto.ResponseOutDto;
import com.ecommerce.user.dto.SignupInDto;
import com.ecommerce.user.dto.SignupOutDto;
import com.ecommerce.user.dto.UpdateUserInDto;
import com.ecommerce.user.dto.UserOutDto;
import com.ecommerce.user.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserControllerTest {

    /**
     * UserController
     */
    @InjectMocks
    private UserController userController;

    /**
     * UserService
     */
    @Mock
    private UserService userService;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void signupTest() throws Exception {

        MockitoAnnotations.openMocks(this);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

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

        Long userId = 1L;

        SignupInDto signupInDtoTest = buildSignupInDto(firstName, gender, lastName, phone, countryCode, email,
                addressLine1, addressLine2, city, state, country, postalCode, role, password);
        SignupOutDto signupOutDtoTest = buildSignupOutDto(userId, firstName, gender, lastName, phone, countryCode,
                email, addressLine1, addressLine2, city, state, country, postalCode, role);

        String inputJSON = objectMapper.writeValueAsString(signupInDtoTest);
        when(userService.signup(signupInDtoTest)).thenReturn(signupOutDtoTest);

        MvcResult mvcResult = mockMvc
                .perform(post("/v1/user/signup").contentType(MediaType.APPLICATION_JSON).content(inputJSON))
                .andReturn();
        int responseStatus = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), responseStatus);

    }

    @Test
    public void getUserDetailsTest() throws Exception {

        MockitoAnnotations.openMocks(this);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

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

        when(userService.getUserDetails(userId)).thenReturn(signupOutDtoTest);

        MvcResult mvcResult = mockMvc.perform(get("/v1/user/details/12").contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        int responseStatus = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), responseStatus);

    }

    @Test
    public void updateUserDetailsTest() throws Exception {

        MockitoAnnotations.openMocks(this);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

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

        UpdateUserInDto updateUserInDtoTest = buildUpdateUserInDto(firstName, gender, lastName, phone, countryCode,
                email, addressLine1, addressLine2, city, state, country, postalCode, role);

        String inputJSON = objectMapper.writeValueAsString(updateUserInDtoTest);
        when(userService.updateUserDetails(userId, updateUserInDtoTest)).thenReturn(signupOutDtoTest);

        MvcResult mvcResult = mockMvc
                .perform(put("/v1/user/update/1").contentType(MediaType.APPLICATION_JSON).content(inputJSON))
                .andReturn();
        int responseStatus = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), responseStatus);

    }

    @Test
    public void loginTest() throws Exception {

        MockitoAnnotations.openMocks(this);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

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

        Long userId = 1L;

        LoginInDto loginInDto = buildLogin(email, password);

        UserOutDto userOutDtoTest = buildUserOutDto(userId, firstName, gender, lastName, phone, countryCode, email,
                addressLine1, addressLine2, city, state, country, postalCode, role);

        String inputJSON = objectMapper.writeValueAsString(loginInDto);
        when(userService.login(loginInDto)).thenReturn(userOutDtoTest);

        MvcResult mvcResult = mockMvc
                .perform(post("/v1/user/login").contentType(MediaType.APPLICATION_JSON).content(inputJSON)).andReturn();
        int responseStatus = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), responseStatus);

    }

    @Test
    public void deleteUserTest() throws Exception {

        MockitoAnnotations.openMocks(this);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        Long userId = 1L;

        ResponseOutDto responseOutDto = new ResponseOutDto();
        responseOutDto.setMessage(ResponseConstants.USER_DELETED);

        when(userService.deleteUser(userId)).thenReturn(responseOutDto);

        MvcResult mvcResult = mockMvc.perform(delete("/v1/user/delete/1").contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        int responseStatus = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), responseStatus);

    }
    
    @Test
    public void checkUserTest() throws Exception {

        MockitoAnnotations.openMocks(this);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        String role = "seller";
        Long userId = 1L;

        when(userService.checkUserAndRole(userId, role)).thenReturn("seller");

        MvcResult mvcResult = mockMvc
                .perform(get("/v1/user/check/1/role/seller").contentType(MediaType.APPLICATION_JSON)).andReturn();
        int responseStatus = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), responseStatus);

    }

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

    public LoginInDto buildLogin(String email, char[] password) {
        LoginInDto loginInDto = new LoginInDto();
        loginInDto.setEmail(email);
        loginInDto.setPassword(password);
        return loginInDto;
    }

}
