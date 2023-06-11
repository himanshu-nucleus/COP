package com.ecommerce.order.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
import com.ecommerce.order.dto.OrderDetailOutDto;
import com.ecommerce.order.dto.OrderInDto;
import com.ecommerce.order.dto.OrderOutDto;
import com.ecommerce.order.dto.ResponseOutDto;
import com.ecommerce.order.exception.InvalidDetailsException;
import com.ecommerce.order.exception.RecordNotFoundException;
import com.ecommerce.order.repository.CartRepository;
import com.ecommerce.order.repository.OrderRepository;
import com.ecommerce.order.repository.PaymentRepository;
import com.ecommerce.order.repository.ProductRepository;
import com.ecommerce.order.repository.UserClient;

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
	 * UserClient
	 */
	@Autowired
	private UserClient userClient;

	/**
	 * @param orderInDto
	 * @return ResponseOutDto
	 * @throws RecordNotFoundException
	 * @throws InvalidDetailsException
	 */
	public ResponseOutDto placeOrder(OrderInDto orderInDto) throws RecordNotFoundException, InvalidDetailsException {

		checkUserAndItsRole(orderInDto.getUserId(), "buyer");

		Optional<Cart> optCart = cartRepository.findByIdAndUserId(orderInDto.getCartId(), orderInDto.getUserId());
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
		order.setTotalAmount(totalPrice);
		order.setUserId(orderInDto.getUserId());
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
	public List<OrderOutDto> getOrders(Long userId) throws RecordNotFoundException {

		checkUserAndItsRole(userId, "buyer");

		List<Order> orders = orderRepository.findByUserId(userId);
		if (orders.size() == 0) {
			throw new RecordNotFoundException(ResponseConstants.ORDERS_NOT_FOUND);
		}

		List<OrderOutDto> orderOutDtos = orders.stream().map(order -> modelMapper.map(order, OrderOutDto.class))
				.collect(Collectors.toList());

		return orderOutDtos;
	}

	/**
	 * @param userId
	 * @param orderId
	 * @return
	 * @throws RecordNotFoundException
	 */
	public OrderDetailOutDto getOrderDetail(Long userId, String orderId) throws RecordNotFoundException {

		checkUserAndItsRole(userId, "buyer");

		Optional<Order> optOrder = orderRepository.findByIdAndUserId(orderId, userId);
		if (optOrder.isEmpty()) {
			throw new RecordNotFoundException(ResponseConstants.ORDERS_NOT_FOUND);
		}

		OrderDetailOutDto orderOutDtos = modelMapper.map(optOrder.get(), OrderDetailOutDto.class);
		return orderOutDtos;
	}

	/**
	 * @param orderId
	 * @param userId
	 * @return ResponseOutDto
	 * @throws RecordNotFoundException
	 */
	public ResponseOutDto deleteOrder(Long userId, String orderId) throws RecordNotFoundException {
	    
		checkUserAndItsRole(userId, "buyer");

		Optional<Order> optOrder = orderRepository.findByIdAndUserId(orderId, userId);
		if (optOrder.isEmpty()) {
			throw new RecordNotFoundException(ResponseConstants.ORDERS_NOT_FOUND);
		}

		Order order = optOrder.get();
		order.setStatus("canceled");
		orderRepository.save(order);

		for (Product orderProduct : order.getProducts()) {
			Product dbProduct = productRepository.findById(orderProduct.getId()).get();
			dbProduct.setQuantity(orderProduct.getQuantity() + dbProduct.getQuantity());
			productRepository.save(dbProduct);
		}

		Wallet wallet = paymentRepository.findByCardNo(order.getCardNo()).get();
		wallet.setBalance(wallet.getBalance() + order.getTotalAmount());
		paymentRepository.save(wallet);

		ResponseOutDto response = new ResponseOutDto();
		response.setMessage(ResponseConstants.ORDER_CANCELED);

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
