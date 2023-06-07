package com.ecommerce.payment.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ecommerce.payment.constants.ResponseConstants;
import com.ecommerce.payment.dto.AddWalletInDto;
import com.ecommerce.payment.dto.ResponseOutDto;
import com.ecommerce.payment.service.PaymentService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PaymentControllerTest {

    /**
     * PaymentController
     */
    @InjectMocks
    private PaymentController payementController;

    /**
     * CartService
     */
    @Mock
    private PaymentService paymentService;
    
    /**
     * ObjectMapper
     */
    ObjectMapper objectMapper = new ObjectMapper();


    @Test
    public void addWalletTest() throws Exception {

        MockitoAnnotations.openMocks(this);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(payementController).build();

        Long userId = 1L;
        String cardNo = "cardNo";
        double balance = 23.0;
        Boolean isDefault = true;

        AddWalletInDto addWalletInDto = buildAddWalletInDto(userId, cardNo, balance, isDefault);

        ResponseOutDto response = new ResponseOutDto();
        response.setMessage(ResponseConstants.WALLET_ADDED);

        String inputJSON = objectMapper.writeValueAsString(addWalletInDto);
        when(paymentService.addWallet(addWalletInDto)).thenReturn(response);

        MvcResult mvcResult = mockMvc.perform(post("/v1/payment/add")
                .contentType(MediaType.APPLICATION_JSON).content(inputJSON)).andReturn();
        int responseStatus = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), responseStatus);

    }
    
    public AddWalletInDto buildAddWalletInDto(Long userId, String cardNo, double balance, Boolean isDefault) {
        AddWalletInDto addWalletInDto = new AddWalletInDto();
        addWalletInDto.setBalance(balance);
        addWalletInDto.setCardNo(cardNo);
        addWalletInDto.setIsDefault(isDefault);
        addWalletInDto.setUserId(userId);
        return addWalletInDto;
    }
}
