package com.ecommerce.order.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.order.domain.Wallet;

@Repository
public interface PaymentRepository extends MongoRepository<Wallet, String> {

	Optional<Wallet> findByUserIdAndIsDefault(Integer userId, boolean b);

}
