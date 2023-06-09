package com.ecommerce.cart.domain;

import java.util.Objects;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CartProducts {

	private String productId;
	private Integer quantity;
    @Override
    public int hashCode() {
        return Objects.hash(productId, quantity);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        CartProducts other = (CartProducts) obj;
        return Objects.equals(productId, other.productId) && Objects.equals(quantity, other.quantity);
    }
}
