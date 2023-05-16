package com.ecommerce.product.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

@Service
public class ProductService {

	/**
	 * The model mapper object.
	 */
	private ModelMapper modelMapper = new ModelMapper();

	/**
	 * ProductRepository
	 */
	@Autowired
	private ProductRepository productRepository;

	/**
	 * UserClient
	 */
	@Autowired
	private UserClient userClient;

	/**
	 * @param createProductInDto
	 * @param userId 
	 * @return CreateProductOutDto
	 * @throws InvalidDetailsException
	 * @throws RecordNotFoundException
	 */
	public CreateProductOutDto createProduct(CreateProductInDto createProductInDto, Long userId)
			throws InvalidDetailsException, RecordNotFoundException {

		checkUserExistOrNot(userId);

		if (Objects.isNull(createProductInDto.getName()) || createProductInDto.getQuantity() == 0
				|| createProductInDto.getPrice() == 0) {
			throw new InvalidDetailsException(ResponseConstants.INVALID_INPUT_REQUEST);
		}

		Product product = modelMapper.map(createProductInDto, Product.class);
		product.setUserId(userId);
		product = productRepository.save(product);

		CreateProductOutDto productOutDto = modelMapper.map(product, CreateProductOutDto.class);
		return productOutDto;
	}

	/**
	 * @param productId
	 * @param userId 
	 * @return ProductOutDto
	 * @throws InvalidDetailsException
	 * @throws RecordNotFoundException
	 */
	public GetProductOutDto getProduct(String productId) throws InvalidDetailsException, RecordNotFoundException {

		if (Objects.isNull(productId)) {
			throw new InvalidDetailsException(ResponseConstants.INVALID_INPUT_REQUEST);
		}

		Optional<Product> optProduct = productRepository.findById(productId);
		if (optProduct.isEmpty()) {
			throw new RecordNotFoundException(ResponseConstants.PRODUCTS_NOT_FOUND);
		}

		Product product = optProduct.get();
		GetProductOutDto getProductOutDto = modelMapper.map(product, GetProductOutDto.class);
		return getProductOutDto;
	}

	/**
	 * @param userId 
	 * @return List<GetProductOutDto>
	 * @throws RecordNotFoundException
	 */
	public List<GetProductOutDto> getAllProducts() throws RecordNotFoundException {

		List<Product> allProducts = productRepository.findAll();

		if (allProducts.size() == 0) {
			throw new RecordNotFoundException(ResponseConstants.NO_PRODUCTS_FOUND);
		}

		List<GetProductOutDto> getProductsList = allProducts.stream()
				.map(product -> modelMapper.map(product, GetProductOutDto.class)).collect(Collectors.toList());
		return getProductsList;
	}

	/**
	 * @param updateProductDto
	 * @param userId 
	 * @return ResponseOutDto
	 * @throws RecordNotFoundException
	 * @throws InvalidDetailsException
	 */
	public ResponseOutDto updateProducts(UpdateProductInDto updateProductDto, String productId, Long userId)
			throws RecordNotFoundException, InvalidDetailsException {

		checkUserExistOrNot(userId);
		
		if (Objects.isNull(updateProductDto) || Objects.isNull(productId)) {
			throw new InvalidDetailsException("Invalid product details!");
		}

		Optional<Product> optProduct = productRepository.findByIdAndUserId(productId, userId);
		if (optProduct.isEmpty()) {
			throw new RecordNotFoundException(ResponseConstants.PRODUCTS_NOT_FOUND);
		}
		
		Product product = modelMapper.map(updateProductDto, Product.class);
		product.setId(productId);
		product.setUserId(userId);
		product = productRepository.save(product);

		ResponseOutDto reponseOutDto = new ResponseOutDto();
		reponseOutDto.setMessage(ResponseConstants.PRODUCTS_UPDATED);
		return reponseOutDto;
	}

	/**
	 * @param productId
	 * @param userId 
	 * @return ResponseOutDto
	 * @throws InvalidDetailsException
	 * @throws RecordNotFoundException
	 */
	public ResponseOutDto deleteProduct(String productId, Long userId) throws InvalidDetailsException, RecordNotFoundException {

		checkUserExistOrNot(userId);
		
		Optional<Product> optProduct = productRepository.findByIdAndUserId(productId, userId);
		if (optProduct.isEmpty()) {
			throw new RecordNotFoundException(ResponseConstants.PRODUCTS_NOT_FOUND);
		}

		productRepository.deleteById(productId);

		ResponseOutDto reponseOutDto = new ResponseOutDto();
		reponseOutDto.setMessage(ResponseConstants.PRODUCTS_DELETED);
		return reponseOutDto;
	}

	/**
	 * @param userId
	 * @return
	 * @throws RecordNotFoundException
	 */
	public void checkUserExistOrNot(Long userId) throws RecordNotFoundException {
		if (userClient.getUserDetails(userId) != true) {
			throw new RecordNotFoundException(ResponseConstants.UNAUTHORIZED_USER);
		}
	}
	
}
