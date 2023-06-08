package com.ecommerce.order.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.ecommerce.order.domain.Product;

public class OrderDetailOutDtoTest {

    Instant instant = Instant.now();

    public OrderDetailOutDto buildOrderDetailOutDto(String id, Instant createDt, double totalAmount, String status,
            String cardNo, List<Product> products) {
        OrderDetailOutDto orderDetailOutDtoTest = new OrderDetailOutDto();
        orderDetailOutDtoTest.setCardNo(cardNo);
        orderDetailOutDtoTest.setCreateDt(createDt);
        orderDetailOutDtoTest.setId(id);
        orderDetailOutDtoTest.setStatus(status);
        orderDetailOutDtoTest.setTotalAmount(totalAmount);
        orderDetailOutDtoTest.setProducts(products);

        return orderDetailOutDtoTest;
    }

    @Test
    public void getterSetterTest() {

        String id = "id1";
        double totalAmount = 1.0;
        String status = "placed";
        String cardNo = "cardNo";
        Instant createDt = instant;

        List<Product> products = new ArrayList<Product>();
        String productId = "id1";
        String name = "name";
        String description = "description";
        Integer quantity = 2;
        double price = 1.0;
        double discount = 1.0;
        String manufacturer = "manufacturer";
        Product productTest = buildProduct(productId, name, description, quantity, price, discount, manufacturer);
        products.add(productTest);

        OrderDetailOutDto orderDetailOutDto = new OrderDetailOutDto();

        assertNull(orderDetailOutDto.getId());
        orderDetailOutDto.setId(id);
        assertEquals(id, orderDetailOutDto.getId());

        assertNull(orderDetailOutDto.getCardNo());
        orderDetailOutDto.setCardNo(cardNo);
        assertEquals(cardNo, orderDetailOutDto.getCardNo());

        assertNull(orderDetailOutDto.getCreateDt());
        orderDetailOutDto.setCreateDt(createDt);
        assertEquals(createDt, orderDetailOutDto.getCreateDt());

        assertNull(orderDetailOutDto.getStatus());
        orderDetailOutDto.setStatus(status);
        assertEquals(status, orderDetailOutDto.getStatus());

        assertEquals(0, orderDetailOutDto.getTotalAmount());
        orderDetailOutDto.setTotalAmount(totalAmount);
        assertEquals(totalAmount, orderDetailOutDto.getTotalAmount());

        assertNull(orderDetailOutDto.getProducts());
        orderDetailOutDto.setProducts(products);
        assertEquals(products, orderDetailOutDto.getProducts());

        System.out.println(orderDetailOutDto);
    }

    @Test
    public void toStringTest() {

        String orderDetailOutDtoTestString = "OrderDetailOutDto(id=id1, createDt=2023-06-08T15:09:38.953487600Z, totalAmount=1.0, status=placed, cardNo=cardNo, products=[Product(id=id1, name=name, description=description, quantity=2, price=1.0, discount=1.0, manufacturer=manufacturer)])";

        String id = "id1";
        double totalAmount = 1.0;
        String status = "placed";
        String cardNo = "cardNo";
        Instant createDt = Instant.parse("2023-06-08T15:09:38.953487600Z");

        List<Product> products = new ArrayList<Product>();
        String productId = "id1";
        String name = "name";
        String description = "description";
        Integer quantity = 2;
        double price = 1.0;
        double discount = 1.0;
        String manufacturer = "manufacturer";
        Product productTest = buildProduct(productId, name, description, quantity, price, discount, manufacturer);
        products.add(productTest);

        OrderDetailOutDto orderDetailOutDtoTest = buildOrderDetailOutDto(id, createDt, totalAmount, status, cardNo,
                products);
        assertEquals(orderDetailOutDtoTestString, orderDetailOutDtoTest.toString());

    }

    @Test
    public void equalsAndHashCodeTest() {
        String id = "id1";
        double totalAmount = 1.0;
        String status = "placed";
        String cardNo = "cardNo";
        Instant createDt = Instant.parse("2023-06-08T15:09:38.953487600Z");

        List<Product> products = new ArrayList<Product>();
        String productId = "id1";
        String name = "name";
        String description = "description";
        Integer quantity = 2;
        double price = 1.0;
        double discount = 1.0;
        String manufacturer = "manufacturer";
        Product productTest = buildProduct(productId, name, description, quantity, price, discount, manufacturer);
        products.add(productTest);

        OrderDetailOutDto orderDetailOutDtoTest = buildOrderDetailOutDto(id, createDt, totalAmount, status, cardNo,
                products);
        OrderDetailOutDto orderDetailOutDtoTest2 = buildOrderDetailOutDto(id, createDt, totalAmount, status, cardNo,
                products);

        assertEquals(orderDetailOutDtoTest, orderDetailOutDtoTest);
        assertEquals(orderDetailOutDtoTest.hashCode(), orderDetailOutDtoTest.hashCode());

        assertNotEquals(orderDetailOutDtoTest, new Object());
        assertNotEquals(orderDetailOutDtoTest2, null);

        assertEquals(orderDetailOutDtoTest2, orderDetailOutDtoTest2);
        assertEquals(orderDetailOutDtoTest2.hashCode(), orderDetailOutDtoTest2.hashCode());

        orderDetailOutDtoTest = buildOrderDetailOutDto("changed", createDt, totalAmount, status, cardNo, products);
        assertNotEquals(orderDetailOutDtoTest2, orderDetailOutDtoTest);
        assertNotEquals(orderDetailOutDtoTest2.hashCode(), orderDetailOutDtoTest.hashCode());

        Instant ins = Instant.now();
        orderDetailOutDtoTest = buildOrderDetailOutDto(id, ins, totalAmount, status, cardNo, products);
        assertNotEquals(orderDetailOutDtoTest2, orderDetailOutDtoTest);
        assertNotEquals(orderDetailOutDtoTest2.hashCode(), orderDetailOutDtoTest.hashCode());

        orderDetailOutDtoTest = buildOrderDetailOutDto(id, createDt, 5.0, status, cardNo, products);
        assertNotEquals(orderDetailOutDtoTest2, orderDetailOutDtoTest);
        assertNotEquals(orderDetailOutDtoTest2.hashCode(), orderDetailOutDtoTest.hashCode());
        
        orderDetailOutDtoTest = buildOrderDetailOutDto(id, createDt, totalAmount, "canceled", cardNo, products);
        assertNotEquals(orderDetailOutDtoTest2, orderDetailOutDtoTest);
        assertNotEquals(orderDetailOutDtoTest2.hashCode(), orderDetailOutDtoTest.hashCode());
        
        orderDetailOutDtoTest = buildOrderDetailOutDto(id, createDt, totalAmount, status, "changed", products);
        assertNotEquals(orderDetailOutDtoTest2, orderDetailOutDtoTest);
        assertNotEquals(orderDetailOutDtoTest2.hashCode(), orderDetailOutDtoTest.hashCode());
        
        List<Product> newProducts = new ArrayList<Product>();
        Product productNew = buildProduct("neww", "changed", "changed", 2, 2.0, 2.0, "changed");
        newProducts.add(productNew);
        orderDetailOutDtoTest = buildOrderDetailOutDto(id, createDt, totalAmount, status, cardNo, newProducts);
        assertNotEquals(orderDetailOutDtoTest2, orderDetailOutDtoTest);
        assertNotEquals(orderDetailOutDtoTest2.hashCode(), orderDetailOutDtoTest.hashCode());

    }

    public Product buildProduct(String id, String name, String description, Integer quantity, double price,
            double discount, String manufacturer) {
        Product productTest = new Product();
        productTest.setDescription(description);
        productTest.setDiscount(discount);
        productTest.setId(id);
        productTest.setManufacturer(manufacturer);
        productTest.setName(name);
        productTest.setPrice(price);
        productTest.setQuantity(quantity);
        return productTest;
    }

}
