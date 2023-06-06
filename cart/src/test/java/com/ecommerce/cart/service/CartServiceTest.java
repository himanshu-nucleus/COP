package com.ecommerce.cart.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

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

public class CartServiceTest {

    /**
     * The model mapper object.
     */
    private ModelMapper modelMapper = new ModelMapper();

    /**
     * CartRepository
     */
    @Mock
    private CartRepository cartRepository;

    /**
     * productRepository
     */
    @Mock
    private ProductRepository productRepository;

    /**
     * CartService
     */
    @InjectMocks
    private CartService cartService;

    /**
     * UserClient
     */
    @Mock
    private UserClient userClient;

    @Test
    public void createCartTest() throws RecordNotFoundException {

        MockitoAnnotations.openMocks(this);

        Long userId = 1L;
        String productId = "id1";
        Integer quantity = 1;
        
        CreateCartInDto createCartInDto = buildCreateCartInDto(userId, productId, quantity);

        CartProducts cartProduct = new CartProducts();
        cartProduct.setQuantity(createCartInDto.getQuantity());
        cartProduct.setProductId(createCartInDto.getProductId());

        List<CartProducts> cartProducts = new ArrayList<CartProducts>();
        cartProducts.add(cartProduct);

        Cart cart = buildCart(null, userId, cartProducts);
        Cart retCart = buildCart(productId, userId, cartProducts);

        Mockito.when(cartRepository.save(cart)).thenReturn(retCart);
        Mockito.when(userClient.checkUserAndRole(userId, "buyer")).thenReturn("buyer");

        ResponseOutDto response = new ResponseOutDto();
        response.setMessage(ResponseConstants.CART_CREATED);

        String id = "id1";
        String name = "name";
        String description = "description";
        Integer pQuantity = 2;
        double price = 1.0;
        double discount = 1.0;
        String manufacturer = "manufacturer";

        Product retProduct = buildProduct(id, name, description, pQuantity, price, discount, manufacturer);

        Mockito.when(productRepository.findById("id1")).thenReturn(Optional.of(retProduct));
        assertEquals(response, cartService.createCart(createCartInDto));

        createCartInDto.setProductId("changed");
        RecordNotFoundException recordNotFoundException = assertThrows(RecordNotFoundException.class,
                () -> cartService.createCart(createCartInDto));
        assertEquals(ResponseConstants.PRODUCTS_NOT_FOUND, recordNotFoundException.getMessage());

        createCartInDto.setQuantity(3);
        createCartInDto.setProductId(id);
        recordNotFoundException = assertThrows(RecordNotFoundException.class,
                () -> cartService.createCart(createCartInDto));
        assertEquals(ResponseConstants.PRODUCTS_OOS, recordNotFoundException.getMessage());

    }

    @Test
    public void getCartTest() throws RecordNotFoundException {

        MockitoAnnotations.openMocks(this);

        Long userId = 1L;
        String productId = "id1";
        Integer quantity = 1;

        CreateCartInDto createCartInDto = buildCreateCartInDto(userId, productId, quantity);

        CartProducts cartProduct = new CartProducts();
        cartProduct.setQuantity(createCartInDto.getQuantity());
        cartProduct.setProductId(createCartInDto.getProductId());

        List<CartProducts> cartProducts = new ArrayList<CartProducts>();
        cartProducts.add(cartProduct);

        Cart retCart = buildCart("id2", userId, cartProducts);

        Mockito.when(cartRepository.findByUserId(userId)).thenReturn(Optional.of(retCart));
        Mockito.when(userClient.checkUserAndRole(userId, "buyer")).thenReturn("buyer");

        String name = "name";
        String description = "description";
        Integer pQuantity = 2;
        double price = 1.0;
        double discount = 1.0;
        String manufacturer = "manufacturer";

        Product retProduct = buildProduct(productId, name, description, pQuantity, price, discount, manufacturer);

        Mockito.when(productRepository.findById(cartProduct.getProductId())).thenReturn(Optional.of(retProduct));

        List<CartProductsDetail> cartProductDetails = new ArrayList<CartProductsDetail>();
        CartProductsDetail prodcutDetail = modelMapper.map(retProduct, CartProductsDetail.class);
        prodcutDetail.setQuantity(retCart.getCartProducts().get(0).getQuantity());
        cartProductDetails.add(prodcutDetail);

        CartOutDto cartOutDto = new CartOutDto();
        cartOutDto.setCartProductsDetail(cartProductDetails);
        cartOutDto.setId(retCart.getId());
        cartOutDto.setUserId(retCart.getUserId());

        assertEquals(cartOutDto, cartService.getCart(userId));

    }

    @Test
    public void deleteCartProductTest() throws RecordNotFoundException {

        MockitoAnnotations.openMocks(this);

        Long userId = 1L;
        String productId = "id1";
        Integer quantity = 1;

        CreateCartInDto createCartInDto = buildCreateCartInDto(userId, productId, quantity);

        CartProducts cartProduct = new CartProducts();
        cartProduct.setQuantity(createCartInDto.getQuantity());
        cartProduct.setProductId(createCartInDto.getProductId());

        List<CartProducts> cartProducts = new ArrayList<CartProducts>();
        cartProducts.add(cartProduct);

        String cartId = "id1";
        Cart retCart = buildCart(cartId, userId, cartProducts);

        DeleteCartProductInDto deleteCartProductInDto = buildDeleteCartProductInDto(cartId, userId, productId);
        Mockito.when(userClient.checkUserAndRole(userId, "buyer")).thenReturn("buyer");
        Mockito.when(cartRepository.findById(deleteCartProductInDto.getCartId())).thenReturn(Optional.of(retCart));

        Optional<CartProducts> productFound = retCart.getCartProducts().stream()
                .filter(f -> f.getProductId().equals(deleteCartProductInDto.getProductId())).findFirst();

        ResponseOutDto response = new ResponseOutDto();
        response.setMessage(ResponseConstants.CART_PRODUCT_DELETED);

        assertEquals(response, cartService.deleteCartProduct(deleteCartProductInDto));

        retCart.getCartProducts().remove(productFound.get());
        RecordNotFoundException recordNotFoundException = assertThrows(RecordNotFoundException.class,
                () -> cartService.deleteCartProduct(deleteCartProductInDto));
        assertEquals(ResponseConstants.PRODUCTS_NOT_FOUND, recordNotFoundException.getMessage());

    }

    @Test
    public void updateCartTest() throws RecordNotFoundException {

        MockitoAnnotations.openMocks(this);

        Long userId = 1L;
        String productId = "id1";
        Integer quantity = 1;

        CreateCartInDto createCartInDto = buildCreateCartInDto(userId, productId, quantity);

        CartProducts cartProduct = new CartProducts();
        cartProduct.setQuantity(createCartInDto.getQuantity());
        cartProduct.setProductId(createCartInDto.getProductId());

        List<CartProducts> cartProducts = new ArrayList<CartProducts>();
        cartProducts.add(cartProduct);

        String cartId = "id1";
        Cart retCart = buildCart(cartId, userId, cartProducts);

        CreateCartInDto updateCartDto = buildCreateCartInDto(userId, productId, quantity);

        Mockito.when(userClient.checkUserAndRole(userId, "buyer")).thenReturn("buyer");
        Mockito.when(cartRepository.findById(cartId)).thenReturn(Optional.of(retCart));

        String id = "id1";
        String name = "name";
        String description = "description";
        Integer pQuantity = 4;
        double price = 1.0;
        double discount = 1.0;
        String manufacturer = "manufacturer";

        Product retProduct = buildProduct(id, name, description, pQuantity, price, discount, manufacturer);
        Mockito.when(productRepository.findById("id1")).thenReturn(Optional.of(retProduct));

        ResponseOutDto response = new ResponseOutDto();
        response.setMessage(ResponseConstants.CART_UPDATED);

        assertEquals(response, cartService.updateCart(updateCartDto, cartId));

//        retCart.getCartProducts().remove(productFound.get());
//        RecordNotFoundException recordNotFoundException = assertThrows(RecordNotFoundException.class,
//                () -> cartService.deleteCartProduct(deleteCartProductInDto));
//        assertEquals(ResponseConstants.PRODUCTS_NOT_FOUND, recordNotFoundException.getMessage());

    }

    public DeleteCartProductInDto buildDeleteCartProductInDto(String cartId, Long userId, String productId) {
        DeleteCartProductInDto deleteCartProductInDto = new DeleteCartProductInDto();
        deleteCartProductInDto.setProductId(productId);
        deleteCartProductInDto.setCartId(cartId);
        deleteCartProductInDto.setUserId(userId);
        return deleteCartProductInDto;
    }

    public Cart buildCart(String id, Long userId, List<CartProducts> cartProducts) {
        Cart cartTest = new Cart();
        cartTest.setCartProducts(cartProducts);
        cartTest.setId(id);
        cartTest.setUserId(userId);
        return cartTest;
    }

    public CreateCartInDto buildCreateCartInDto(Long userId, String productId, Integer quantity) {
        CreateCartInDto createCartInDto = new CreateCartInDto();
        createCartInDto.setProductId(productId);
        createCartInDto.setQuantity(quantity);
        createCartInDto.setUserId(userId);
        return createCartInDto;
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

}
