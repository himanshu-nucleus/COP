package com.ecommerce.product.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import com.ecommerce.product.domain.Product;
import com.ecommerce.product.dto.CreateProductInDto;
import com.ecommerce.product.dto.CreateProductOutDto;
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
