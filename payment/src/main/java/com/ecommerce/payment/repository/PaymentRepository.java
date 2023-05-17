package com.ecommerce.payment.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.payment.domain.Wallet;

@Repository
public interface PaymentRepository extends MongoRepository<Wallet, String> {

	Optional<Wallet> findByUserIdAndCardNo(Long long1, String cardNo);

	List<Wallet> findByUserId(Long userId);

	List<Wallet> findByUserIdAndIsDefault(Long long1, boolean b);

	Optional<Wallet> findByIdAndUserId(String walletId, Long userId);
}
