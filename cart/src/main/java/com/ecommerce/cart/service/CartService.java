	package com.ecommerce.cart.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.cart.constants.ResponseConstants;
import com.ecommerce.cart.domain.Cart;
import com.ecommerce.cart.domain.CartProducts;
import com.ecommerce.cart.domain.Product;
import com.ecommerce.cart.dto.CartOutDto;
import com.ecommerce.cart.dto.CartProductsDetail;
import com.ecommerce.cart.dto.CreateCartInDto;
import com.ecommerce.cart.dto.DeleteCartProductInDto;
import com.ecommerce.cart.dto.ResponseOutDto;
import com.ecommerce.cart.exception.RecordNotFoundException;
import com.ecommerce.cart.repository.CartRepository;
import com.ecommerce.cart.repository.ProductRepository;
import com.ecommerce.cart.repository.UserClient;

@Service
public class CartService {

	/**
	 * The model mapper object.
	 */
	private ModelMapper modelMapper = new ModelMapper();

	/**
	 * ProductRepository
	 */
	@Autowired
	private CartRepository cartRepository;

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
	 * @param createCartInDto
	 * @return CreateCartInDto
	 * @throws RecordNotFoundException
	 */
	public ResponseOutDto createCart(CreateCartInDto createCartInDto) throws RecordNotFoundException {

		checkUserAndItsRole(createCartInDto.getUserId(), "buyer");
		checkProduct(createCartInDto);

		CartProducts cartProduct = new CartProducts();
		cartProduct.setQuantity(createCartInDto.getQuantity());
		cartProduct.setProductId(createCartInDto.getProductId());

		List<CartProducts> cartProducts = new ArrayList<CartProducts>();
		cartProducts.add(cartProduct);

		Cart cart = new Cart();
		cart.setUserId(createCartInDto.getUserId());
		cart.setCartProducts(cartProducts);
		cartRepository.save(cart);

		ResponseOutDto response = new ResponseOutDto();
		response.setMessage(ResponseConstants.CART_CREATED);

		return response;
	}

	/**
	 * @param createCartInDto
	 * @throws RecordNotFoundException
	 */
	private void checkProduct(CreateCartInDto createCartInDto) throws RecordNotFoundException {

		Optional<Product> optProduct = productRepository.findById(createCartInDto.getProductId());
		if (optProduct.isEmpty()) {
			throw new RecordNotFoundException(ResponseConstants.PRODUCTS_NOT_FOUND);
		}

		Product product = optProduct.get();

		Integer remainingQuantity = product.getQuantity() - createCartInDto.getQuantity();
		if (product.getQuantity() == 0 || remainingQuantity < 0) {
			throw new RecordNotFoundException(ResponseConstants.PRODUCTS_OOS);
		}

	}

	/**
	 * @param userId
	 * @return CartOutDto
	 * @throws RecordNotFoundException
	 */
	public CartOutDto getCart(Long userId) throws RecordNotFoundException {

		checkUserAndItsRole(userId, "buyer");

		Optional<Cart> optCart = cartRepository.findByUserId(userId);
		if (optCart.isEmpty()) {
			throw new RecordNotFoundException(ResponseConstants.NO_CARTS_AVAILABLE);
		}

		List<CartProductsDetail> cartProductDetails = new ArrayList<CartProductsDetail>();

		List<CartProducts> cartProducts = optCart.get().getCartProducts();
		for (CartProducts prod : cartProducts) {
			Optional<Product> optProduct = productRepository.findById(prod.getProductId());

			if (optProduct.isEmpty()) {
				throw new RecordNotFoundException(ResponseConstants.PRODUCTS_NOT_FOUND);
			}

			CartProductsDetail prodcutDetail = modelMapper.map(optProduct.get(), CartProductsDetail.class);
			prodcutDetail.setQuantity(prod.getQuantity());
			cartProductDetails.add(prodcutDetail);
		}

		CartOutDto cartOutDto = new CartOutDto();
		cartOutDto.setCartProductsDetail(cartProductDetails);
		cartOutDto.setId(optCart.get().getId());
		cartOutDto.setUserId(optCart.get().getUserId());

		return cartOutDto;
	}

	/**
	 * @param deleteCartProductInDto
	 * @return ResponseOutDto
	 * @throws RecordNotFoundException
	 */
	public ResponseOutDto deleteCartProduct(DeleteCartProductInDto deleteCartProductInDto)
			throws RecordNotFoundException {
		
		checkUserAndItsRole(deleteCartProductInDto.getUserId(), "buyer");		

		Optional<Cart> optCart = cartRepository.findById(deleteCartProductInDto.getCartId());
		if (optCart.isEmpty()) {
			throw new RecordNotFoundException(ResponseConstants.CART_NOT_FOUND);
		}
		
		Cart cart = optCart.get();
		Optional<CartProducts> productFound = cart.getCartProducts().stream()
				.filter(f -> f.getProductId().equals(deleteCartProductInDto.getProductId())).findFirst();
		
		if (productFound.isEmpty()) {
			throw new RecordNotFoundException(ResponseConstants.PRODUCTS_NOT_FOUND);
		}
		
		cart.getCartProducts().remove(productFound.get());

		if (cart.getCartProducts().size() == 0) {
			cartRepository.deleteById(deleteCartProductInDto.getCartId());
		} else {
			cartRepository.save(cart);
		}

		ResponseOutDto response = new ResponseOutDto();
		response.setMessage(ResponseConstants.CART_PRODUCT_DELETED);

		return response;
	}

	/**
	 * @param updateCartDetails
	 * @param cartId
	 * @return ResponseOutDto
	 * @throws RecordNotFoundException
	 */
	public ResponseOutDto updateCart(CreateCartInDto updateCartDetails, String cartId) throws RecordNotFoundException {

		checkUserAndItsRole(updateCartDetails.getUserId(), "buyer");
		checkProduct(updateCartDetails);

		Optional<Cart> optCart = cartRepository.findById(cartId);
		if (optCart.isEmpty()) {
			throw new RecordNotFoundException(ResponseConstants.CART_NOT_FOUND);
		}

		Cart cart = optCart.get();
		List<CartProducts> existingProducts = cart.getCartProducts();
		Optional<CartProducts> productInCart = existingProducts.stream()
				.filter(f -> f.getProductId().equals(updateCartDetails.getProductId())).findFirst();

		if (!productInCart.isEmpty()) {
			existingProducts.remove(productInCart.get());
		}

		CartProducts cartProduct = new CartProducts();
		cartProduct.setQuantity(updateCartDetails.getQuantity());
		cartProduct.setProductId(updateCartDetails.getProductId());

		existingProducts.add(cartProduct);

		cart.setCartProducts(existingProducts);
		cartRepository.save(cart);

		ResponseOutDto response = new ResponseOutDto();
		response.setMessage(ResponseConstants.CART_UPDATED);

		return response;
	}

	/**
	 * @param userId
	 * @throws RecordNotFoundException
	 */
	public void checkUserAndItsRole(Long userId, String role) throws RecordNotFoundException {
		String userRole = userClient.checkUserAndRole(userId, role);
		if (Objects.isNull(userRole) || !userRole.equals(role)) {
			throw new RecordNotFoundException(ResponseConstants.UNAUTHORIZED_USER);
		}
	}
}
