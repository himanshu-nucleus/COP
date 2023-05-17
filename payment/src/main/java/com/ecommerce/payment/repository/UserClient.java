package com.ecommerce.payment.repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value="user", url="http://localhost:8080/v1/user")
public interface UserClient {
	
	@GetMapping("/check/{userId}/role/{role}")
	public String checkUserAndRole(@PathVariable("userId") Long userId, @PathVariable("role") String role);
}
