package com.ecommerce.payment.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class UpdateDefaultWalletInDtoTest {

    public UpdateDefaultWalletInDto buildUpdateDefaultWalletInDto(Long userId, Boolean isDefault) {
        UpdateDefaultWalletInDto updateDefaultWalletInDto = new UpdateDefaultWalletInDto();
        updateDefaultWalletInDto.setIsDefault(isDefault);
        updateDefaultWalletInDto.setUserId(userId);
        return updateDefaultWalletInDto;
    }

    @Test
    public void getterSetterTest() {

        Long userId = 1L;
        Boolean isDefault = true;

        UpdateDefaultWalletInDto updateDefaultWalletInDto = new UpdateDefaultWalletInDto();

        
        assertNull(updateDefaultWalletInDto.getUserId());
        updateDefaultWalletInDto.setUserId(userId);
        assertEquals(userId, updateDefaultWalletInDto.getUserId());
        
        assertNull(updateDefaultWalletInDto.getIsDefault());
        updateDefaultWalletInDto.setIsDefault(isDefault);
        assertEquals(isDefault, updateDefaultWalletInDto.getIsDefault());

        System.out.println(updateDefaultWalletInDto);
    }

    @Test
    public void toStringTest() {

        String updateDefaultWalletInDtoString = "UpdateDefaultWalletInDto(userId=1, isDefault=true)";

        Long userId = 1L;
        Boolean isDefault = true;

        UpdateDefaultWalletInDto updateDefaultWalletInDto = buildUpdateDefaultWalletInDto(userId, isDefault);
        assertEquals(updateDefaultWalletInDtoString, updateDefaultWalletInDto.toString());

    }

    @Test
    public void equalsAndHashCodeTest() {
        Long userId = 1L;
        Boolean isDefault = true;

        UpdateDefaultWalletInDto updateDefaultWalletInDto = buildUpdateDefaultWalletInDto(userId, isDefault);
        UpdateDefaultWalletInDto updateDefaultWalletInDto2 = buildUpdateDefaultWalletInDto(userId, isDefault);
        
        assertEquals(updateDefaultWalletInDto, updateDefaultWalletInDto);
        assertEquals(updateDefaultWalletInDto.hashCode(), updateDefaultWalletInDto.hashCode());

        assertNotEquals(updateDefaultWalletInDto, new Object());
        assertNotEquals(updateDefaultWalletInDto2, null);

        assertEquals(updateDefaultWalletInDto2, updateDefaultWalletInDto2);
        assertEquals(updateDefaultWalletInDto2.hashCode(), updateDefaultWalletInDto2.hashCode());

        updateDefaultWalletInDto = buildUpdateDefaultWalletInDto(2l, isDefault);
        assertNotEquals(updateDefaultWalletInDto2, updateDefaultWalletInDto);
        assertNotEquals(updateDefaultWalletInDto2.hashCode(), updateDefaultWalletInDto.hashCode());
        
        
        updateDefaultWalletInDto = buildUpdateDefaultWalletInDto(userId, false);
        assertNotEquals(updateDefaultWalletInDto2, updateDefaultWalletInDto);
        assertNotEquals(updateDefaultWalletInDto2.hashCode(), updateDefaultWalletInDto.hashCode());
        
    }

}
