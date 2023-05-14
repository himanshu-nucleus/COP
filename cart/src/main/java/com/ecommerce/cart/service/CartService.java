package com.ecommerce.cart.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.cart.constants.ResponseConstants;
import com.ecommerce.cart.domain.Cart;
import com.ecommerce.cart.domain.Product;
import com.ecommerce.cart.dto.CartOutDto;
import com.ecommerce.cart.dto.CartProducts;
import com.ecommerce.cart.dto.CreateCartInDto;
import com.ecommerce.cart.dto.ResponseOutDto;
import com.ecommerce.cart.exception.RecordNotFoundException;
import com.ecommerce.cart.repository.CartRepository;
import com.ecommerce.cart.repository.ProductRepository;

@Service
public class CartService {

	/**
	 * The model mapper object.
	 */
	private ModelMapper modelMapper = new ModelMapper();

	/**
	 * ProductRepository
	 */
	@Autowired
	private CartRepository cartRepository;

	/**
	 * ProductRepository
	 */
	@Autowired
	private ProductRepository productRepository;

	public ResponseOutDto createCart(CreateCartInDto createCartInDto)
			throws RecordNotFoundException {

//		if (Objects.nonNull(cartId)) {
//			Optional<Cart> optCart = cartRepository.findById(cartId);
//			if (optCart.isEmpty()) {
//				throw new RecordNotFoundException("Cart not found for Id : " + cartId);
//			}
//		}

		Optional<Product> optProduct = productRepository.findById(createCartInDto.getProductId());
		if (optProduct.isEmpty()) {
			throw new RecordNotFoundException(ResponseConstants.PRODUCTS_NOT_FOUND);
		}
		
		List<CartProducts> cartProducts = new ArrayList<CartProducts>();
		CartProducts cartProduct = modelMapper.map(optProduct.get(), CartProducts.class);
		cartProducts.add(cartProduct);

		Cart cart = new Cart();
		cart.setUserId(createCartInDto.getUserId());
		cart.setCartProducts(cartProducts);
		cartRepository.save(cart);
		
		ResponseOutDto response = new ResponseOutDto();
		response.setMessage(ResponseConstants.CART_CREATED);

		return response;
	}

//	/**
//	 * @param signupIndto
//	 * @return SignupOutDto
//	 * @throws InvalidDetailsException
//	 * @throws RecordAlreadyExistsException
//	 */
//	public SignupOutDto signup(SignupInDto signupIndto) throws InvalidDetailsException, RecordAlreadyExistsException {
//		if (Objects.isNull(signupIndto) || Objects.isNull(signupIndto.getAddressLine1())
//				|| Objects.isNull(signupIndto.getAddressLine2()) || Objects.isNull(signupIndto.getCity())
//				|| Objects.isNull(signupIndto.getCountry()) || Objects.isNull(signupIndto.getCountryCode())
//				|| Objects.isNull(signupIndto.getEmail()) || Objects.isNull(signupIndto.getFirstName())
//				|| Objects.isNull(signupIndto.getGender()) || Objects.isNull(signupIndto.getLastName())
//				|| Objects.isNull(signupIndto.getPassword()) || Objects.isNull(signupIndto.getPhone())
//				|| Objects.isNull(signupIndto.getPostalCode()) || Objects.isNull(signupIndto.getRole())
//				|| Objects.isNull(signupIndto.getState())) {
//			throw new InvalidDetailsException(ResponseConstants.INVALID_INPUT_REQUEST);
//		}
//
//		signupIndto.setEmail(signupIndto.getEmail().toLowerCase());
//		Optional<User> optionalUser = userRepository.findByEmail(signupIndto.getEmail());
//		if (optionalUser.isPresent()) {
//			throw new RecordAlreadyExistsException("Record already exist for email : " + signupIndto.getEmail());
//		}
//
//		User user = modelMapper.map(signupIndto, User.class);
//		user = userRepository.save(user);
//
//		SignupOutDto signupOutDto = modelMapper.map(user, SignupOutDto.class);
//		return signupOutDto;
//	}
//
//	/**
//	 * @param userId
//	 * @return SignupOutDto
//	 * @throws RecordNotFoundException
//	 */
//	public SignupOutDto getUserDetails(Long userId) throws RecordNotFoundException {
//
//		Optional<User> optionalUser = userRepository.findById(userId);
//		if (optionalUser.isEmpty()) {
//			throw new RecordNotFoundException("Recordnot found for user Id : " + userId);
//		}
//
//		SignupOutDto signupOutDto = modelMapper.map(optionalUser.get(), SignupOutDto.class);
//		return signupOutDto;
//	}
//
//	/**
//	 * @param loginInDTO
//	 * @return UserOutDTO
//	 * @throws RecordNotFoundException
//	 * @throws InvalidDetailsException
//	 */
//	public UserOutDTO login(LoginInDTO loginInDTO) throws RecordNotFoundException, InvalidDetailsException {
//
//		Optional<User> optionalUser = userRepository.findByEmail(loginInDTO.getEmail());
//		if (optionalUser.isEmpty()) {
//			throw new RecordNotFoundException("Record not found for user email : " + loginInDTO.getEmail());
//		}
//
//		if (!String.valueOf(optionalUser.get().getPassword()).equals(String.valueOf(loginInDTO.getPassword()))) {
//			throw new InvalidDetailsException(ResponseConstants.INVALID_EMAIL_OR_PASSWORD);
//		}
//
//		UserOutDTO userOutDTO = modelMapper.map(optionalUser.get(), UserOutDTO.class);
//		return userOutDTO;
//
//	}
//
//	/**
//	 * @param userId
//	 * @param updateUserInDto
//	 * @return SignupOutDto
//	 * @throws InvalidDetailsException
//	 * @throws RecordAlreadyExistsException
//	 * @throws RecordNotFoundException
//	 */
//	public SignupOutDto updateUserDetails(Long userId, UpdateUserInDto updateUserInDto)
//			throws InvalidDetailsException, RecordAlreadyExistsException, RecordNotFoundException {
//		if (Objects.isNull(updateUserInDto) || Objects.isNull(updateUserInDto.getAddressLine1())
//				|| Objects.isNull(updateUserInDto.getAddressLine2()) || Objects.isNull(updateUserInDto.getCity())
//				|| Objects.isNull(updateUserInDto.getCountry()) || Objects.isNull(updateUserInDto.getCountryCode())
//				|| Objects.isNull(updateUserInDto.getEmail()) || Objects.isNull(updateUserInDto.getFirstName())
//				|| Objects.isNull(updateUserInDto.getGender()) || Objects.isNull(updateUserInDto.getLastName())
//				|| Objects.isNull(updateUserInDto.getPhone()) || Objects.isNull(updateUserInDto.getPostalCode())
//				|| Objects.isNull(updateUserInDto.getRole()) || Objects.isNull(updateUserInDto.getState())) {
//			throw new InvalidDetailsException(ResponseConstants.INVALID_INPUT_REQUEST);
//		}
//
//		Optional<User> userById = userRepository.findById(userId);
//		if (userById.isEmpty()) {
//			throw new RecordNotFoundException("Record not found for user Id : " + userId);
//		}
//
//		updateUserInDto.setEmail(updateUserInDto.getEmail().toLowerCase());
//		if (!userById.get().getEmail().equals(updateUserInDto.getEmail())) {
//			Optional<User> optionalUser = userRepository.findByEmail(updateUserInDto.getEmail());
//			if (optionalUser.isPresent()) {
//				throw new RecordAlreadyExistsException(
//						"Record already exist for email : " + updateUserInDto.getEmail());
//			}
//		}
//
//		User user = modelMapper.map(updateUserInDto, User.class);
//		user.setUserId(userId);
//		user.setPassword(userById.get().getPassword());
//		user = userRepository.save(user);
//
//		SignupOutDto signupOutDto = modelMapper.map(user, SignupOutDto.class);
//		return signupOutDto;
//	}
//
//	/**
//	 * @param userId
//	 * @return ResponseOutDto
//	 * @throws RecordNotFoundException
//	 */
//	public ResponseOutDto deleteUser(Long userId) throws RecordNotFoundException {
//		Optional<User> optionalUser = userRepository.findById(userId);
//		if (optionalUser.isEmpty()) {
//			throw new RecordNotFoundException("Record not found for user id : " + userId);
//		}
//
//		userRepository.delete(optionalUser.get());
//
//		ResponseOutDto responseOutDto = new ResponseOutDto();
//		responseOutDto.setMessage(ResponseConstants.USER_DELETED);
//		return responseOutDto;
//	}

}
