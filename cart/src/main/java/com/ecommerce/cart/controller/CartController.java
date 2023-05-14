package com.ecommerce.cart.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.cart.constants.RestURLConstants;
import com.ecommerce.cart.dto.CartOutDto;
import com.ecommerce.cart.dto.CreateCartInDto;
import com.ecommerce.cart.dto.ResponseOutDto;
import com.ecommerce.cart.service.CartService;

@RestController
@RequestMapping(RestURLConstants.BASE_URL)
public class CartController {

	/**
	 * The logger object.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(CartController.class);

	/**
	 * Autowired cartService.
	 */
	@Autowired
	private CartService cartService;

	/**
	 * @param fileId
	 * @return ResponseOutDto
	 * @throws Exception
	 */
	@PostMapping(path = "create")
	public ResponseEntity<ResponseOutDto> createProduct(final @RequestBody CreateCartInDto createCartInDto)
			throws Exception {
		LOGGER.info("Create cart started.");
		ResponseOutDto responseOutDto = cartService.createCart(createCartInDto);
		LOGGER.info("Create cart completed. ");
		return ResponseEntity.status(HttpStatus.OK).body(responseOutDto);
	}
	
	/**
	 * @param userId
	 * @return CartOutDto
	 * @throws Exception
	 */
	@GetMapping(path = "")
	public ResponseEntity<CartOutDto> getCart(
			final @RequestParam Integer userId) throws Exception {
		LOGGER.info("Get cart started for userId {}"+ userId);
		CartOutDto cartOutDto = cartService.getCart(userId);
		LOGGER.info("Get cart started for userId {}"+ userId);
		return ResponseEntity.status(HttpStatus.OK).body(cartOutDto);
	}

	/**
	 * @param updateCartDetails
	 * @param cartId
	 * @return ResponseOutDto
	 * @throws Exception
	 */
	@PutMapping(path = "update/{cartId}")
	public ResponseEntity<ResponseOutDto> updateProducts(final @RequestBody CreateCartInDto updateCartDetails,
			final @PathVariable String cartId) throws Exception {
		LOGGER.info("Update cart started for cartId : {}", cartId);
		ResponseOutDto responseOutDto = cartService.updateCart(updateCartDetails, cartId);
		LOGGER.info("Update cart completed for cartId : {}", cartId);
		return ResponseEntity.status(HttpStatus.OK).body(responseOutDto);
	}

	/**
	 * @param cartId
	 * @return ResponseOutDto
	 * @throws Exception
	 */
	@DeleteMapping(path = "delete/{cartId}")
	public ResponseEntity<ResponseOutDto> deleteCart(final @PathVariable String cartId) throws Exception {
		LOGGER.info("Delete cart for cartId : {}", cartId);
		ResponseOutDto responseOutDTO = cartService.deleteCart(cartId);
		LOGGER.info("Delete cart completed for cartId : {}", cartId);
		return ResponseEntity.status(HttpStatus.OK).body(responseOutDTO);
	}
}
