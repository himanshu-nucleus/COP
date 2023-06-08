package com.ecommerce.order.domain;

import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Document(collection = "cart")
public class Cart {
	
	@Id
	private String id;
	private Long userId;
	private List<CartProducts> cartProducts;

	@Override
	public int hashCode() {
		return Objects.hash(cartProducts, id, userId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Cart)) {
			return false;
		}
		Cart other = (Cart) obj;
		return Objects.equals(cartProducts, other.cartProducts) && Objects.equals(id, other.id)
				&& Objects.equals(userId, other.userId);
	}
}
