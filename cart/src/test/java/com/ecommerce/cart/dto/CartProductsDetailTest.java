package com.ecommerce.cart.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class CartProductsDetailTest {

    public CartProductsDetail buildCart(String productId, String name, String description, Integer quantity,
            double price, double discount, String manufacturer) {
        CartProductsDetail cartProductDetailTest = new CartProductsDetail();
        cartProductDetailTest.setDescription(description);
        cartProductDetailTest.setDiscount(discount);
        cartProductDetailTest.setManufacturer(manufacturer);
        cartProductDetailTest.setName(name);
        cartProductDetailTest.setPrice(price);
        cartProductDetailTest.setProductId(productId);
        cartProductDetailTest.setQuantity(quantity);
        return cartProductDetailTest;
    }

    @Test
    public void getterSetterTest() {

        String productId = "id1";
        String name = "name";
        String description = "description";
        Integer quantity = 1;
        double price = 1.0;
        double discount = 1.0;
        String manufacturer = "manufacturer";

        CartProductsDetail cartProductsDetail = new CartProductsDetail();

        assertNull(cartProductsDetail.getDescription());
        cartProductsDetail.setDescription(description);
        assertEquals(description, cartProductsDetail.getDescription());

        assertEquals(0.0, cartProductsDetail.getDiscount());
        cartProductsDetail.setDiscount(discount);
        assertEquals(discount, cartProductsDetail.getDiscount());

        assertNull(cartProductsDetail.getManufacturer());
        cartProductsDetail.setManufacturer(manufacturer);
        assertEquals(manufacturer, cartProductsDetail.getManufacturer());

        assertNull(cartProductsDetail.getName());
        cartProductsDetail.setName(name);
        assertEquals(name, cartProductsDetail.getName());

        assertEquals(0.0, cartProductsDetail.getPrice());
        cartProductsDetail.setPrice(price);
        assertEquals(price, cartProductsDetail.getPrice());

        assertNull(cartProductsDetail.getProductId());
        cartProductsDetail.setProductId(productId);
        assertEquals(productId, cartProductsDetail.getProductId());

        assertNull(cartProductsDetail.getQuantity());
        cartProductsDetail.setQuantity(quantity);
        assertEquals(quantity, cartProductsDetail.getQuantity());

        System.out.println(cartProductsDetail);
    }

    @Test
    public void toStringTest() {

        String cartTestString = "CartProductsDetail(productId=id1, name=name, description=description, quantity=1, price=1.0, discount=1.0, manufacturer=manufacturer)";
        String productId = "id1";
        String name = "name";
        String description = "description";
        Integer quantity = 1;
        double price = 1.0;
        double discount = 1.0;
        String manufacturer = "manufacturer";

        CartProductsDetail cartProductDetailTest = buildCart(productId, name, description, quantity, price, discount,
                manufacturer);

        assertEquals(cartTestString, cartProductDetailTest.toString());

    }

    @Test
    public void equalsAndHashCodeTest() {

        String productId = "id1";
        String name = "name";
        String description = "description";
        Integer quantity = 1;
        double price = 1.0;
        double discount = 1.0;
        String manufacturer = "manufacturer";

        CartProductsDetail cartProductDetailTest = buildCart(productId, name, description, quantity, price, discount,
                manufacturer);
        CartProductsDetail cartProductDetailTest2 = buildCart(productId, name, description, quantity, price, discount,
                manufacturer);

        assertEquals(cartProductDetailTest, cartProductDetailTest);
        assertEquals(cartProductDetailTest.hashCode(), cartProductDetailTest.hashCode());

        assertNotEquals(cartProductDetailTest, new Object());
        assertNotEquals(cartProductDetailTest2, null);

        assertEquals(cartProductDetailTest2, cartProductDetailTest2);
        assertEquals(cartProductDetailTest2.hashCode(), cartProductDetailTest2.hashCode());

        cartProductDetailTest = buildCart("changed", name, description, quantity, price, discount, manufacturer);

        cartProductDetailTest = buildCart(productId, "changed", description, quantity, price, discount, manufacturer);

        cartProductDetailTest = buildCart(productId, name, "changed", quantity, price, discount, manufacturer);

        cartProductDetailTest = buildCart(productId, name, description, 2, price, discount, manufacturer);

        cartProductDetailTest = buildCart(productId, name, description, quantity, 3.0, discount, manufacturer);

        cartProductDetailTest = buildCart(productId, name, description, quantity, price, 4.0, manufacturer);

        cartProductDetailTest = buildCart(productId, name, description, quantity, price, discount, "changed");

    }

}
