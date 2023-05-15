package com.ecommerce.order.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.order.constants.RestURLConstants;
import com.ecommerce.order.dto.OrderInDto;
import com.ecommerce.order.dto.OrderOutDto;
import com.ecommerce.order.dto.ResponseOutDto;
import com.ecommerce.order.service.OrderService;

@RestController
@RequestMapping(RestURLConstants.BASE_URL)
public class OrderController {

	/**
	 * The logger object.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

	/**
	 * Autowired orderService.
	 */
	@Autowired
	private OrderService orderService;

	/**
	 * @param orderInDto
	 * @return ResponseOutDto
	 * @throws Exception
	 */
	@PostMapping(path = "create")
	public ResponseEntity<ResponseOutDto> placeOrder(final @RequestBody OrderInDto orderInDto)
			throws Exception {
		LOGGER.info("Place order started.");
		ResponseOutDto responseOutDto = orderService.placeOrder(orderInDto);
		LOGGER.info("Place order completed. ");
		return ResponseEntity.status(HttpStatus.OK).body(responseOutDto);
	}

	/**
	 * @param userId
	 * @return List<OrderOutDto>
	 * @throws Exception
	 */
	@GetMapping(path = "")
	public ResponseEntity<List<OrderOutDto>> getOrders(
			final @RequestParam Integer userId) throws Exception {
		LOGGER.info("Get orders started for userId {}"+ userId);
		List<OrderOutDto> orders = orderService.getOrders(userId);
		LOGGER.info("Get orders started for userId {}"+ userId);
		return ResponseEntity.status(HttpStatus.OK).body(orders);
	}
//
//	/**
//	 * @param updateCartDetails
//	 * @param cartId
//	 * @return ResponseOutDto
//	 * @throws Exception
//	 */
//	@PutMapping(path = "update/{cartId}")
//	public ResponseEntity<ResponseOutDto> updateProducts(final @RequestBody CreateCartInDto updateCartDetails,
//			final @PathVariable String cartId) throws Exception {
//		LOGGER.info("Update cart started for cartId : {}", cartId);
//		ResponseOutDto responseOutDto = orderService.updateCart(updateCartDetails, cartId);
//		LOGGER.info("Update cart completed for cartId : {}", cartId);
//		return ResponseEntity.status(HttpStatus.OK).body(responseOutDto);
//	}
//
//	/**
//	 * @param cartId
//	 * @return ResponseOutDto
//	 * @throws Exception
//	 */
//	@DeleteMapping(path = "delete/{cartId}")
//	public ResponseEntity<ResponseOutDto> deleteCart(final @PathVariable String cartId) throws Exception {
//		LOGGER.info("Delete cart for cartId : {}", cartId);
//		ResponseOutDto responseOutDTO = orderService.deleteCart(cartId);
//		LOGGER.info("Delete cart completed for cartId : {}", cartId);
//		return ResponseEntity.status(HttpStatus.OK).body(responseOutDTO);
//	}
}
