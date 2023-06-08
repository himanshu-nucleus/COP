package com.ecommerce.user.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.Instant;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import com.ecommerce.user.constants.ResponseConstants;
import com.ecommerce.user.domain.User;
import com.ecommerce.user.dto.LoginInDto;
import com.ecommerce.user.dto.ResponseOutDto;
import com.ecommerce.user.dto.SignupInDto;
import com.ecommerce.user.dto.SignupOutDto;
import com.ecommerce.user.dto.UpdateUserInDto;
import com.ecommerce.user.dto.UserOutDto;
import com.ecommerce.user.exception.InvalidDetailsException;
import com.ecommerce.user.exception.RecordAlreadyExistsException;
import com.ecommerce.user.exception.RecordNotFoundException;
import com.ecommerce.user.repository.UserRepository;

public class UserServiceTest {

    /**
     * The model mapper object.
     */
    private ModelMapper modelMapper = new ModelMapper();

    /**
     * UserRepository
     */
    @Mock
    private UserRepository userRepository;

    /**
     * UserService
     */
    @InjectMocks
    private UserService userService;

    @Test
    public void signupTest() throws RecordAlreadyExistsException {

        MockitoAnnotations.openMocks(this);

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

        Optional<User> empUser = Optional.empty();
        Mockito.when(userRepository.findByEmail(signupInDtoTest.getEmail())).thenReturn(empUser);

        Instant createdDt = Instant.parse("2023-06-08T07:01:43.253777800Z");
        Instant updatedDate = Instant.parse("2023-06-08T07:01:43.253777800Z");

        User user = modelMapper.map(signupInDtoTest, User.class);
        User mainUser = buildUser(userId, firstName, gender, lastName, phone, countryCode, email, createdDt,
                addressLine1, addressLine2, city, state, country, postalCode, role, password, updatedDate);

        Mockito.when(userRepository.save(user)).thenReturn(mainUser);
        assertEquals(signupOutDtoTest, userService.signup(signupInDtoTest));

    }

    @Test
    public void getUserDetailsTest() throws RecordAlreadyExistsException, RecordNotFoundException {

        MockitoAnnotations.openMocks(this);

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

        SignupOutDto signupOutDtoTest = buildSignupOutDto(userId, firstName, gender, lastName, phone, countryCode,
                email, addressLine1, addressLine2, city, state, country, postalCode, role);

        Instant createdDt = Instant.parse("2023-06-08T07:01:43.253777800Z");
        Instant updatedDate = Instant.parse("2023-06-08T07:01:43.253777800Z");

        User user = buildUser(userId, firstName, gender, lastName, phone, countryCode, email, createdDt, addressLine1,
                addressLine2, city, state, country, postalCode, role, password, updatedDate);

        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        assertEquals(signupOutDtoTest, userService.getUserDetails(userId));

        Optional<User> empUser = Optional.empty();
        Mockito.when(userRepository.findById(userId)).thenReturn(empUser);
        RecordNotFoundException recordNotFoundException = assertThrows(RecordNotFoundException.class,
                () -> userService.getUserDetails(userId));
        assertEquals("Record not found for user Id : " + userId, recordNotFoundException.getMessage());

    }
    
    @Test
    public void loginTest() throws RecordAlreadyExistsException, RecordNotFoundException, InvalidDetailsException {

        MockitoAnnotations.openMocks(this);

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
        Instant createdDt = Instant.parse("2023-06-08T07:01:43.253777800Z");
        Instant updatedDate = Instant.parse("2023-06-08T07:01:43.253777800Z");

        char[] password = { 'N', 'c' };
        Long userId = 1L;

        LoginInDto loginInDto = buildLogin(email, password);

        UserOutDto userOutDtoTest = buildUserOutDto(userId, firstName, gender, lastName, phone, countryCode, email,
                addressLine1, addressLine2, city, state, country, postalCode, role);

        User user = buildUser(userId, firstName, gender, lastName, phone, countryCode, email, createdDt, addressLine1,
                addressLine2, city, state, country, postalCode, role, password, updatedDate);

        Mockito.when(userRepository.findByEmail(loginInDto.getEmail())).thenReturn(Optional.of(user));
        assertEquals(userOutDtoTest, userService.login(loginInDto));
        
        Optional<User> empUser = Optional.empty();
        Mockito.when(userRepository.findByEmail(loginInDto.getEmail())).thenReturn(empUser);
        RecordNotFoundException recordNotFoundException = assertThrows(RecordNotFoundException.class,
                () -> userService.login(loginInDto));
        assertEquals("Record not found for user email : " + loginInDto.getEmail(), recordNotFoundException.getMessage());

    }
    
    @Test
    public void updateUserDetailsTest() throws InvalidDetailsException, RecordAlreadyExistsException, RecordNotFoundException {

        MockitoAnnotations.openMocks(this);

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
        Instant createdDt = Instant.parse("2023-06-08T07:01:43.253777800Z");
        Instant updatedDate = Instant.parse("2023-06-08T07:01:43.253777800Z");

        char[] password = { 'N', 'c' };
        Long userId = 1L;
        LoginInDto loginInDto = buildLogin(email, password);

        Optional<User> empUser = Optional.empty();
        User user = buildUser(userId, firstName, gender, lastName, phone, countryCode, email, createdDt, addressLine1,
                addressLine2, city, state, country, postalCode, role, password, updatedDate);

        UpdateUserInDto updateUserInDtoTest = buildUpdateUserInDto(firstName, gender, lastName, phone, countryCode, email,
                addressLine1, addressLine2, city, state, country, postalCode, role);
        
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        Mockito.when(userRepository.findByEmail(updateUserInDtoTest.getEmail())).thenReturn(empUser);
        
        User rawUser = modelMapper.map(updateUserInDtoTest, User.class);
        rawUser.setUserId(userId);
        rawUser.setPassword(password);
        Mockito.when(userRepository.save(rawUser)).thenReturn(user);
       
        SignupOutDto signupOutDtoTest = buildSignupOutDto(userId, firstName, gender, lastName, phone, countryCode,
                email, addressLine1, addressLine2, city, state, country, postalCode, role);
        
        assertEquals(signupOutDtoTest, userService.updateUserDetails(userId, updateUserInDtoTest));
        
        Mockito.when(userRepository.findByEmail(loginInDto.getEmail())).thenReturn(empUser);
        RecordNotFoundException recordNotFoundException = assertThrows(RecordNotFoundException.class,
                () -> userService.login(loginInDto));
        assertEquals("Record not found for user email : " + loginInDto.getEmail(), recordNotFoundException.getMessage());

    }
    
    
    @Test
    public void deleteUserTest() throws RecordNotFoundException {

        MockitoAnnotations.openMocks(this);

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
        Instant createdDt = Instant.parse("2023-06-08T07:01:43.253777800Z");
        Instant updatedDate = Instant.parse("2023-06-08T07:01:43.253777800Z");
        char[] password = { 'N', 'c' };
        Long userId = 1L;

        Optional<User> empUser = Optional.empty();
        User user = buildUser(userId, firstName, gender, lastName, phone, countryCode, email, createdDt, addressLine1,
                addressLine2, city, state, country, postalCode, role, password, updatedDate);
        
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        ResponseOutDto responseOutDto = new ResponseOutDto();
        responseOutDto.setMessage(ResponseConstants.USER_DELETED);
        
        assertEquals(responseOutDto, userService.deleteUser(userId));
        
        Mockito.when(userRepository.findById(userId)).thenReturn(empUser);
        RecordNotFoundException recordNotFoundException = assertThrows(RecordNotFoundException.class,
                () -> userService.deleteUser(userId));
        assertEquals("Record not found for user id : " + userId, recordNotFoundException.getMessage());

    }
    
    @Test
    public void checkUserAndRoleTest() throws RecordNotFoundException {

        MockitoAnnotations.openMocks(this);

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
        String role = "seller";
        Instant createdDt = Instant.parse("2023-06-08T07:01:43.253777800Z");
        Instant updatedDate = Instant.parse("2023-06-08T07:01:43.253777800Z");
        char[] password = { 'N', 'c' };
        Long userId = 1L;

        User user = buildUser(userId, firstName, gender, lastName, phone, countryCode, email, createdDt, addressLine1,
                addressLine2, city, state, country, postalCode, role, password, updatedDate);
        
        Mockito.when(userRepository.findByUserIdAndRole(userId, role)).thenReturn(Optional.of(user));        
        assertEquals("seller", userService.checkUserAndRole(userId, role));

    }
    
    
    
    public LoginInDto buildLogin(String email, char[] password) {
        LoginInDto loginInDto = new LoginInDto();
        loginInDto.setEmail(email);
        loginInDto.setPassword(password);
        return loginInDto;
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

}
