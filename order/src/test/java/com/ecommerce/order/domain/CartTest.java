package com.ecommerce.order.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class CartTest {

    public Cart buildCart(String id, Long userId, List<CartProducts> cartProducts) {
        Cart cartTest = new Cart();
        cartTest.setCartProducts(cartProducts);
        cartTest.setId(id);
        cartTest.setUserId(userId);
        return cartTest;
    }
    
    public CartProducts buildCartProducts(String productId, Integer quantity) {
        CartProducts cartProductsTest = new CartProducts();
        cartProductsTest.setQuantity(quantity);
        cartProductsTest.setProductId(productId);
        return cartProductsTest;
    }

    @Test
    public void getterSetterTest() {

        String id = "id1";
        Long userId = 1L;
        List<CartProducts> cartProducts = new ArrayList<CartProducts>();
        CartProducts cartProducts1 = buildCartProducts(id, 2);
        cartProducts.add(cartProducts1);

        Cart cart = new Cart();

        assertNull(cart.getId());
        cart.setId(id);
        assertEquals(id, cart.getId());
        
        assertNull(cart.getUserId());
        cart.setUserId(userId);
        assertEquals(userId, cart.getUserId());

        assertNull(cart.getCartProducts());
        cart.setCartProducts(cartProducts);
        assertEquals(cartProducts, cart.getCartProducts());

        System.out.println(cart);
    }

    @Test
    public void toStringTest() {

        String cartTestString = "Cart(id=id1, userId=1, cartProducts=[CartProducts(productId=id1, quantity=2)])";
        
        String id = "id1";
        Long userId = 1L;
        List<CartProducts> cartProducts = new ArrayList<CartProducts>();
        CartProducts cartProducts1 = buildCartProducts(id, 2);
        cartProducts.add(cartProducts1);

        Cart cartTest = buildCart(id, userId, cartProducts);
        assertEquals(cartTestString, cartTest.toString());

    }

    @Test
    public void equalsAndHashCodeTest() {

        
        String id = "id1";
        Long userId = 1L;
        List<CartProducts> cartProducts = new ArrayList<CartProducts>();
        CartProducts cartProducts1 = buildCartProducts(id, 2);
        cartProducts.add(cartProducts1);

        Cart cartTest = buildCart(id, userId, cartProducts);
        Cart cartTest2 = buildCart(id, userId, cartProducts);

        assertEquals(cartTest, cartTest);
        assertEquals(cartTest.hashCode(), cartTest.hashCode());

        assertNotEquals(cartTest, new Object());
        assertNotEquals(cartTest2, null);

        assertEquals(cartTest2, cartTest2);
        assertEquals(cartTest2.hashCode(), cartTest2.hashCode());

        cartTest = buildCart("changed", userId, cartProducts);
        assertNotEquals(cartTest2, cartTest);
        assertNotEquals(cartTest2.hashCode(), cartTest.hashCode());
        
        cartTest = buildCart(id, 2L, cartProducts);
        assertNotEquals(cartTest2, cartTest);
        assertNotEquals(cartTest2.hashCode(), cartTest.hashCode());
        
        List<CartProducts> cartNew = new ArrayList<CartProducts>();
        CartProducts cartProduct2 = buildCartProducts("changed", 3);
        cartNew.add(cartProduct2);
        cartTest = buildCart(id, userId, cartNew);
        assertNotEquals(cartTest2, cartTest);
        assertNotEquals(cartTest2.hashCode(), cartTest.hashCode());

    }
    

}
