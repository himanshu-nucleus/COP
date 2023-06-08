package com.ecommerce.user.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

@Service
public class UserService {

	/**
	 * The model mapper object.
	 */
	private ModelMapper modelMapper = new ModelMapper();

	/**
	 * ProductRepository
	 */
	@Autowired
	private UserRepository userRepository;

	/**
	 * @param signupIndto
	 * @return SignupOutDto
	 * @throws InvalidDetailsException
	 * @throws RecordAlreadyExistsException
	 */
	public SignupOutDto signup(SignupInDto signupIndto) throws RecordAlreadyExistsException {
	
		signupIndto.setEmail(signupIndto.getEmail().toLowerCase());
		Optional<User> optionalUser = userRepository.findByEmail(signupIndto.getEmail());
		if (optionalUser.isPresent()) {
			throw new RecordAlreadyExistsException("Record already exist for email : " + signupIndto.getEmail());
		}
	
		User user = modelMapper.map(signupIndto, User.class);
		User returnedUser = userRepository.save(user);

		SignupOutDto signupOutDto = modelMapper.map(returnedUser, SignupOutDto.class);
		return signupOutDto;
	}

	/**
	 * @param userId
	 * @return SignupOutDto
	 * @throws RecordNotFoundException
	 */
	public SignupOutDto getUserDetails(Long userId) throws RecordNotFoundException {

		Optional<User> optionalUser = userRepository.findById(userId);
		if (optionalUser.isEmpty()) {
			throw new RecordNotFoundException("Record not found for user Id : " + userId);
		}

		SignupOutDto signupOutDto = modelMapper.map(optionalUser.get(), SignupOutDto.class);
		return signupOutDto;
	}

	/**
	 * @param loginInDTO
	 * @return UserOutDTO
	 * @throws RecordNotFoundException
	 * @throws InvalidDetailsException
	 */
	public UserOutDto login(LoginInDto loginInDTO) throws RecordNotFoundException, InvalidDetailsException {

		Optional<User> optionalUser = userRepository.findByEmail(loginInDTO.getEmail());
		if (optionalUser.isEmpty()) {
			throw new RecordNotFoundException("Record not found for user email : " + loginInDTO.getEmail());
		}

		if (!String.valueOf(optionalUser.get().getPassword()).equals(String.valueOf(loginInDTO.getPassword()))) {
			throw new InvalidDetailsException(ResponseConstants.INVALID_EMAIL_OR_PASSWORD);
		}

		UserOutDto userOutDto = modelMapper.map(optionalUser.get(), UserOutDto.class);
		return userOutDto;

	}

	/**
	 * @param userId
	 * @param updateUserInDto
	 * @return SignupOutDto
	 * @throws InvalidDetailsException
	 * @throws RecordAlreadyExistsException
	 * @throws RecordNotFoundException
	 */
	public SignupOutDto updateUserDetails(Long userId, UpdateUserInDto updateUserInDto)
			throws InvalidDetailsException, RecordAlreadyExistsException, RecordNotFoundException {
		
		Optional<User> userById = userRepository.findById(userId);
		if (userById.isEmpty()) {
			throw new RecordNotFoundException("Record not found for user Id : " + userId);
		}

		updateUserInDto.setEmail(updateUserInDto.getEmail().toLowerCase());
		if (!userById.get().getEmail().equals(updateUserInDto.getEmail())) {
			Optional<User> optionalUser = userRepository.findByEmail(updateUserInDto.getEmail());
			if (optionalUser.isPresent()) {
				throw new RecordAlreadyExistsException(
						"Record already exist for email : " + updateUserInDto.getEmail());
			}
		}

		User user = modelMapper.map(updateUserInDto, User.class);
		user.setUserId(userId);
		user.setPassword(userById.get().getPassword());
		user = userRepository.save(user);

		SignupOutDto signupOutDto = modelMapper.map(user, SignupOutDto.class);
		return signupOutDto;
	}

	/**
	 * @param userId
	 * @return ResponseOutDto
	 * @throws RecordNotFoundException
	 */
	public ResponseOutDto deleteUser(Long userId) throws RecordNotFoundException {
		Optional<User> optionalUser = userRepository.findById(userId);
		if (optionalUser.isEmpty()) {
			throw new RecordNotFoundException("Record not found for user id : " + userId);
		}

		userRepository.delete(optionalUser.get());

		ResponseOutDto responseOutDto = new ResponseOutDto();
		responseOutDto.setMessage(ResponseConstants.USER_DELETED);
		return responseOutDto;
	}

	public String checkUserAndRole(Long userId, String role) {
		String userRole = null;
		Optional<User> optionalUser = userRepository.findByUserIdAndRole(userId, role);
		if (optionalUser.isPresent()) {
			userRole = optionalUser.get().getRole();	
		}
		return userRole;
	}

}
