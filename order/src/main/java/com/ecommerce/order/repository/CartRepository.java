package com.ecommerce.order.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.order.domain.Cart;

@Repository
public interface CartRepository extends MongoRepository<Cart, String> {

	Optional<Cart> findByUserId(Integer userId);

	Optional<Cart> findByIdAndUserId(String cartId, Long long1);

}
