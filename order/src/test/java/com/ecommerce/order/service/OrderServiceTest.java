package com.ecommerce.order.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

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

public class OrderServiceTest {

    /**
     * The model mapper object.
     */
    private ModelMapper modelMapper = new ModelMapper();

    /**
     * ProductRepository
     */
    @Mock
    private CartRepository cartRepository;

    /**
     * ProductRepository
     */
    @Mock
    private ProductRepository productRepository;

    /**
     * OrderRepository
     */
    @Mock
    private OrderRepository orderRepository;

    /**
     * ProductRepository
     */
    @Mock
    private PaymentRepository paymentRepository;

    /**
     * UserClient
     */
    @Mock
    private UserClient userClient;

    @InjectMocks
    private OrderService orderService;

    @Test
    public void placeOrderTest() throws RecordNotFoundException, InvalidDetailsException {

        MockitoAnnotations.openMocks(this);

        Long userId = 1l;
        String cartId = "cartId";
        OrderInDto orderInDto = buildOrderInDto(userId, cartId);

        String id = "id1";
        List<CartProducts> cartProducts = new ArrayList<CartProducts>();
        CartProducts cartProducts1 = buildCartProducts(id, 2);
        cartProducts.add(cartProducts1);
        Cart cart = buildCart(id, userId, cartProducts);

        String productId = "id1";
        String name = "name";
        String description = "description";
        Integer quantity = 2;
        double price = 1.0;
        double discount = 1.0;
        String manufacturer = "manufacturer";
        Product product = buildProduct(productId, name, description, quantity, price, discount, manufacturer);

        String walleId = "id1";
        String cardNo = "cardNo";
        double balance = 23.0;
        Boolean isDefault = true;
        Wallet wallet = buildWallet(walleId, userId, cardNo, balance, isDefault);

        Mockito.when(userClient.checkUserAndRole(userId, "buyer")).thenReturn("buyer");

        Optional<Cart> empCart = Optional.empty();
        Mockito.when(cartRepository.findByIdAndUserId(orderInDto.getCartId(), orderInDto.getUserId()))
                .thenReturn(empCart);
        RecordNotFoundException recordNotFoundException = assertThrows(RecordNotFoundException.class,
                () -> orderService.placeOrder(orderInDto));
        assertEquals(ResponseConstants.NO_CARTS_AVAILABLE, recordNotFoundException.getMessage());

        Mockito.when(cartRepository.findByIdAndUserId(orderInDto.getCartId(), orderInDto.getUserId()))
                .thenReturn(Optional.of(cart));

        Optional<Product> empProduct = Optional.empty();
        Mockito.when(productRepository.findById(cart.getCartProducts().get(0).getProductId())).thenReturn(empProduct);
        RecordNotFoundException recordNotFoundException2 = assertThrows(RecordNotFoundException.class,
                () -> orderService.placeOrder(orderInDto));
        assertEquals(ResponseConstants.PRODUCTS_NOT_FOUND, recordNotFoundException2.getMessage());

        Mockito.when(productRepository.findById(cart.getCartProducts().get(0).getProductId()))
                .thenReturn(Optional.of(product));

        Optional<Wallet> empWallet = Optional.empty();
        Mockito.when(paymentRepository.findByUserIdAndIsDefault(orderInDto.getUserId(), true)).thenReturn(empWallet);
        RecordNotFoundException recordNotFoundException3 = assertThrows(RecordNotFoundException.class,
                () -> orderService.placeOrder(orderInDto));
        assertEquals(ResponseConstants.DEFAULT_WALLET_NOT_FOUND, recordNotFoundException3.getMessage());

        Mockito.when(paymentRepository.findByUserIdAndIsDefault(orderInDto.getUserId(), true))
                .thenReturn(Optional.of(wallet));

        ResponseOutDto response = new ResponseOutDto();
        response.setMessage(ResponseConstants.ORDER_PLACED);

        Mockito.when(cartRepository.findByIdAndUserId(orderInDto.getCartId(), orderInDto.getUserId()))
                .thenReturn(Optional.of(cart));
        assertEquals(response, orderService.placeOrder(orderInDto));

    }

    @Test
    public void getOrdersTest() throws RecordNotFoundException, InvalidDetailsException {

        MockitoAnnotations.openMocks(this);

        Long userId = 1l;

        Instant createDt = Instant.parse("2023-06-08T11:54:54.096543500Z");
        double totalAmount = 2.0;
        String status = "placed";
        String cardNo = "54qw";
        List<Product> products = new ArrayList<Product>();

        String productId = "id1";
        String name = "name";
        String description = "description";
        Integer quantity = 2;
        double price = 1.0;
        double discount = 1.0;
        String manufacturer = "manufacturer";
        Product productTest = buildProduct(productId, name, description, quantity, price, discount, manufacturer);
        products.add(productTest);

        String orderId = "id2";
        Order orderTest = buildOrder(orderId, userId, createDt, totalAmount, status, cardNo, products);
        List<Order> orders = new ArrayList<Order>();
        orders.add(orderTest);

        Mockito.when(userClient.checkUserAndRole(userId, "buyer")).thenReturn("buyer");
        Mockito.when(orderRepository.findByUserId(userId)).thenReturn(orders);

        List<OrderOutDto> orderOutDtos = orders.stream().map(ord -> modelMapper.map(ord, OrderOutDto.class))
                .collect(Collectors.toList());

        assertEquals(orderOutDtos, orderService.getOrders(userId));

        List<Order> empList = new ArrayList<Order>();
        Mockito.when(orderRepository.findByUserId(userId)).thenReturn(empList);
        RecordNotFoundException recordNotFoundException = assertThrows(RecordNotFoundException.class,
                () -> orderService.getOrders(userId));
        assertEquals(ResponseConstants.ORDERS_NOT_FOUND, recordNotFoundException.getMessage());
    }

    @Test
    public void getOrderDetailTest() throws RecordNotFoundException, InvalidDetailsException {

        MockitoAnnotations.openMocks(this);

        String orderId = "ord1";
        Long userId = 1l;

        double totalAmount = 1.0;
        String status = "placed";
        String cardNo = "cardNo";
        Instant createDt = Instant.parse("2023-06-08T15:09:38.953487600Z");

        List<Product> products = new ArrayList<Product>();
        String productId = "id1";
        String name = "name";
        String description = "description";
        Integer quantity = 2;
        double price = 1.0;
        double discount = 1.0;
        String manufacturer = "manufacturer";
        Product productTest = buildProduct(productId, name, description, quantity, price, discount, manufacturer);
        products.add(productTest);

        Order order = buildOrder(orderId, userId, createDt, totalAmount, status, cardNo, products);

        Mockito.when(userClient.checkUserAndRole(userId, "buyer")).thenReturn("buyer");
        Mockito.when(orderRepository.findByIdAndUserId(orderId, userId)).thenReturn(Optional.of(order));

        OrderDetailOutDto orderDetailOutDto = modelMapper.map(order, OrderDetailOutDto.class);

        assertEquals(orderDetailOutDto, orderService.getOrderDetail(userId, orderId));

        Optional<Order> empOrder = Optional.empty();
        Mockito.when(orderRepository.findByIdAndUserId(orderId, userId)).thenReturn(empOrder);
        RecordNotFoundException recordNotFoundException = assertThrows(RecordNotFoundException.class,
                () -> orderService.getOrderDetail(userId, orderId));
        assertEquals(ResponseConstants.ORDERS_NOT_FOUND, recordNotFoundException.getMessage());
    }

    @Test
    public void deleteOrderTest() throws RecordNotFoundException, InvalidDetailsException {

        MockitoAnnotations.openMocks(this);

        String orderId = "ord1";
        Long userId = 1l;

        double totalAmount = 1.0;
        String status = "placed";
        String cardNo = "cardNo";
        Instant createDt = Instant.parse("2023-06-08T15:09:38.953487600Z");

        List<Product> products = new ArrayList<Product>();
        String productId = "id1";
        String name = "name";
        String description = "description";
        Integer quantity = 2;
        double price = 1.0;
        double discount = 1.0;
        String manufacturer = "manufacturer";
        Product productTest = buildProduct(productId, name, description, quantity, price, discount, manufacturer);
        products.add(productTest);

        Order order = buildOrder(orderId, userId, createDt, totalAmount, status, cardNo, products);

        Mockito.when(userClient.checkUserAndRole(userId, "buyer")).thenReturn("buyer");
        Mockito.when(orderRepository.findByIdAndUserId(orderId, userId)).thenReturn(Optional.of(order));
        Mockito.when(productRepository.findById(order.getProducts().get(0).getId()))
                .thenReturn(Optional.of(productTest));

        String walleId = "id1";
        double balance = 23.0;
        Boolean isDefault = true;
        Wallet wallet = buildWallet(walleId, userId, cardNo, balance, isDefault);
        Mockito.when(paymentRepository.findByCardNo(order.getCardNo())).thenReturn(Optional.of(wallet));

        ResponseOutDto response = new ResponseOutDto();
        response.setMessage(ResponseConstants.ORDER_CANCELED);

        assertEquals(response, orderService.deleteOrder(userId, orderId));

        Optional<Order> empOrder = Optional.empty();
        Mockito.when(orderRepository.findByIdAndUserId(orderId, userId)).thenReturn(empOrder);
        RecordNotFoundException recordNotFoundException = assertThrows(RecordNotFoundException.class,
                () -> orderService.deleteOrder(userId, orderId));
        assertEquals(ResponseConstants.ORDERS_NOT_FOUND, recordNotFoundException.getMessage());
    }

    public Wallet buildWallet(String id, Long userId, String cardNo, double balance, Boolean isDefault) {
        Wallet walletTest = new Wallet();
        walletTest.setBalance(balance);
        walletTest.setCardNo(cardNo);
        walletTest.setId(id);
        walletTest.setIsDefault(isDefault);
        walletTest.setUserId(userId);
        return walletTest;
    }

    public OrderInDto buildOrderInDto(Long userId, String cartId) {
        OrderInDto orderInDtoTest = new OrderInDto();
        orderInDtoTest.setCartId(cartId);
        orderInDtoTest.setUserId(userId);
        return orderInDtoTest;
    }

    public Product buildProduct(String id, String name, String description, Integer quantity, double price,
            double discount, String manufacturer) {
        Product productTest = new Product();
        productTest.setDescription(description);
        productTest.setDiscount(discount);
        productTest.setId(id);
        productTest.setManufacturer(manufacturer);
        productTest.setName(name);
        productTest.setPrice(price);
        productTest.setQuantity(quantity);
        return productTest;
    }

    public Cart buildCart(String id, Long userId, List<CartProducts> cartProducts) {
        Cart cartTest = new Cart();
        cartTest.setCartProducts(cartProducts);
        cartTest.setId(id);
        cartTest.setUserId(userId);
        return cartTest;
    }

    public CartProducts buildCartProducts(String productId, Integer quantity) {
        CartProducts cartProductsTest = new CartProducts();
        cartProductsTest.setQuantity(quantity);
        cartProductsTest.setProductId(productId);
        return cartProductsTest;
    }

    public Order buildOrder(String id, Long userId, Instant createDt, double totalAmount, String status, String cardNo,
            List<Product> products) {
        Order orderTest = new Order();
        orderTest.setCardNo(cardNo);
        orderTest.setCreateDt(createDt);
        orderTest.setId(id);
        orderTest.setProducts(products);
        orderTest.setStatus(status);
        orderTest.setTotalAmount(totalAmount);
        orderTest.setUserId(userId);
        return orderTest;
    }

}
