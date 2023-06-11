package com.ecommerce.order.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ecommerce.order.constants.ResponseConstants;
import com.ecommerce.order.domain.Product;
import com.ecommerce.order.dto.OrderDetailOutDto;
import com.ecommerce.order.dto.OrderInDto;
import com.ecommerce.order.dto.OrderOutDto;
import com.ecommerce.order.dto.ResponseOutDto;
import com.ecommerce.order.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
public class OrderControllerTest {

    /**
     * OrderController
     */
    @InjectMocks
    private OrderController orderController;

    /**
     * OrderService
     */
    @Mock
    private OrderService orderService;

    ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    public void placeOrderTest() throws Exception {

        MockitoAnnotations.openMocks(this);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();

        Long userId = 1l;
        String cartId = "cartId";

        OrderInDto orderInDtoTest = buildOrderInDto(userId, cartId);

        ResponseOutDto response = new ResponseOutDto();
        response.setMessage(ResponseConstants.ORDER_PLACED);

        String inputJSON = objectMapper.writeValueAsString(orderInDtoTest);
        when(orderService.placeOrder(orderInDtoTest)).thenReturn(response);
        MvcResult mvcResult = mockMvc
                .perform(post("/v1/order/place").contentType(MediaType.APPLICATION_JSON).content(inputJSON))
                .andReturn();
        int responseStatus = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), responseStatus);

    }

    @Test
    public void getOrdersTest() throws Exception {

        MockitoAnnotations.openMocks(this);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();

        Long userId = 1l;

        List<OrderOutDto> orderOutDtos = new ArrayList<OrderOutDto>();

        String id = "id1";
        double totalPrice = 1.0;
        String status = "placed";
        String cardNo = "cardNo";
        Instant createDt = Instant.parse("2023-06-08T12:29:55.137796300Z");

        OrderOutDto orderOutDtoTest = buildOrderOutDto(id, createDt, totalPrice, status, cardNo);
        orderOutDtos.add(orderOutDtoTest);

        when(orderService.getOrders(userId)).thenReturn(orderOutDtos);
        MvcResult mvcResult = mockMvc.perform(get("/v1/order/?userId=1").contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        int responseStatus = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), responseStatus);

    }

    @Test
    public void getOrderDetailTest() throws Exception {

        MockitoAnnotations.openMocks(this);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();

        String orderId = "ord1";
        Long userId = 1l;

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

        when(orderService.getOrderDetail(userId, orderId)).thenReturn(orderDetailOutDtoTest);
        MvcResult mvcResult = mockMvc.perform(get("/v1/order/id1?userId=1").contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        int responseStatus = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), responseStatus);

    }

    @Test
    public void deleteCartTest() throws Exception {

        MockitoAnnotations.openMocks(this);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();

        String orderId = "id1";
        Long userId = 1L;

        ResponseOutDto reponseOutDto = new ResponseOutDto();
        reponseOutDto.setMessage(ResponseConstants.ORDER_CANCELED);
        
        when(orderService.deleteOrder(userId, orderId)).thenReturn(reponseOutDto);
        MvcResult mvcResult = mockMvc.perform(delete("/v1/order/delete/ord1?userId=5")
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        int responseStatus = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), responseStatus);

    }

    public OrderInDto buildOrderInDto(Long userId, String cartId) {
        OrderInDto orderInDtoTest = new OrderInDto();
        orderInDtoTest.setCartId(cartId);
        orderInDtoTest.setUserId(userId);
        return orderInDtoTest;
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

    public OrderOutDto buildOrderOutDto(String id, Instant createDt, double totalPrice, String status, String cardNo) {
        OrderOutDto orderOutDtoTest = new OrderOutDto();
        orderOutDtoTest.setCardNo(cardNo);
        orderOutDtoTest.setCreateDt(createDt);
        orderOutDtoTest.setId(id);
        orderOutDtoTest.setStatus(status);
        orderOutDtoTest.setTotalPrice(totalPrice);
        return orderOutDtoTest;
    }

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

}
