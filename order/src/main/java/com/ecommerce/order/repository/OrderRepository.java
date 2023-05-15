package com.ecommerce.order.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.order.domain.Order;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

	List<Order> findByUserId(Integer userId);

	Optional<Order> findByIdAndUserId(String orderId, Integer userId);
}
