package com.ecommerce.payment.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class WalletOutDtoTest {

    public WalletOutDto buildWalletOutDto(String id, Long userId, String cardNo, double balance, Boolean isDefault) {
        WalletOutDto walletOutDtoTest = new WalletOutDto();
        walletOutDtoTest.setBalance(balance);
        walletOutDtoTest.setCardNo(cardNo);
        walletOutDtoTest.setId(id);
        walletOutDtoTest.setIsDefault(isDefault);
        walletOutDtoTest.setUserId(userId);
        return walletOutDtoTest;
    }

    @Test
    public void getterSetterTest() {

        String id = "id1";
        Long userId = 1L;
        String cardNo = "cardNo";
        double balance = 23.0;
        Boolean isDefault = true;

        WalletOutDto walletOutDto = new WalletOutDto();

        assertNull(walletOutDto.getId());
        walletOutDto.setId(id);
        assertEquals(id, walletOutDto.getId());
        
        assertNull(walletOutDto.getUserId());
        walletOutDto.setUserId(userId);
        assertEquals(userId, walletOutDto.getUserId());

        assertEquals(0.0, walletOutDto.getBalance());
        walletOutDto.setBalance(balance);
        assertEquals(balance, walletOutDto.getBalance());
        
        assertNull(walletOutDto.getCardNo());
        walletOutDto.setCardNo(cardNo);
        assertEquals(cardNo, walletOutDto.getCardNo());
        
        assertNull(walletOutDto.getIsDefault());
        walletOutDto.setIsDefault(isDefault);
        assertEquals(isDefault, walletOutDto.getIsDefault());

        System.out.println(walletOutDto);
    }

    @Test
    public void toStringTest() {

        String walletOutDtoTestString = "WalletOutDto(id=id1, userId=1, cardNo=cardNo, balance=23.0, isDefault=true)";
        
        String id = "id1";
        Long userId = 1L;
        String cardNo = "cardNo";
        double balance = 23.0;
        Boolean isDefault = true;

        WalletOutDto walletOutDtoTest = buildWalletOutDto(id, userId, cardNo, balance, isDefault);
        assertEquals(walletOutDtoTestString, walletOutDtoTest.toString());

    }

    @Test
    public void equalsAndHashCodeTest() {

        String id = "id1";
        Long userId = 1L;
        String cardNo = "cardNo";
        double balance = 23.0;
        Boolean isDefault = true;

        WalletOutDto walletOutDtoTest = buildWalletOutDto(id, userId, cardNo, balance, isDefault);
        WalletOutDto walletOutDtoTest2 = buildWalletOutDto(id, userId, cardNo, balance, isDefault);

        assertEquals(walletOutDtoTest, walletOutDtoTest);
        assertEquals(walletOutDtoTest.hashCode(), walletOutDtoTest.hashCode());

        assertNotEquals(walletOutDtoTest, new Object());
        assertNotEquals(walletOutDtoTest2, null);

        assertEquals(walletOutDtoTest2, walletOutDtoTest2);
        assertEquals(walletOutDtoTest2.hashCode(), walletOutDtoTest2.hashCode());

        walletOutDtoTest = buildWalletOutDto("changed", userId, cardNo, balance, isDefault);
        assertNotEquals(walletOutDtoTest2, walletOutDtoTest);
        assertNotEquals(walletOutDtoTest2.hashCode(), walletOutDtoTest.hashCode());
        
        walletOutDtoTest = buildWalletOutDto(id, 5l, cardNo, balance, isDefault);
        assertNotEquals(walletOutDtoTest2, walletOutDtoTest);
        assertNotEquals(walletOutDtoTest2.hashCode(), walletOutDtoTest.hashCode());
        
        walletOutDtoTest = buildWalletOutDto(id, userId, "changed", balance, isDefault);
        assertNotEquals(walletOutDtoTest2, walletOutDtoTest);
        assertNotEquals(walletOutDtoTest2.hashCode(), walletOutDtoTest.hashCode());
        
        walletOutDtoTest = buildWalletOutDto(id, userId, cardNo, 3.0, isDefault);
        assertNotEquals(walletOutDtoTest2, walletOutDtoTest);
        assertNotEquals(walletOutDtoTest2.hashCode(), walletOutDtoTest.hashCode());
        
        walletOutDtoTest = buildWalletOutDto(id, userId, cardNo, balance, false);
        assertNotEquals(walletOutDtoTest2, walletOutDtoTest);
        assertNotEquals(walletOutDtoTest2.hashCode(), walletOutDtoTest.hashCode());
    }
    

}
