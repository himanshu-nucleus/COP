package com.ecommerce.product.controller;

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

import com.ecommerce.product.constants.ResponseConstants;
import com.ecommerce.product.domain.Product;
import com.ecommerce.product.dto.CreateProductInDto;
import com.ecommerce.product.dto.CreateProductOutDto;
import com.ecommerce.product.dto.GetProductOutDto;
import com.ecommerce.product.dto.ResponseOutDto;
import com.ecommerce.product.dto.UpdateProductInDto;
import com.ecommerce.product.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
public class ProductControllerTest {

    /**
     * The model mapper object.
     */
    private ModelMapper modelMapper = new ModelMapper();

    /**
     * ProductController
     */
    @InjectMocks
    private ProductController productController;

    /**
     * productService
     */
    @Mock
    private ProductService productService;

    ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
       
    }

    @Test
    public void createProdcutTest() throws Exception {

        MockitoAnnotations.openMocks(this);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(productController).build();

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
        MvcResult mvcResult = mockMvc.perform(post("/v1/product/create?userId=1")
                .contentType(MediaType.APPLICATION_JSON).content(inputJSON)).andReturn();
        int responseStatus = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), responseStatus);

    }
    
    @Test
    public void updateProductsTest() throws Exception {

        MockitoAnnotations.openMocks(this);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(productController).build();

        String id = "id1";
        String name = "name";
        String description = "description";
        Integer quantity = 2;
        double price = 1.0;
        double discount = 1.0;
        String manufacturer = "manufacturer";
        Long userId = 1L;

        UpdateProductInDto updateProductDto = buildUpdateProductInDto(name, description, quantity, price, discount,
                manufacturer);
        
        ResponseOutDto reponseOutDto = new ResponseOutDto();
        reponseOutDto.setMessage(ResponseConstants.PRODUCTS_UPDATED);
        
        String inputJSON = objectMapper.writeValueAsString(updateProductDto);
        when(productService.updateProducts(updateProductDto, id, userId)).thenReturn(reponseOutDto);
        MvcResult mvcResult = mockMvc.perform(put("/v1/product/update/id1?userId=7")
                .contentType(MediaType.APPLICATION_JSON).content(inputJSON)).andReturn();
        int responseStatus = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), responseStatus);

    }
    
    @Test
    public void getAllProductsTest() throws Exception {

        MockitoAnnotations.openMocks(this);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(productController).build();

        String id = "id1";
        String name = "name";
        String description = "description";
        Integer quantity = 2;
        double price = 1.0;
        double discount = 1.0;
        String manufacturer = "manufacturer";
        Long userId = 1L;
        
        List<Product> products = new ArrayList<Product>();
        Product retProduct = buildProduct(id, userId, name, description, quantity, price, discount, manufacturer);
        products.add(retProduct);
        
        List<GetProductOutDto> getProductsList = products.stream()
                .map(product -> modelMapper.map(product, GetProductOutDto.class)).collect(Collectors.toList());
        
        when(productService.getAllProducts()).thenReturn(getProductsList);

        MvcResult mvcResult = mockMvc.perform(get("/v1/product/")
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        int responseStatus = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), responseStatus);

    }
    
    @Test
    public void getProductTest() throws Exception {

        MockitoAnnotations.openMocks(this);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(productController).build();

        String id = "id1";
        String name = "name";
        String description = "description";
        Integer quantity = 2;
        double price = 1.0;
        double discount = 1.0;
        String manufacturer = "manufacturer";
        Long userId = 1L;

        Product retProduct = buildProduct(id, userId, name, description, quantity, price, discount, manufacturer);
        
        GetProductOutDto getProductOutDto = modelMapper.map(retProduct, GetProductOutDto.class);
        
        when(productService.getProduct(id)).thenReturn(getProductOutDto);
        MvcResult mvcResult = mockMvc.perform(get("/v1/product/id1")
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        int responseStatus = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), responseStatus);

    }
    
    @Test
    public void deleteProductTest() throws Exception {
        
        MockitoAnnotations.openMocks(this);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(productController).build();

        String id = "id1";
        Long userId = 1L;

        ResponseOutDto reponseOutDto = new ResponseOutDto();
        reponseOutDto.setMessage(ResponseConstants.PRODUCTS_DELETED);
        
        when(productService.deleteProduct(id, userId)).thenReturn(reponseOutDto);
        MvcResult mvcResult = mockMvc.perform(delete("/v1/product/delete/id1?userId=1")
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        int responseStatus = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), responseStatus);

    }

    public UpdateProductInDto buildUpdateProductInDto(String name, String description, Integer quantity, double price,
            double discount, String manufacturer) {
        UpdateProductInDto updateProductInDto = new UpdateProductInDto();
        updateProductInDto.setDescription(description);
        updateProductInDto.setDiscount(discount);
        updateProductInDto.setManufacturer(manufacturer);
        updateProductInDto.setName(name);
        updateProductInDto.setPrice(price);
        updateProductInDto.setQuantity(quantity);
        return updateProductInDto;
    }

    public CreateProductInDto buildCreateProductInDto(String name, String description, Integer quantity, double price,
            double discount, String manufacturer) {
        CreateProductInDto createProductInDto = new CreateProductInDto();
        createProductInDto.setDescription(description);
        createProductInDto.setDiscount(discount);
        createProductInDto.setManufacturer(manufacturer);
        createProductInDto.setName(name);
        createProductInDto.setPrice(price);
        createProductInDto.setQuantity(quantity);
        return createProductInDto;
    }

    public CreateProductOutDto buildCreateProductOutDto(String id, String name, String description, Integer quantity,
            double price, double discount, String manufacturer, Long userId) {
        CreateProductOutDto createProductOutDto = new CreateProductOutDto();
        createProductOutDto.setDescription(description);
        createProductOutDto.setDiscount(discount);
        createProductOutDto.setId(id);
        createProductOutDto.setManufacturer(manufacturer);
        createProductOutDto.setName(name);
        createProductOutDto.setPrice(price);
        createProductOutDto.setQuantity(quantity);
        createProductOutDto.setUserId(userId);
        return createProductOutDto;
    }

    public Product buildProduct(String id, Long userId, String name, String description, Integer quantity, double price,
            double discount, String manufacturer) {
        Product productTest = new Product();
        productTest.setDescription(description);
        productTest.setDiscount(discount);
        productTest.setId(id);
        productTest.setManufacturer(manufacturer);
        productTest.setName(name);
        productTest.setPrice(price);
        productTest.setQuantity(quantity);
        productTest.setUserId(userId);
        return productTest;
    }
}
