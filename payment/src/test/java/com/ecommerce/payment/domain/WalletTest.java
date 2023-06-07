package com.ecommerce.payment.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class WalletTest {

    public Wallet buildWallet(String id, Long userId, String cardNo, double balance, Boolean isDefault) {
        Wallet walletTest = new Wallet();
        walletTest.setBalance(balance);
        walletTest.setCardNo(cardNo);
        walletTest.setId(id);
        walletTest.setIsDefault(isDefault);
        walletTest.setUserId(userId);
        return walletTest;
    }

    @Test
    public void getterSetterTest() {

        String id = "id1";
        Long userId = 1L;
        String cardNo = "cardNo";
        double balance = 23.0;
        Boolean isDefault = true;

        Wallet wallet = new Wallet();

        assertNull(wallet.getId());
        wallet.setId(id);
        assertEquals(id, wallet.getId());
        
        assertNull(wallet.getUserId());
        wallet.setUserId(userId);
        assertEquals(userId, wallet.getUserId());

        assertEquals(0.0, wallet.getBalance());
        wallet.setBalance(balance);
        assertEquals(balance, wallet.getBalance());
        
        assertNull(wallet.getCardNo());
        wallet.setCardNo(cardNo);
        assertEquals(cardNo, wallet.getCardNo());
        
        assertNull(wallet.getIsDefault());
        wallet.setIsDefault(isDefault);
        assertEquals(isDefault, wallet.getIsDefault());

        System.out.println(wallet);
    }

    @Test
    public void toStringTest() {

        String walletTestString = "Wallet(id=id1, userId=1, cardNo=cardNo, balance=23.0, isDefault=true)";
        
        String id = "id1";
        Long userId = 1L;
        String cardNo = "cardNo";
        double balance = 23.0;
        Boolean isDefault = true;

        Wallet walletTest = buildWallet(id, userId, cardNo, balance, isDefault);
        assertEquals(walletTestString, walletTest.toString());

    }

    @Test
    public void equalsAndHashCodeTest() {

        String id = "id1";
        Long userId = 1L;
        String cardNo = "cardNo";
        double balance = 23.0;
        Boolean isDefault = true;

        Wallet walletTest = buildWallet(id, userId, cardNo, balance, isDefault);
        Wallet walletTest2 = buildWallet(id, userId, cardNo, balance, isDefault);

        assertEquals(walletTest, walletTest);
        assertEquals(walletTest.hashCode(), walletTest.hashCode());

        assertNotEquals(walletTest, new Object());
        assertNotEquals(walletTest2, null);

        assertEquals(walletTest2, walletTest2);
        assertEquals(walletTest2.hashCode(), walletTest2.hashCode());

        walletTest = buildWallet("changed", userId, cardNo, balance, isDefault);
        assertNotEquals(walletTest2, walletTest);
        assertNotEquals(walletTest2.hashCode(), walletTest.hashCode());
        
        walletTest = buildWallet(id, 5l, cardNo, balance, isDefault);
        assertNotEquals(walletTest2, walletTest);
        assertNotEquals(walletTest2.hashCode(), walletTest.hashCode());
        
        walletTest = buildWallet(id, userId, "changed", balance, isDefault);
        assertNotEquals(walletTest2, walletTest);
        assertNotEquals(walletTest2.hashCode(), walletTest.hashCode());
        
        walletTest = buildWallet(id, userId, cardNo, 3.0, isDefault);
        assertNotEquals(walletTest2, walletTest);
        assertNotEquals(walletTest2.hashCode(), walletTest.hashCode());
        
        walletTest = buildWallet(id, userId, cardNo, balance, false);
        assertNotEquals(walletTest2, walletTest);
        assertNotEquals(walletTest2.hashCode(), walletTest.hashCode());
    }
    

}
