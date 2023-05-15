package com.ecommerce.order.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.order.domain.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
}
