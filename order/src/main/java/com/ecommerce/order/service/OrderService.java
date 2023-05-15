package com.ecommerce.order.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.order.constants.ResponseConstants;
import com.ecommerce.order.domain.Cart;
import com.ecommerce.order.domain.CartProducts;
import com.ecommerce.order.domain.Order;
import com.ecommerce.order.domain.Product;
import com.ecommerce.order.domain.Wallet;
import com.ecommerce.order.dto.OrderInDto;
import com.ecommerce.order.dto.OrderOutDto;
import com.ecommerce.order.dto.ResponseOutDto;
import com.ecommerce.order.exception.InvalidDetailsException;
import com.ecommerce.order.exception.RecordNotFoundException;
import com.ecommerce.order.repository.CartRepository;
import com.ecommerce.order.repository.OrderRepository;
import com.ecommerce.order.repository.PaymentRepository;
import com.ecommerce.order.repository.ProductRepository;

@Service
public class OrderService {

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
	 * OrderRepository
	 */
	@Autowired
	private OrderRepository orderRepository;

	/**
	 * ProductRepository
	 */
	@Autowired
	private PaymentRepository paymentRepository;

	/**
	 * @param orderInDto
	 * @return ResponseOutDto
	 * @throws RecordNotFoundException
	 * @throws InvalidDetailsException
	 */
	public ResponseOutDto placeOrder(OrderInDto orderInDto) throws RecordNotFoundException, InvalidDetailsException {

		Optional<Cart> optCart = cartRepository.findById(orderInDto.getCartId());
		if (optCart.isEmpty()) {
			throw new RecordNotFoundException(ResponseConstants.NO_CARTS_AVAILABLE);
		}

		List<Product> orderProductDetails = new ArrayList<Product>();
		List<Product> updatedProducts = new ArrayList<Product>();
		List<CartProducts> cartProducts = optCart.get().getCartProducts();
		double totalPrice = 0;

		for (CartProducts cartProduct : cartProducts) {
			Optional<Product> mainProduct = productRepository.findById(cartProduct.getProductId());
			if (mainProduct.isEmpty()) {
				throw new RecordNotFoundException(ResponseConstants.PRODUCTS_NOT_FOUND);
			}

			Product product = mainProduct.get();

			Integer remainingQuantity = product.getQuantity() - cartProduct.getQuantity();
			if (product.getQuantity() == 0 || remainingQuantity < 0) {
				throw new RecordNotFoundException(ResponseConstants.PRODUCTS_OOS);
			}

			totalPrice = totalPrice + (product.getPrice() * cartProduct.getQuantity());

			product.setQuantity(remainingQuantity);
			updatedProducts.add(product);

			Product productInOrder = modelMapper.map(product, Product.class);
			productInOrder.setQuantity(cartProduct.getQuantity());
			orderProductDetails.add(productInOrder);
		}

		Optional<Wallet> optWallet = paymentRepository.findByUserIdAndIsDefault(orderInDto.getUserId(), true);
		if (optWallet.isEmpty()) {
			throw new RecordNotFoundException(ResponseConstants.DEFAULT_WALLET_NOT_FOUND);
		}
		Wallet wallet = optWallet.get();
		if (wallet.getBalance() < totalPrice) {
			throw new InvalidDetailsException(ResponseConstants.INSUFFICIENT_BALANCE);
		}
		
		wallet.setBalance(wallet.getBalance() - totalPrice);
		paymentRepository.save(wallet);

		productRepository.saveAll(updatedProducts);

		Order order = new Order();
		order.setCardNo(wallet.getCardNo());
		order.setStatus("placed");
		order.setTotalPrice(totalPrice);
		order.setUserId(wallet.getUserId());
		order.setCreateDt(Instant.now());
		order.setProducts(orderProductDetails);
		orderRepository.save(order);
		
		cartRepository.deleteById(orderInDto.getCartId());

		ResponseOutDto response = new ResponseOutDto();
		response.setMessage(ResponseConstants.ORDER_PLACED);

		return response;
	}

	/**
	 * @param userId
	 * @return List<OrderOutDto>
	 * @throws RecordNotFoundException
	 */
	public List<OrderOutDto> getOrders(Integer userId) throws RecordNotFoundException {
		
		List<Order> orders = orderRepository.findByUserId(orderRepository);
		if (orders.size() == 0) {
			throw new RecordNotFoundException(ResponseConstants.ORDERS_NOT_FOUND);
		}
		
		List<OrderOutDto> orderOutDtos = orders.stream().map(order -> modelMapper.map(order, OrderOutDto.class))
				.collect(Collectors.toList());

		return orderOutDtos;
	}

//	/**
//	 * @param createCartInDto
//	 * @return CreateCartInDto
//	 * @throws RecordNotFoundException
//	 */
//	public ResponseOutDto createCart(CreateCartInDto createCartInDto) throws RecordNotFoundException {
//
//		checkProduct(createCartInDto);
//
//		CartProducts cartProduct = new CartProducts();
//		cartProduct.setQuantity(createCartInDto.getQuantity());
//		cartProduct.setProductId(createCartInDto.getProductId());
//
//		List<CartProducts> cartProducts = new ArrayList<CartProducts>();
//		cartProducts.add(cartProduct);
//
//		Cart cart = new Cart();
//		cart.setUserId(createCartInDto.getUserId());
//		cart.setCartProducts(cartProducts);
//		cartRepository.save(cart);
//
//		ResponseOutDto response = new ResponseOutDto();
//		response.setMessage(ResponseConstants.CART_CREATED);
//
//		return response;
//	}

//	/**
//	 * @param createCartInDto
//	 * @throws RecordNotFoundException
//	 */
//	private void checkProduct(CreateCartInDto createCartInDto) throws RecordNotFoundException {
//		
//		Optional<Product> optProduct = productRepository.findById(createCartInDto.getProductId());
//		if (optProduct.isEmpty()) {
//			throw new RecordNotFoundException(ResponseConstants.PRODUCTS_NOT_FOUND);
//		}
//
//		Product product = optProduct.get();
//
//		Integer remainingQuantity = product.getQuantity() - createCartInDto.getQuantity();
//		if (product.getQuantity() == 0 || remainingQuantity < 0) {
//			throw new RecordNotFoundException(ResponseConstants.PRODUCTS_OOS);
//		}
//		
//	}
//
//	/**
//	 * @param userId
//	 * @return CartOutDto
//	 * @throws RecordNotFoundException
//	 */
//	public CartOutDto getCart(Integer userId) throws RecordNotFoundException {
//
//		Optional<Cart> optCart = cartRepository.findByUserId(userId);
//		if (optCart.isEmpty()) {
//			throw new RecordNotFoundException(ResponseConstants.NO_CARTS_AVAILABLE);
//		}
//
//		List<CartProductsDetail> cartProductDetails = new ArrayList<CartProductsDetail>();
//
//		List<CartProducts> cartProducts = optCart.get().getCartProducts();
//		for (CartProducts prod : cartProducts) {
//			Optional<Product> optProduct = productRepository.findById(prod.getProductId());
//
//			if (optProduct.isEmpty()) {
//				throw new RecordNotFoundException(ResponseConstants.PRODUCTS_NOT_FOUND);
//			}
//
//			CartProductsDetail prodcutDetail = modelMapper.map(optProduct.get(), CartProductsDetail.class);
//			prodcutDetail.setQuantity(prod.getQuantity());
//			cartProductDetails.add(prodcutDetail);
//		}
//
//		CartOutDto cartOutDto = new CartOutDto();
//		cartOutDto.setCartProductsDetail(cartProductDetails);
//		cartOutDto.setId(optCart.get().getId());
//		cartOutDto.setUserId(optCart.get().getUserId());
//
//		return cartOutDto;
//	}
//
//	/**
//	 * @param cartId
//	 * @return ResponseOutDto
//	 * @throws RecordNotFoundException
//	 */
//	public ResponseOutDto deleteCart(String cartId) throws RecordNotFoundException {
//
//		Optional<Cart> optCart = cartRepository.findById(cartId);
//		if (optCart.isEmpty()) {
//			throw new RecordNotFoundException(ResponseConstants.CART_NOT_FOUND);
//		}
//
//		cartRepository.deleteById(cartId);
//
//		ResponseOutDto response = new ResponseOutDto();
//		response.setMessage(ResponseConstants.CART_DELETED);
//
//		return response;
//	}
//
//	/**
//	 * @param updateCartDetails
//	 * @param cartId
//	 * @return ResponseOutDto
//	 * @throws RecordNotFoundException
//	 */
//	public ResponseOutDto updateCart(CreateCartInDto updateCartDetails, String cartId) throws RecordNotFoundException {
//
//		checkProduct(updateCartDetails);
//		
//		Optional<Cart> optCart = cartRepository.findById(cartId);
//		if (optCart.isEmpty()) {
//			throw new RecordNotFoundException(ResponseConstants.CART_NOT_FOUND);
//		}
//
//		Cart cart = optCart.get();
//		List<CartProducts> existingProducts = cart.getCartProducts();
//		Optional<CartProducts> productInCart = existingProducts.stream()
//				.filter(f -> f.getProductId().equals(updateCartDetails.getProductId())).findFirst();
//
//		if (!productInCart.isEmpty()) {
//			existingProducts.remove(productInCart.get());
//		}
//
//		CartProducts cartProduct = new CartProducts();
//		cartProduct.setQuantity(updateCartDetails.getQuantity());
//		cartProduct.setProductId(updateCartDetails.getProductId());
//
//		existingProducts.add(cartProduct);
//
//		cart.setCartProducts(existingProducts);
//		cartRepository.save(cart);
//
//		ResponseOutDto response = new ResponseOutDto();
//		response.setMessage(ResponseConstants.CART_UPDATED);
//
//		return response;
//	}

}
