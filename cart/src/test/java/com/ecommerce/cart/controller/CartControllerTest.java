package com.ecommerce.cart.controller;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.util.ArrayList;
import java.util.List;

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

import com.ecommerce.cart.constants.ResponseConstants;
import com.ecommerce.cart.dto.CartOutDto;
import com.ecommerce.cart.dto.CartProductsDetail;
import com.ecommerce.cart.dto.CreateCartInDto;
import com.ecommerce.cart.dto.DeleteCartProductInDto;
import com.ecommerce.cart.dto.ResponseOutDto;
import com.ecommerce.cart.service.CartService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
public class CartControllerTest {

    /**
     * CartController
     */
    @InjectMocks
    private CartController cartController;
    
    ObjectMapper objectMapper = new ObjectMapper();

    /**
     * CartService
     */
    @Mock
    private CartService cartService;


    @Test
    public void createCartTest() throws Exception {

        MockitoAnnotations.openMocks(this);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(cartController).build();

        Long userId = 1L;
        String productId = "id1";
        Integer quantity = 1;

        CreateCartInDto createCartInDto = buildCreateCartInDto(userId, productId, quantity);

        ResponseOutDto response = new ResponseOutDto();
        response.setMessage(ResponseConstants.CART_CREATED);

        String inputJSON = objectMapper.writeValueAsString(createCartInDto);
        when(cartService.createCart(createCartInDto)).thenReturn(response);

        MvcResult mvcResult = mockMvc.perform(post("/v1/cart/create")
                .contentType(MediaType.APPLICATION_JSON).content(inputJSON)).andReturn();
        int responseStatus = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), responseStatus);

    }
    
    @Test
    public void getCartTest() throws Exception {

        MockitoAnnotations.openMocks(this);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(cartController).build();

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

        when(cartService.getCart(userId)).thenReturn(cartOutDto);

        MvcResult mvcResult = mockMvc.perform(get("/v1/cart/?userId=1")
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        int responseStatus = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), responseStatus);

    }
   
    @Test
    public void updateCartTest() throws Exception {

        MockitoAnnotations.openMocks(this);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(cartController).build();

        Long userId = 1L;
        String productId = "id1";
        Integer quantity = 1;

        CreateCartInDto updateCartDetails = buildCreateCartInDto(userId, productId, quantity);

        ResponseOutDto response = new ResponseOutDto();
        response.setMessage(ResponseConstants.CART_UPDATED);
        
        String inputJSON = objectMapper.writeValueAsString(updateCartDetails);
        when(cartService.updateCart(updateCartDetails, "2d")).thenReturn(response);

        MvcResult mvcResult = mockMvc.perform(put("/v1/cart/update/2d")
                .contentType(MediaType.APPLICATION_JSON).content(inputJSON)).andReturn();
        int responseStatus = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), responseStatus);

    }
   
    
    @Test
    public void deleteCartProductTest() throws Exception {

        MockitoAnnotations.openMocks(this);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(cartController).build();


        Long userId = 1L;
        String productId = "id1";
        String cartId = "id1";

        DeleteCartProductInDto deleteCartProductInDto = buildDeleteCartProductInDto(cartId, userId, productId) ;

        ResponseOutDto response = new ResponseOutDto();
        response.setMessage(ResponseConstants.CART_PRODUCT_DELETED);

        String inputJSON = objectMapper.writeValueAsString(deleteCartProductInDto);
        when(cartService.deleteCartProduct(deleteCartProductInDto)).thenReturn(response);

        MvcResult mvcResult = mockMvc.perform(delete("/v1/cart/delete/product")
                .contentType(MediaType.APPLICATION_JSON).content(inputJSON)).andReturn();
        int responseStatus = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), responseStatus);

    }
    
    public DeleteCartProductInDto buildDeleteCartProductInDto(String cartId, Long userId, String productId) {
        DeleteCartProductInDto deleteCartProductInDto = new DeleteCartProductInDto();
        deleteCartProductInDto.setProductId(productId);
        deleteCartProductInDto.setCartId(cartId);
        deleteCartProductInDto.setUserId(userId);
        return deleteCartProductInDto;
    }
   
   

    public CreateCartInDto buildCreateCartInDto(Long userId, String productId, Integer quantity) {
        CreateCartInDto createCartInDto = new CreateCartInDto();
        createCartInDto.setProductId(productId);
        createCartInDto.setQuantity(quantity);
        createCartInDto.setUserId(userId);
        return createCartInDto;
    }
    
    public CartOutDto buildCart(String id, Long userId, List<CartProductsDetail> cartProductsDetail) {
        CartOutDto cartOutDto = new CartOutDto();
        cartOutDto.setCartProductsDetail(cartProductsDetail);
        cartOutDto.setId(id);
        cartOutDto.setUserId(userId);
        return cartOutDto;
    }
    
    public CartProductsDetail buildCartProductsDetail(String productId, String name, String description,
            Integer quantity, double price, double discount, String manufacturer) {
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
    
    
}
