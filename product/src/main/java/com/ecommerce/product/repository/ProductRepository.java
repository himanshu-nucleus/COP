package com.ecommerce.product.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.product.domain.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

	Optional<Product> findByIdAndUserId(String productId, Long userId);

	List<Product> findByUserId(Long userId);
}
