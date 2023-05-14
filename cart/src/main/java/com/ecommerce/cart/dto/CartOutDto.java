package com.ecommerce.cart.dto;

import java.util.List;
import java.util.Objects;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CartOutDto {
	
	private String id;
	private Integer userId;
	private List<CartProductsDetail> cartProductsDetail;

	@Override
	public int hashCode() {
		return Objects.hash(cartProductsDetail, id, userId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof CartOutDto)) {
			return false;
		}
		CartOutDto other = (CartOutDto) obj;
		return Objects.equals(cartProductsDetail, other.cartProductsDetail) && Objects.equals(id, other.id)
				&& Objects.equals(userId, other.userId);
	}


}
