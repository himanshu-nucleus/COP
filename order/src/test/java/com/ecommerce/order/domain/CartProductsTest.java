package com.ecommerce.order.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class CartProductsTest {

    public CartProducts buildCartProducts(String productId, Integer quantity) {
        CartProducts cartProductsTest = new CartProducts();
        cartProductsTest.setQuantity(quantity);
        cartProductsTest.setProductId(productId);
        return cartProductsTest;
    }

    @Test
    public void getterSetterTest() {

        String productId = "id1";
        Integer quantity = 2;

        CartProducts cartProducts = new CartProducts();

        assertNull(cartProducts.getProductId());
        cartProducts.setProductId(productId);
        assertEquals(productId, cartProducts.getProductId());

        assertNull(cartProducts.getQuantity());
        cartProducts.setQuantity(quantity);
        assertEquals(quantity, cartProducts.getQuantity());
        
        System.out.println(cartProducts);
    }

    @Test
    public void toStringTest() {

        String cartProductsTestString = "CartProducts(productId=id1, quantity=2)"; 
        String productId = "id1";
        Integer quantity = 2;

        CartProducts cartProductsTest = buildCartProducts(productId, quantity);
        assertEquals(cartProductsTestString, cartProductsTest.toString());

    }

    @Test
    public void equalsAndHashCodeTest() {

        String productId = "id1";
        Integer quantity = 2;

        CartProducts cartProductsTest = buildCartProducts(productId, quantity);
        
        CartProducts cartProductsTest2  = buildCartProducts(productId, quantity);

        assertEquals(cartProductsTest, cartProductsTest);
        assertEquals(cartProductsTest.hashCode(), cartProductsTest.hashCode());

        assertNotEquals(cartProductsTest, new Object());
        assertNotEquals(cartProductsTest2, null);

        assertEquals(cartProductsTest2, cartProductsTest2);
        assertEquals(cartProductsTest2.hashCode(), cartProductsTest2.hashCode());

        cartProductsTest = buildCartProducts("chaged", quantity);
        assertNotEquals(cartProductsTest2, cartProductsTest);
        assertNotEquals(cartProductsTest2.hashCode(), cartProductsTest.hashCode());
        
        cartProductsTest = buildCartProducts(productId, 3);
        assertNotEquals(cartProductsTest2, cartProductsTest);
        assertNotEquals(cartProductsTest2.hashCode(), cartProductsTest.hashCode());
    }

}
