package com.ecommerce.payment.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.payment.domain.Wallet;

@Repository
public interface PaymentRepository extends MongoRepository<Wallet, String> {

	Optional<Wallet> findByUserIdAndCardNo(String userId, String cardNo);

	List<Wallet> findByUserId(String userId);

	List<Wallet> findByUserIdAndIsDefault(String userId, boolean b);
}
