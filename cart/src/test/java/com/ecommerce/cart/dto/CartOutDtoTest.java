package com.ecommerce.cart.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class CartOutDtoTest {

    public CartOutDto buildCart(String id, Long userId, List<CartProductsDetail> cartProductsDetail) {
        CartOutDto cartOutDto = new CartOutDto();
        cartOutDto.setCartProductsDetail(cartProductsDetail);
        cartOutDto.setId(id);
        cartOutDto.setUserId(userId);
        return cartOutDto;
    }

    @Test
    public void getterSetterTest() {

        String id = "id1";
        Long userId = 1L;
        List<CartProductsDetail> cartProductsDetails = new ArrayList<CartProductsDetail>();

        String productId = "id1";
        String name = "name";
        String description = "description";
        Integer quantity = 1;
        double price = 1.0;
        double discount = 1.0;
        String manufacturer = "manufacturer";
        CartProductsDetail cartProductDetail = buildCartProductsDetail(productId, name, description, quantity, price,
                discount, manufacturer);
        cartProductsDetails.add(cartProductDetail);

        CartOutDto cartOutDto = new CartOutDto();

        assertNull(cartOutDto.getCartProductsDetail());
        cartOutDto.setCartProductsDetail(cartProductsDetails);
        assertEquals(cartProductsDetails, cartOutDto.getCartProductsDetail());

        assertNull(cartOutDto.getId());
        cartOutDto.setId(productId);
        assertEquals(id, cartOutDto.getId());

        assertNull(cartOutDto.getUserId());
        cartOutDto.setUserId(userId);
        assertEquals(userId, cartOutDto.getUserId());

        System.out.println(cartOutDto);
    }

    @Test
    public void toStringTest() {

        String cartOutDtoString = "CartOutDto(id=id1, userId=1, cartProductsDetail=[CartProductsDetail(productId=id1, name=name, description=description, quantity=1, price=1.0, discount=1.0, manufacturer=manufacturer)])";

        Long userId = 1L;
        List<CartProductsDetail> cartProductsDetails = new ArrayList<CartProductsDetail>();

        String productId = "id1";
        String name = "name";
        String description = "description";
        Integer quantity = 1;
        double price = 1.0;
        double discount = 1.0;
        String manufacturer = "manufacturer";
        CartProductsDetail cartProductDetail = buildCartProductsDetail(productId, name, description, quantity, price,
                discount, manufacturer);
        cartProductsDetails.add(cartProductDetail);

        CartOutDto cartOutDto = buildCart(productId, userId, cartProductsDetails);

        assertEquals(cartOutDtoString, cartOutDto.toString());

    }

    @Test
    public void equalsAndHashCodeTest() {

        Long userId = 1L;
        List<CartProductsDetail> cartProductsDetails = new ArrayList<CartProductsDetail>();

        String productId = "id1";
        String name = "name";
        String description = "description";
        Integer quantity = 1;
        double price = 1.0;
        double discount = 1.0;
        String manufacturer = "manufacturer";
        CartProductsDetail cartProductDetail = buildCartProductsDetail(productId, name, description, quantity, price,
                discount, manufacturer);
        cartProductsDetails.add(cartProductDetail);

        CartOutDto cartOutDto = buildCart(productId, userId, cartProductsDetails);
        
        CartOutDto cartOutDto2 = buildCart(productId, userId, cartProductsDetails);

        assertEquals(cartOutDto, cartOutDto);
        assertEquals(cartOutDto.hashCode(), cartOutDto.hashCode());

        assertNotEquals(cartOutDto, new Object());
        assertNotEquals(cartOutDto2, null);

        assertEquals(cartOutDto2, cartOutDto2);
        assertEquals(cartOutDto2.hashCode(), cartOutDto2.hashCode());

        cartOutDto2 = buildCart("changed", userId, cartProductsDetails);
        assertNotEquals(cartOutDto2, cartOutDto);
        assertNotEquals(cartOutDto2.hashCode(), cartOutDto.hashCode());

        cartOutDto2 = buildCart(productId, 2l, cartProductsDetails);
        assertNotEquals(cartOutDto2, cartOutDto);
        assertNotEquals(cartOutDto2.hashCode(), cartOutDto.hashCode());
        
        
        List<CartProductsDetail> changedList = new ArrayList<CartProductsDetail>();
        CartProductsDetail cartProductDetail2 = buildCartProductsDetail("changed", name, description, quantity, price,
                discount, manufacturer);
        changedList.add(cartProductDetail2);
        cartOutDto2 = buildCart(productId, userId, changedList);
        assertNotEquals(cartOutDto2, cartOutDto);
        assertNotEquals(cartOutDto2.hashCode(), cartOutDto.hashCode());

    }

    public CartProductsDetail buildCartProductsDetail(String productId, String name, String description,
            Integer quantity, double price, double discount, String manufacturer) {
        CartProductsDetail cartOutDto = new CartProductsDetail();
        cartOutDto.setDescription(description);
        cartOutDto.setDiscount(discount);
        cartOutDto.setManufacturer(manufacturer);
        cartOutDto.setName(name);
        cartOutDto.setPrice(price);
        cartOutDto.setProductId(productId);
        cartOutDto.setQuantity(quantity);
        return cartOutDto;
    }

}
