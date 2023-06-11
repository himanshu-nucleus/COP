package com.ecommerce.order.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class OrderTest {

    Instant instant = Instant.now();
    
    public Order buildOrder(String id, Long userId, Instant createDt, double totalAmount, String status, String cardNo,
            List<Product> products) {
        Order orderTest = new Order();
        orderTest.setCardNo(cardNo);
        orderTest.setCreateDt(createDt);
        orderTest.setId(id);
        orderTest.setProducts(products);
        orderTest.setStatus(status);
        orderTest.setTotalAmount(totalAmount);
        orderTest.setUserId(userId);
        return orderTest;
    }

    @Test
    public void getterSetterTest() {

        String id = "id1";
        Long userId = 1L;
        Instant createDt = instant;
        double totalAmount = 2.0;
        String status = "placed";
        String cardNo = "54qw";
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

        
        Order order = new Order();

        assertNull(order.getId());
        order.setId(id);
        assertEquals(id, order.getId());
        
        assertNull(order.getUserId());
        order.setUserId(userId);
        assertEquals(userId, order.getUserId());

        assertNull(order.getCardNo());
        order.setCardNo(cardNo);
        assertEquals(cardNo, order.getCardNo());

        assertNull(order.getCreateDt());
        order.setCreateDt(createDt);
        assertEquals(createDt, order.getCreateDt());

        assertNull(order.getProducts());
        order.setProducts(products);
        assertEquals(products, order.getProducts());

        assertNull(order.getStatus());
        order.setStatus(status);
        assertEquals(status, order.getStatus());

        assertEquals(0, order.getTotalAmount());
        order.setTotalAmount(totalAmount);
        assertEquals(totalAmount, order.getTotalAmount());
        
       
        System.out.println(order);
    }

    @Test
    public void toStringTest() {

        String orderTestString = "Order(id=id1, userId=1, createDt=2023-06-08T11:54:54.096543500Z, totalAmount=2.0, status=placed, cardNo=54qw, products=[Product(id=id1, name=name, description=description, quantity=2, price=1.0, discount=1.0, manufacturer=manufacturer)])";

        String id = "id1";
        Long userId = 1L;
        Instant createDt = Instant.parse("2023-06-08T11:54:54.096543500Z");
        double totalAmount = 2.0;
        String status = "placed";
        String cardNo = "54qw";
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

        
        Order orderTest = buildOrder(id, userId, createDt, totalAmount, status, cardNo, products);
        assertEquals(orderTestString, orderTest.toString());

    }

    @Test
    public void equalsAndHashCodeTest() {

        String id = "id1";
        Long userId = 1L;
        Instant createDt = instant;
        double totalAmount = 2.0;
        String status = "placed";
        String cardNo = "ssw";
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

        
        Order orderTest = buildOrder(id, userId, createDt, totalAmount, status, cardNo, products);
        Order orderTest2 = buildOrder(id, userId, createDt, totalAmount, status, cardNo, products);

        assertEquals(orderTest, orderTest);
        assertEquals(orderTest.hashCode(), orderTest.hashCode());

        assertNotEquals(orderTest, new Object());
        assertNotEquals(orderTest2, null);

        assertEquals(orderTest2, orderTest2);
        assertEquals(orderTest2.hashCode(), orderTest2.hashCode());

        orderTest = buildOrder("changed", userId, createDt, totalAmount, status, cardNo, products);
        assertNotEquals(orderTest2, orderTest);
        assertNotEquals(orderTest2.hashCode(), orderTest.hashCode());
        
        orderTest = buildOrder(id, 3l, createDt, totalAmount, status, cardNo, products);
        assertNotEquals(orderTest2, orderTest);
        assertNotEquals(orderTest2.hashCode(), orderTest.hashCode());
        
        Instant newInstant = Instant.MAX;
        orderTest = buildOrder(id, userId, newInstant, totalAmount, status, cardNo, products);
        assertNotEquals(orderTest2, orderTest);
        assertNotEquals(orderTest2.hashCode(), orderTest.hashCode());
        
        orderTest = buildOrder(id, userId, createDt, 4.0, status, cardNo, products);
        assertNotEquals(orderTest2, orderTest);
        assertNotEquals(orderTest2.hashCode(), orderTest.hashCode());
        
        orderTest = buildOrder(id, userId, createDt, totalAmount, "canceled", cardNo, products);
        assertNotEquals(orderTest2, orderTest);
        assertNotEquals(orderTest2.hashCode(), orderTest.hashCode());
        
        orderTest = buildOrder(id, userId, createDt, totalAmount, status, "changed", products);
        assertNotEquals(orderTest2, orderTest);
        assertNotEquals(orderTest2.hashCode(), orderTest.hashCode());

        List<Product> newProducts = new ArrayList<Product>();
        Product productNew = buildProduct("neww", "changed", "changed", 2, 2.0, 2.0, "changed");
        newProducts.add(productNew);
        orderTest = buildOrder(id, userId, createDt, totalAmount, status, cardNo, newProducts);
        assertNotEquals(orderTest2, orderTest);
        assertNotEquals(orderTest2.hashCode(), orderTest.hashCode());
        
    
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
