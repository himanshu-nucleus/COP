package com.ecommerce.payment.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class AddWalletInDtoTest {

    public AddWalletInDto buildAddWalletInDto(Long userId, String cardNo, double balance, Boolean isDefault) {
        AddWalletInDto addWalletInDto = new AddWalletInDto();
        addWalletInDto.setBalance(balance);
        addWalletInDto.setCardNo(cardNo);
        addWalletInDto.setIsDefault(isDefault);
        addWalletInDto.setUserId(userId);
        return addWalletInDto;
    }

    @Test
    public void getterSetterTest() {

        Long userId = 1L;
        String cardNo = "cardNo";
        double balance = 23.0;
        Boolean isDefault = true;

        AddWalletInDto addWalletInDto = new AddWalletInDto();

        
        assertNull(addWalletInDto.getUserId());
        addWalletInDto.setUserId(userId);
        assertEquals(userId, addWalletInDto.getUserId());

        assertEquals(0.0, addWalletInDto.getBalance());
        addWalletInDto.setBalance(balance);
        assertEquals(balance, addWalletInDto.getBalance());
        
        assertNull(addWalletInDto.getCardNo());
        addWalletInDto.setCardNo(cardNo);
        assertEquals(cardNo, addWalletInDto.getCardNo());
        
        assertNull(addWalletInDto.getIsDefault());
        addWalletInDto.setIsDefault(isDefault);
        assertEquals(isDefault, addWalletInDto.getIsDefault());

        System.out.println(addWalletInDto);
    }

    @Test
    public void toStringTest() {

        String addWalletInDtoString = "AddWalletInDto(userId=1, cardNo=cardNo, balance=23.0, isDefault=true)";

        Long userId = 1L;
        String cardNo = "cardNo";
        double balance = 23.0;
        Boolean isDefault = true;

        AddWalletInDto addWalletInDto = buildAddWalletInDto(userId, cardNo, balance, isDefault);
        assertEquals(addWalletInDtoString, addWalletInDto.toString());

    }

    @Test
    public void equalsAndHashCodeTest() {
        Long userId = 1L;
        String cardNo = "cardNo";
        double balance = 23.0;
        Boolean isDefault = true;

        AddWalletInDto addWalletInDto = buildAddWalletInDto(userId, cardNo, balance, isDefault);
        AddWalletInDto addWalletInDto2 = buildAddWalletInDto(userId, cardNo, balance, isDefault);
        
        assertEquals(addWalletInDto, addWalletInDto);
        assertEquals(addWalletInDto.hashCode(), addWalletInDto.hashCode());

        assertNotEquals(addWalletInDto, new Object());
        assertNotEquals(addWalletInDto2, null);

        assertEquals(addWalletInDto2, addWalletInDto2);
        assertEquals(addWalletInDto2.hashCode(), addWalletInDto2.hashCode());

        addWalletInDto = buildAddWalletInDto(2l, cardNo, balance, isDefault);
        assertNotEquals(addWalletInDto2, addWalletInDto);
        assertNotEquals(addWalletInDto2.hashCode(), addWalletInDto.hashCode());
        
        addWalletInDto = buildAddWalletInDto(userId, "changed", balance, isDefault);
        assertNotEquals(addWalletInDto2, addWalletInDto);
        assertNotEquals(addWalletInDto2.hashCode(), addWalletInDto.hashCode());
        
        addWalletInDto = buildAddWalletInDto(userId, cardNo, 62.0, isDefault);
        assertNotEquals(addWalletInDto2, addWalletInDto);
        assertNotEquals(addWalletInDto2.hashCode(), addWalletInDto.hashCode());
        
        addWalletInDto = buildAddWalletInDto(userId, cardNo, balance, false);
        assertNotEquals(addWalletInDto2, addWalletInDto);
        assertNotEquals(addWalletInDto2.hashCode(), addWalletInDto.hashCode());
        
    }

}
