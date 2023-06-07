package com.ecommerce.payment.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import com.ecommerce.payment.constants.ResponseConstants;
import com.ecommerce.payment.domain.Wallet;
import com.ecommerce.payment.dto.AddWalletInDto;
import com.ecommerce.payment.dto.ResponseOutDto;
import com.ecommerce.payment.dto.WalletOutDto;
import com.ecommerce.payment.exception.RecordAlreadyExistsException;
import com.ecommerce.payment.exception.RecordNotFoundException;
import com.ecommerce.payment.repository.PaymentRepository;
import com.ecommerce.payment.repository.UserClient;


public class PaymentServiceTest {


    /**
     * The model mapper object.
     */
    private ModelMapper modelMapper = new ModelMapper();

    /**
     * PaymentRepository
     */
    @Mock
    private PaymentRepository paymentRepository;

    /**
     * PaymentService
     */
    @InjectMocks
    private PaymentService paymentService;

    /**
     * UserClient
     */
    @Mock
    private UserClient userClient;

    @Test
    public void addWalletTest() throws RecordAlreadyExistsException, RecordNotFoundException  {

        MockitoAnnotations.openMocks(this);

        Long userId = 1L;
        String cardNo = "cardNo";
        double balance = 23.0;
        Boolean isDefault = true;

        AddWalletInDto addWalletInDto = buildAddWalletInDto(userId, cardNo, balance, isDefault);
        
        String walletId = "id1";
        Wallet wallet = buildWallet(walletId, userId, cardNo, balance, isDefault);

        Mockito.when(userClient.checkUserAndRole(userId, "buyer")).thenReturn("buyer");

        Optional<Wallet> optWallet = Optional.empty();
        Mockito.when(paymentRepository.findByUserIdAndCardNo(userId, cardNo)).thenReturn(optWallet);
        
        List<Wallet> wallets = new ArrayList<Wallet>();
        wallets.add(wallet);
        Mockito.when(paymentRepository.findByUserIdAndIsDefault(addWalletInDto.getUserId(), true)).thenReturn(wallets);

        ResponseOutDto response = new ResponseOutDto();
        response.setMessage(ResponseConstants.WALLET_ADDED);
        assertEquals(response, paymentService.addWallet(addWalletInDto));

        Mockito.when(paymentRepository.findByUserIdAndCardNo(userId, cardNo)).thenReturn(Optional.of(wallet));
        RecordAlreadyExistsException recordAlreadyExistsException = assertThrows(RecordAlreadyExistsException.class,
                () -> paymentService.addWallet(addWalletInDto));
        assertEquals(ResponseConstants.WALLET_AlREADY_EXISTS, recordAlreadyExistsException.getMessage());

    }
    
    @Test
    public void deleteWalletTest() throws RecordAlreadyExistsException, RecordNotFoundException  {

        MockitoAnnotations.openMocks(this);

        Long userId = 1L;
        String cardNo = "cardNo";
        double balance = 23.0;
        Boolean isDefault = true;

        String walletId = "id1";
        Wallet wallet = buildWallet(walletId, userId, cardNo, balance, isDefault);

        Mockito.when(userClient.checkUserAndRole(userId, "buyer")).thenReturn("buyer");

        Mockito.when(paymentRepository.findByIdAndUserId(walletId, userId)).thenReturn(Optional.of(wallet));

        ResponseOutDto response = new ResponseOutDto();
        response.setMessage(ResponseConstants.WALLET_DELETED);
        assertEquals(response, paymentService.deleteWallet(walletId, userId));

        Optional<Wallet> optWallet = Optional.empty();
        Mockito.when(paymentRepository.findByIdAndUserId(walletId, userId)).thenReturn(optWallet);
        RecordNotFoundException recordNotFoundException = assertThrows(RecordNotFoundException.class,
                () -> paymentService.deleteWallet(walletId, userId));
        assertEquals(ResponseConstants.WALLET_NOT_FOUND, recordNotFoundException.getMessage());

    }
    
    @Test
    public void updateDefaultWalletTest() throws RecordAlreadyExistsException, RecordNotFoundException  {

        MockitoAnnotations.openMocks(this);

        Long userId = 1L;
        String cardNo = "cardNo";
        double balance = 23.0;
        Boolean isDefault = true;

        String walletId = "id1";
        Wallet wallet = buildWallet(walletId, userId, cardNo, balance, isDefault);

        Mockito.when(userClient.checkUserAndRole(userId, "buyer")).thenReturn("buyer");
        Mockito.when(paymentRepository.findByIdAndUserId(walletId, userId)).thenReturn(Optional.of(wallet));

        List<Wallet> wallets = new ArrayList<Wallet>();
        wallets.add(wallet);
        Mockito.when(paymentRepository.findByUserIdAndIsDefault(userId, true)).thenReturn(wallets);
        
        ResponseOutDto response = new ResponseOutDto();
        response.setMessage(ResponseConstants.DEFAULT_WALLET_CHANGED);
        assertEquals(response, paymentService.updateDefaultWallet(userId, walletId));

        Optional<Wallet> optWallet = Optional.empty();
        Mockito.when(paymentRepository.findByIdAndUserId(walletId, userId)).thenReturn(optWallet);
        RecordNotFoundException recordNotFoundException = assertThrows(RecordNotFoundException.class,
                () -> paymentService.updateDefaultWallet(userId, walletId));
        assertEquals(ResponseConstants.WALLET_NOT_FOUND, recordNotFoundException.getMessage());

    }
    
    @Test
    public void getWalletsTest() throws RecordAlreadyExistsException, RecordNotFoundException  {

        MockitoAnnotations.openMocks(this);

        Long userId = 1L;
        String cardNo = "cardNo";
        double balance = 23.0;
        Boolean isDefault = true;

        String walletId = "id1";
        Wallet wallet = buildWallet(walletId, userId, cardNo, balance, isDefault);

        Mockito.when(userClient.checkUserAndRole(userId, "buyer")).thenReturn("buyer");
        
        List<Wallet> wallets = new ArrayList<Wallet>();
        Mockito.when(paymentRepository.findByUserId(userId)).thenReturn(wallets);
        RecordNotFoundException recordNotFoundException = assertThrows(RecordNotFoundException.class,
                () -> paymentService.getWallets(userId));
        assertEquals(ResponseConstants.NO_WALLET_FOUND, recordNotFoundException.getMessage());
        
        wallets.add(wallet);
        Mockito.when(paymentRepository.findByUserId(userId)).thenReturn(wallets);

        List<WalletOutDto> walletOutDtos = wallets.stream().map(wlt -> modelMapper.map(wlt, WalletOutDto.class))
                .collect(Collectors.toList());

        assertEquals(walletOutDtos, paymentService.getWallets(userId));

    }
    

    public WalletOutDto buildWalletOutDto(String id, Long userId, String cardNo, double balance, Boolean isDefault) {
        WalletOutDto walletOutDtoTest = new WalletOutDto();
        walletOutDtoTest.setBalance(balance);
        walletOutDtoTest.setCardNo(cardNo);
        walletOutDtoTest.setId(id);
        walletOutDtoTest.setIsDefault(isDefault);
        walletOutDtoTest.setUserId(userId);
        return walletOutDtoTest;
    }
    
    public AddWalletInDto buildAddWalletInDto(Long userId, String cardNo, double balance, Boolean isDefault) {
        AddWalletInDto addWalletInDto = new AddWalletInDto();
        addWalletInDto.setBalance(balance);
        addWalletInDto.setCardNo(cardNo);
        addWalletInDto.setIsDefault(isDefault);
        addWalletInDto.setUserId(userId);
        return addWalletInDto;
    }
    
    public Wallet buildWallet(String id, Long userId, String cardNo, double balance, Boolean isDefault) {
        Wallet walletTest = new Wallet();
        walletTest.setBalance(balance);
        walletTest.setCardNo(cardNo);
        walletTest.setId(id);
        walletTest.setIsDefault(isDefault);
        walletTest.setUserId(userId);
        return walletTest;
    }

}
