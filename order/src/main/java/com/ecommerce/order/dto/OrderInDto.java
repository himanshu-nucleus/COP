package com.ecommerce.order.dto;

import java.util.Objects;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderInDto {

	private Long userId;
	private String cartId;

	@Override
	public int hashCode() {
		return Objects.hash(cartId, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof OrderInDto)) {
			return false;
		}
		OrderInDto other = (OrderInDto) obj;
		return Objects.equals(cartId, other.cartId) && Objects.equals(userId, other.userId);
	}

}
