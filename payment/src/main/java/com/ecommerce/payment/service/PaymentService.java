package com.ecommerce.payment.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.payment.constants.ResponseConstants;
import com.ecommerce.payment.domain.Wallet;
import com.ecommerce.payment.dto.AddWalletInDto;
import com.ecommerce.payment.dto.ResponseOutDto;
import com.ecommerce.payment.dto.UpdateDefaultWalletInDto;
import com.ecommerce.payment.dto.WalletOutDto;
import com.ecommerce.payment.exception.RecordAlreadyExistsException;
import com.ecommerce.payment.exception.RecordNotFoundException;
import com.ecommerce.payment.repository.PaymentRepository;

@Service
public class PaymentService {

	/**
	 * The model mapper object.
	 */
	private ModelMapper modelMapper = new ModelMapper();

	/**
	 * ProductRepository
	 */
	@Autowired
	private PaymentRepository paymentRepository;

	/**
	 * @param addWalletInDto
	 * @return ResponseOutDto
	 * @throws RecordAlreadyExistsException
	 */
	public ResponseOutDto addWallet(AddWalletInDto addWalletInDto) throws RecordAlreadyExistsException {

		Optional<Wallet> optWallet = paymentRepository.findByUserIdAndCardNo(addWalletInDto.getUserId(),
				addWalletInDto.getCardNo());
		if (optWallet.isPresent()) {
			throw new RecordAlreadyExistsException(ResponseConstants.WALLET_AlREADY_EXISTS);
		}

		if (addWalletInDto.getIsDefault() == true) {
			List<Wallet> wallets = paymentRepository.findByUserIdAndIsDefault(addWalletInDto.getUserId(), true);
			for (Wallet wallet : wallets) {
				wallet.setIsDefault(false);
			}
			paymentRepository.saveAll(wallets);
		}

		Wallet wallet = modelMapper.map(addWalletInDto, Wallet.class);
		wallet.setId(null);
		paymentRepository.save(wallet);

		ResponseOutDto response = new ResponseOutDto();
		response.setMessage(ResponseConstants.WALLET_ADDED);

		return response;
	}

	/**
	 * @param walletId
	 * @return ResponseOutDto
	 * @throws RecordNotFoundException
	 */
	public ResponseOutDto deleteWallet(String walletId) throws RecordNotFoundException {

		Optional<Wallet> optWallet = paymentRepository.findById(walletId);
		if (optWallet.isEmpty()) {
			throw new RecordNotFoundException(ResponseConstants.WALLET_NOT_FOUND);
		}

		paymentRepository.deleteById(walletId);

		ResponseOutDto response = new ResponseOutDto();
		response.setMessage(ResponseConstants.WALLET_DELETED);

		return response;
	}

	/**
	 * @param updateDefaultWalletInDto
	 * @param walletId
	 * @return ResponseOutDto
	 * @throws RecordNotFoundException
	 */
	public ResponseOutDto updateDefaultWallet(UpdateDefaultWalletInDto updateDefaultWalletInDto, String walletId)
			throws RecordNotFoundException {

		Optional<Wallet> optWallet = paymentRepository.findById(walletId);
		if (optWallet.isEmpty()) {
			throw new RecordNotFoundException(ResponseConstants.WALLET_NOT_FOUND);
		}

		List<Wallet> wallets = paymentRepository.findByUserIdAndIsDefault(updateDefaultWalletInDto.getUserId(), true);
		for (Wallet wallet : wallets) {
			wallet.setIsDefault(false);
		}
		paymentRepository.saveAll(wallets);

		optWallet.get().setIsDefault(updateDefaultWalletInDto.getIsDefault());
		paymentRepository.save(optWallet.get());

		ResponseOutDto response = new ResponseOutDto();
		response.setMessage(ResponseConstants.DEFAULT_WALLET_CHANGED);

		return response;
	}

	/**
	 * @param userId
	 * @return List<WalletOutDto>
	 * @throws RecordNotFoundException
	 */
	public List<WalletOutDto> getWallets(String userId) throws RecordNotFoundException {

		List<Wallet> wallets = paymentRepository.findByUserId(userId);
		if (wallets.size() == 0) {
			throw new RecordNotFoundException(ResponseConstants.NO_WALLET_FOUND);
		}

		List<WalletOutDto> walletOutDtos = wallets.stream().map(wallet -> modelMapper.map(wallet, WalletOutDto.class))
				.collect(Collectors.toList());
		return walletOutDtos;
	}

}
