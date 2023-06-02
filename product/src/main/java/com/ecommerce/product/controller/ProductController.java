package com.ecommerce.product.controller;

import java.util.List;

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

import com.ecommerce.product.constants.RestURLConstants;
import com.ecommerce.product.dto.CreateProductInDto;
import com.ecommerce.product.dto.CreateProductOutDto;
import com.ecommerce.product.dto.GetProductOutDto;
import com.ecommerce.product.dto.ResponseOutDto;
import com.ecommerce.product.dto.UpdateProductInDto;
import com.ecommerce.product.service.ProductService;

@RestController
@RequestMapping(RestURLConstants.BASE_URL)
public class ProductController {

	/**
	 * The logger object.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

	/**
	 * Autowired ProductService.
	 */
	@Autowired
	private ProductService productService;
	
	/**
	 * @param fileId
	 * @return CreateProductOutDto
	 * @throws Exception
	 */
	@PostMapping(path = "create")
	public ResponseEntity<CreateProductOutDto> createProduct(final @RequestBody CreateProductInDto createProductInDto,
			final @RequestParam Long userId)
			throws Exception {
		LOGGER.info("Create product started.");
		CreateProductOutDto createProductOutDto = productService.createProduct(createProductInDto, userId);
		LOGGER.info("Create product completed. ");
		return ResponseEntity.status(HttpStatus.OK).body(createProductOutDto);
	}

	/**
	 * @param updateProductDto
	 * @param productId
	 * @return ResponseOutDto
	 * @throws Exception
	 */
	@PutMapping(path = "update/{productId}")
	public ResponseEntity<ResponseOutDto> updateProducts(final @RequestBody UpdateProductInDto updateProductDto,
			final @PathVariable String productId,
			final @RequestParam Long userId) throws Exception {
		LOGGER.info("Update product started.");
		ResponseOutDto responseOutDto = productService.updateProducts(updateProductDto, productId, userId);
		LOGGER.info("Update product completed.");
		return ResponseEntity.status(HttpStatus.OK).body(responseOutDto);
	}

	/**
	 * @param fileId
	 * @param version
	 * @param tableId
	 * @return List<GetProductOutDto>
	 * @throws Exception
	 */
	@GetMapping(path = "")
	public ResponseEntity<List<GetProductOutDto>> getAllProducts() throws Exception {
		LOGGER.info("Get all products started.");
		List<GetProductOutDto> productOutDtoList = productService.getAllProducts();
		LOGGER.info("Get all products completed.");
		return ResponseEntity.status(HttpStatus.OK).body(productOutDtoList);
	}
	
	/**
	 * @param productId
	 * @return GetProductOutDto
	 * @throws Exception
	 */
	@GetMapping(path = "{productId}")
	public ResponseEntity<GetProductOutDto> getProduct(final @PathVariable String productId) throws Exception {
		LOGGER.info("Get product details started for product id : {}", productId);
		GetProductOutDto getProductOutDto = productService.getProduct(productId);
		LOGGER.info("Get product details completed for product id : {}", productId);
		return ResponseEntity.status(HttpStatus.OK).body(getProductOutDto);
	}
	
	/**
	 * @param productId
	 * @return ResponseOutDto
	 * @throws Exception
	 */
	@DeleteMapping(path = "delete/{productId}")
	public ResponseEntity<ResponseOutDto> deleteProduct(final @PathVariable String productId,
			final @RequestParam Long userId) throws Exception {
		LOGGER.info("Delete prodcut for id: {}", productId);
		ResponseOutDto responseOutDTO = productService.deleteProduct(productId, userId);
		LOGGER.info("Delete product for completed for file id: {} ", productId);
		return ResponseEntity.status(HttpStatus.OK).body(responseOutDTO);
	}
}
