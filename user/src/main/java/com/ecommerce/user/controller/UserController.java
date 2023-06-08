package com.ecommerce.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.user.constants.RestURLConstants;
import com.ecommerce.user.dto.LoginInDTO;
import com.ecommerce.user.dto.ResponseOutDto;
import com.ecommerce.user.dto.SignupInDto;
import com.ecommerce.user.dto.SignupOutDto;
import com.ecommerce.user.dto.UpdateUserInDto;
import com.ecommerce.user.dto.UserOutDTO;
import com.ecommerce.user.exception.InvalidDetailsException;
import com.ecommerce.user.exception.RecordAlreadyExistsException;
import com.ecommerce.user.exception.RecordNotFoundException;
import com.ecommerce.user.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(RestURLConstants.BASE_URL)
public class UserController {

	/**
	 * The logger object.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	/**
	 * Autowired UserService.
	 */
	@Autowired
	private UserService userService;

	/**
	 * @param signupIndto
	 * @return signupOutDTO
	 * @throws RecordAlreadyExistsException
	 * @throws InvalidDetailsException
	 */
	@PostMapping(path = "/signup")
	public final SignupOutDto signupUser(@RequestBody final @Valid SignupInDto signupIndto)
			throws InvalidDetailsException, RecordAlreadyExistsException {
		LOGGER.info("signup started for the user email : {}", signupIndto.getEmail());
		SignupOutDto signupOutDTO = userService.signup(signupIndto);
		LOGGER.info("Successfully registered for the user email : {}", signupIndto.getEmail());
		return signupOutDTO;
	}

	/**
	 * @param userId
	 * @return SignupOutDto
	 * @throws RecordNotFoundException
	 */
	@GetMapping(path = "/details/{userId}")
	public final SignupOutDto getUserDetails(@PathVariable final Long userId) throws RecordNotFoundException {
		LOGGER.info("Get user details started for user Id : {}", userId);
		SignupOutDto signupOutDTO = userService.getUserDetails(userId);
		LOGGER.info("Get user details started for user Id : {}", userId);
		return signupOutDTO;
	}

	/**
	 * @param userId
	 * @return SignupOutDto
	 * @throws RecordAlreadyExistsException
	 * @throws InvalidDetailsException
	 * @throws RecordNotFoundException
	 */
	@PutMapping(path = "/update/{userId}")
	public final SignupOutDto getUserDetails(@PathVariable final Long userId,
			@RequestBody final @Valid UpdateUserInDto updateUserInDto)
			throws InvalidDetailsException, RecordAlreadyExistsException, RecordNotFoundException {
		LOGGER.info("Update user details started for user Id : {}", userId);
		SignupOutDto signupOutDTO = userService.updateUserDetails(userId, updateUserInDto);
		LOGGER.info("Update user details started for user Id : {}", userId);
		return signupOutDTO;
	}

	/**
	 * @throws InvalidCredentialsExceptionTest if user id or password is incorrect.
	 * @param loginInDTO loginDTO object to store email and password.
	 * @return a positive response if email and password exist.
	 * @throws InvalidDetailsException
	 * @throws RecordNotFoundException
	 */
	@PostMapping(path = "/login")
	public final UserOutDTO checkLogin(@RequestBody final LoginInDTO loginInDTO)
			throws RecordNotFoundException, InvalidDetailsException {
		LOGGER.info("Login started for the user email : {}", loginInDTO.getEmail());
		UserOutDTO userOutDTO = userService.login(loginInDTO);
		LOGGER.info("Successfully logged in for the user email : {}", loginInDTO.getEmail());
		return userOutDTO;
	}

	/**
	 * @param userId
	 * @return ResponseOutDto
	 * @throws RecordNotFoundException
	 */
	@DeleteMapping(path = "/delete/{userId}")
	public final ResponseOutDto deleteUser(@PathVariable final Long userId) throws RecordNotFoundException {
		LOGGER.info("Delete user started for user id : {}", userId);
		ResponseOutDto responseOutDto = userService.deleteUser(userId);
		LOGGER.info("Delete user completed for user id : {}", userId);
		return responseOutDto;
	}
	
	
	/**
	 * @param userId
	 * @return Boolean
	 * @throws RecordNotFoundException
	 */
	@GetMapping(path = "/check/{userId}/role/{role}")
	public final String checkUser(@PathVariable final Long userId,
			@PathVariable final String role) throws RecordNotFoundException {
		LOGGER.info("Check user started for user id : {}", userId);
		String userRole = userService.checkUserAndRole(userId, role);
		LOGGER.info("Check user completed for user id : {}", userId);
		return userRole;
	}

}
