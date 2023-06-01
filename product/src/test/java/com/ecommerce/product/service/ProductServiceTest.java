package com.ecommerce.product.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringRunner;

import com.ecommerce.product.constants.ResponseConstants;
import com.ecommerce.product.domain.Product;
import com.ecommerce.product.dto.CreateProductInDto;
import com.ecommerce.product.dto.CreateProductOutDto;
import com.ecommerce.product.dto.GetProductOutDto;
import com.ecommerce.product.dto.ResponseOutDto;
import com.ecommerce.product.dto.UpdateProductInDto;
import com.ecommerce.product.exception.InvalidDetailsException;
import com.ecommerce.product.exception.RecordNotFoundException;
import com.ecommerce.product.repository.ProductRepository;
import com.ecommerce.product.repository.UserClient;

@RunWith(SpringRunner.class)
public class ProductServiceTest {

    /**
     * The model mapper object.
     */
    private ModelMapper modelMapper = new ModelMapper();

    /**
     * ProductRepository
     */
    @Mock
    private ProductRepository productRepository;

    /**
     * productService
     */
    @InjectMocks
    private ProductService productService;

    /**
     * UserClient
     */
    @Mock
    private UserClient userClient;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createProdcutTest() throws InvalidDetailsException, RecordNotFoundException {

        MockitoAnnotations.openMocks(this);

        String id = "id1";
        String name = "name";
        String description = "description";
        Integer quantity = 2;
        double price = 1.0;
        double discount = 1.0;
        String manufacturer = "manufacturer";
        Long userId = 1L;
        
        Product product = buildProduct(null, userId, name, description, quantity, price, discount, manufacturer);
        Product retProduct = buildProduct(id, userId, name, description, quantity, price, discount, manufacturer);

        CreateProductInDto createProductInDto = buildCreateProductInDto(name, description, quantity, price, discount,
                manufacturer);

        Mockito.when(productRepository.save(product)).thenReturn(retProduct);
        Mockito.when(userClient.checkUserAndRole(userId, "seller")).thenReturn("seller");

        CreateProductOutDto productOutDto = modelMapper.map(retProduct, CreateProductOutDto.class);

        assertEquals(productOutDto, productService.createProduct(createProductInDto, 1L));

        createProductInDto.setPrice(0);
        InvalidDetailsException invalidDetailsException = assertThrows(InvalidDetailsException.class,
                () -> productService.createProduct(createProductInDto, 1L));
        assertEquals(ResponseConstants.INVALID_INPUT_REQUEST, invalidDetailsException.getMessage());

    }

    @Test
    public void getProduct() throws InvalidDetailsException, RecordNotFoundException {

        MockitoAnnotations.openMocks(this);

        String id = "id1";
        String name = "name";
        String description = "description";
        Integer quantity = 2;
        double price = 1.0;
        double discount = 1.0;
        String manufacturer = "manufacturer";
        Long userId = 1L;

        Product retProduct = buildProduct(id, userId, name, description, quantity, price, discount, manufacturer);

        RecordNotFoundException recordNotFoundException = assertThrows(RecordNotFoundException.class,
                () -> productService.getProduct("id2"));
        assertEquals(ResponseConstants.PRODUCTS_NOT_FOUND, recordNotFoundException.getMessage());

        Optional<Product> optProduct = Optional.of(retProduct);
        Mockito.when(productRepository.findById(id)).thenReturn(optProduct);

        GetProductOutDto getProductOutDto = modelMapper.map(optProduct.get(), GetProductOutDto.class);
        assertEquals(getProductOutDto, productService.getProduct(id));
    }

    @Test
    public void getAllProducts() throws InvalidDetailsException, RecordNotFoundException {

        MockitoAnnotations.openMocks(this);

        String id = "id1";
        String name = "name";
        String description = "description";
        Integer quantity = 2;
        double price = 1.0;
        double discount = 1.0;
        String manufacturer = "manufacturer";
        Long userId = 1L;

        Product domainProduct = buildProduct(id, userId, name, description, quantity, price, discount, manufacturer);

        RecordNotFoundException recordNotFoundException = assertThrows(RecordNotFoundException.class,
                () -> productService.getAllProducts());
        assertEquals(ResponseConstants.NO_PRODUCTS_FOUND, recordNotFoundException.getMessage());

        List<Product> allProducts = new ArrayList<Product>();
        allProducts.add(domainProduct);

        List<GetProductOutDto> getProductsList = allProducts.stream()
                .map(product -> modelMapper.map(product, GetProductOutDto.class)).collect(Collectors.toList());

        Mockito.when(productRepository.findAll()).thenReturn(allProducts);
        assertEquals(getProductsList, productService.getAllProducts());
    }

    @Test
    public void updateProducts() throws InvalidDetailsException, RecordNotFoundException {

        MockitoAnnotations.openMocks(this);

        String id = "id1";
        String name = "name";
        String description = "description";
        Integer quantity = 2;
        double price = 1.0;
        double discount = 1.0;
        String manufacturer = "manufacturer";
        Long userId = 1L;

        Product product = buildProduct(null, userId, name, description, quantity, price, discount, manufacturer);
        Product retProduct = buildProduct(id, userId, name, description, quantity, price, discount, manufacturer);

        UpdateProductInDto updateProductDto = buildUpdateProductInDto(name, description, quantity, price, discount,
                manufacturer);
        
        Mockito.when(userClient.checkUserAndRole(userId, "seller")).thenReturn("seller");
        Mockito.when(productRepository.findByIdAndUserId(id, userId)).thenReturn(Optional.of(retProduct));

        Product product2 = modelMapper.map(updateProductDto, Product.class);
        product2.setUserId(userId);
        product2.setId(id);
       
        Mockito.when(productRepository.save(product)).thenReturn(product2);
        
        ResponseOutDto reponseOutDto = new ResponseOutDto();
        reponseOutDto.setMessage(ResponseConstants.PRODUCTS_UPDATED);
        assertEquals(reponseOutDto, productService.updateProducts(updateProductDto, id, userId));
        
        RecordNotFoundException recordNotFoundException = assertThrows(RecordNotFoundException.class,
                () -> productService.updateProducts(updateProductDto, "id2", userId));
        assertEquals(ResponseConstants.INVALID_USER_REQUEST, recordNotFoundException.getMessage());

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
