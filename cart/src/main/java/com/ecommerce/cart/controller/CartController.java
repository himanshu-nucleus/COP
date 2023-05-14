package com.ecommerce.cart.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.cart.constants.RestURLConstants;
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
//
//	/**
//	 * @param updateProductDto
//	 * @param productId
//	 * @return ResponseOutDto
//	 * @throws Exception
//	 */
//	@PutMapping(path = "update/{productId}")
//	public ResponseEntity<ResponseOutDto> updateProducts(final @RequestBody UpdateProductInDto updateProductDto,
//			final @PathVariable String productId) throws Exception {
//		LOGGER.info("Update product started.");
//		ResponseOutDto responseOutDto = productService.updateProducts(updateProductDto, productId);
//		LOGGER.info("Update product completed.");
//		return ResponseEntity.status(HttpStatus.OK).body(responseOutDto);
//	}
//
//	/**
//	 * @param fileId
//	 * @param version
//	 * @param tableId
//	 * @return List<GetProductOutDto>
//	 * @throws Exception
//	 */
//	@GetMapping(path = "")
//	public ResponseEntity<List<GetProductOutDto>> getAllProducts() throws Exception {
//		LOGGER.info("Get all products started.");
//		List<GetProductOutDto> productOutDtoList = productService.getAllProducts();
//		LOGGER.info("Get all products completed.");
//		return ResponseEntity.status(HttpStatus.OK).body(productOutDtoList);
//	}
//
//	/**
//	 * @param productId
//	 * @return GetProductOutDto
//	 * @throws Exception
//	 */
//	@GetMapping(path = "{productId}")
//	public ResponseEntity<GetProductOutDto> getProduct(final @PathVariable String productId) throws Exception {
//		LOGGER.info("Get product details started for product id : {}", productId);
//		GetProductOutDto getProductOutDto = productService.getProduct(productId);
//		LOGGER.info("Get product details completed for product id : {}", productId);
//		return ResponseEntity.status(HttpStatus.OK).body(getProductOutDto);
//	}
//
//	/**
//	 * @param productId
//	 * @return ResponseOutDto
//	 * @throws Exception
//	 */
//	@DeleteMapping(path = "delete/{productId}")
//	public ResponseEntity<ResponseOutDto> deleteProduct(final @PathVariable String productId) throws Exception {
//		LOGGER.info("Delete prodcut for id: {}", productId);
//		ResponseOutDto responseOutDTO = productService.deleteProduct(productId);
//		LOGGER.info("Delete product for completed for file id: {} ", productId);
//		return ResponseEntity.status(HttpStatus.OK).body(responseOutDTO);
//	}
}
