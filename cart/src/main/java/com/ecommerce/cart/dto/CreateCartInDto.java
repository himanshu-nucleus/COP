package com.ecommerce.cart.dto;

import java.util.Objects;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateCartInDto {
	
	private Long userId;
	private String productId;
    private Integer quantity;

	@Override
	public int hashCode() {
		return Objects.hash(productId, quantity, userId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof CreateCartInDto)) {
			return false;
		}
		CreateCartInDto other = (CreateCartInDto) obj;
		return Objects.equals(productId, other.productId) && Objects.equals(quantity, other.quantity)
				&& Objects.equals(userId, other.userId);
	}

    
}
