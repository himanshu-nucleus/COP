package com.ecommerce.payment.controller;

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

import com.ecommerce.payment.constants.RestURLConstants;
import com.ecommerce.payment.dto.AddWalletInDto;
import com.ecommerce.payment.dto.ResponseOutDto;
import com.ecommerce.payment.dto.WalletOutDto;
import com.ecommerce.payment.service.PaymentService;

@RestController
@RequestMapping(RestURLConstants.BASE_URL)
public class PaymentController {

	/**
	 * The logger object.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentController.class);

	/**
	 * Autowired ProductService.
	 */
	@Autowired
	private PaymentService paymentService;

	/**
	 * @param addWalletInDto
	 * @return ResponseOutDto
	 * @throws Exception
	 */
	@PostMapping(path = "add")
	public ResponseEntity<ResponseOutDto> addWallet(final @RequestBody AddWalletInDto addWalletInDto) throws Exception {
		LOGGER.info("Add wallet started.");
		ResponseOutDto response = paymentService.addWallet(addWalletInDto);
		LOGGER.info("Add wallet completed. ");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	/**
	 * @param walletId
	 * @param updateDefaultWalletInDto
	 * @return ResponseOutDto
	 * @throws Exception
	 */
	@PutMapping(path = "update/default/wallet/{walletId}")
	public ResponseEntity<ResponseOutDto> updateDefaultWallet(final @PathVariable String walletId,
			final @RequestParam Long userId) throws Exception {
		LOGGER.info("Update default wallet started for id : {}", walletId);
		ResponseOutDto responseOutDto = paymentService.updateDefaultWallet(userId, walletId);
		LOGGER.info("Update default wallet started for id : {}", walletId);
		return ResponseEntity.status(HttpStatus.OK).body(responseOutDto);
	}

	/**
	 * @param userId
	 * @return List<WalletOutDto>
	 * @throws Exception
	 */
	@GetMapping(path = "wallet/user/{userId}")
	public ResponseEntity<List<WalletOutDto>> getWallets(final @PathVariable Long userId) throws Exception {
		LOGGER.info("Get wallets started for userId : {}", userId);
		List<WalletOutDto> walletOutDtos = paymentService.getWallets(userId);
		LOGGER.info("Get wallets completed for userId : {}", userId);
		return ResponseEntity.status(HttpStatus.OK).body(walletOutDtos);
	}

	/**
	 * @param walletId
	 * @return ResponseOutDto
	 * @throws Exception
	 */
	@DeleteMapping(path = "delete/wallet/{walletId}")
	public ResponseEntity<ResponseOutDto> deleteWallet(final @PathVariable String walletId,
			final @RequestParam Long userId) throws Exception {
		LOGGER.info("Delete wallet started for id: {}", walletId);
		ResponseOutDto responseOutDTO = paymentService.deleteWallet(walletId, userId);
		LOGGER.info("Delete wallet completed for id: {}", walletId);
		return ResponseEntity.status(HttpStatus.OK).body(responseOutDTO);
	}
}
