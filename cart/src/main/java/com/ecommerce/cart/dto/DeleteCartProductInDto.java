package com.ecommerce.cart.dto;

import java.util.Objects;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DeleteCartProductInDto {
	
	private String cartId;
	private Long userId;
	private String productId;

	@Override
	public int hashCode() {
		return Objects.hash(cartId, productId, userId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof DeleteCartProductInDto)) {
			return false;
		}
		DeleteCartProductInDto other = (DeleteCartProductInDto) obj;
		return Objects.equals(cartId, other.cartId) && Objects.equals(productId, other.productId)
				&& Objects.equals(userId, other.userId);
	}


}
