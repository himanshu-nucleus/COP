package com.ecommerce.product.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.product.domain.Product;
import com.ecommerce.product.dto.CreateProductInDto;
import com.ecommerce.product.dto.CreateProductOutDto;
import com.ecommerce.product.dto.GetProductOutDto;
import com.ecommerce.product.dto.ResponseOutDto;
import com.ecommerce.product.dto.UpdateProductInDto;
import com.ecommerce.product.exception.InvalidDetailsException;
import com.ecommerce.product.exception.RecordNotFoundException;
import com.ecommerce.product.repository.ProductRepository;

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
	 * @param createProductInDto
	 * @return CreateProductOutDto
	 * @throws InvalidDetailsException
	 */
	public CreateProductOutDto createProduct(CreateProductInDto createProductInDto) throws InvalidDetailsException {

		if (Objects.isNull(createProductInDto.getName()) || createProductInDto.getQuantity() == 0
				|| createProductInDto.getPrice() == 0) {
			throw new InvalidDetailsException("Invalid details for a product!");
		}

		Product product = modelMapper.map(createProductInDto, Product.class);
		product = productRepository.save(product);

		CreateProductOutDto productOutDto = modelMapper.map(product, CreateProductOutDto.class);
		return productOutDto;
	}

	/**
	 * @param productId
	 * @return ProductOutDto
	 * @throws InvalidDetailsException
	 * @throws RecordNotFoundException
	 */
	public GetProductOutDto getProduct(String productId) throws InvalidDetailsException, RecordNotFoundException {

		if (Objects.isNull(productId)) {
			throw new InvalidDetailsException("Invalid product Id!");
		}

		Optional<Product> optProduct = productRepository.findById(productId);
		if (optProduct.isEmpty()) {
			throw new RecordNotFoundException("Product not found!");
		}

		Product product = optProduct.get();
		GetProductOutDto getProductOutDto = modelMapper.map(product, GetProductOutDto.class);
		return getProductOutDto;
	}

	/**
	 * @return List<GetProductOutDto>
	 * @throws RecordNotFoundException
	 */
	public List<GetProductOutDto> getAllProducts() throws RecordNotFoundException {

		List<Product> allProducts = productRepository.findAll();

		if (allProducts.size() == 0) {
			throw new RecordNotFoundException("No Products Found!");
		}

		List<GetProductOutDto> getProductsList = allProducts.stream()
				.map(product -> modelMapper.map(product, GetProductOutDto.class)).collect(Collectors.toList());
		return getProductsList;
	}

	/**
	 * @param updateProductDto
	 * @return ResponseOutDto
	 * @throws RecordNotFoundException
	 * @throws InvalidDetailsException
	 */
	public ResponseOutDto updateProducts(UpdateProductInDto updateProductDto, String productId)
			throws RecordNotFoundException, InvalidDetailsException {

		if (Objects.isNull(updateProductDto) || Objects.isNull(productId)) {
			throw new InvalidDetailsException("Invalid product details!");
		}

		Optional<Product> optProduct = productRepository.findById(productId);
		if (optProduct.isEmpty()) {
			throw new RecordNotFoundException("Product not found!");
		}

		Product product = modelMapper.map(updateProductDto, Product.class);
		product.setId(productId);
		product = productRepository.save(product);

		ResponseOutDto reponseOutDto = new ResponseOutDto();
		reponseOutDto.setMessage("Product Updated Successfully!");
		return reponseOutDto;
	}

	/**
	 * @param productId
	 * @return ResponseOutDto
	 * @throws InvalidDetailsException
	 * @throws RecordNotFoundException
	 */
	public ResponseOutDto deleteProduct(String productId) throws InvalidDetailsException, RecordNotFoundException {

		Optional<Product> optProduct = productRepository.findById(productId);
		if (optProduct.isEmpty()) {
			throw new RecordNotFoundException("Product not found!");
		}

		productRepository.deleteById(productId);

		ResponseOutDto reponseOutDto = new ResponseOutDto();
		reponseOutDto.setMessage("Product Deleted Successfully!");
		return reponseOutDto;
	}
}
