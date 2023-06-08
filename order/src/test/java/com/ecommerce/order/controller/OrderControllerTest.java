package com.ecommerce.order.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ecommerce.order.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
public class OrderControllerTest {

    /**
     * The model mapper object.
     */
    private ModelMapper modelMapper = new ModelMapper();

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
    public void createProdcutTest() throws Exception {

        MockitoAnnotations.openMocks(this);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();

        String id = "id1";
        String name = "name";
        String description = "description";
        Integer quantity = 2;
        double price = 1.0;
        double discount = 1.0;
        String manufacturer = "manufacturer";
        Long userId = 1L;

        Product retProduct = buildProduct(id, userId, name, description, quantity, price, discount, manufacturer);

        CreateProductInDto createProductInDto = buildCreateProductInDto(name, description, quantity, price, discount,
                manufacturer);
        CreateProductOutDto productOutDto = modelMapper.map(retProduct, CreateProductOutDto.class);

        String inputJSON = objectMapper.writeValueAsString(createProductInDto);
        when(productService.createProduct(createProductInDto, userId)).thenReturn(productOutDto);
        MvcResult mvcResult = mockMvc
                .perform(post("/v1/product/create?userId=1").contentType(MediaType.APPLICATION_JSON).content(inputJSON))
                .andReturn();
        int responseStatus = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), responseStatus);

    }
}
