package com.ecommerce.order.dto;

import java.util.Objects;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CartProductsDetail {

	private String productId;
    private String name;
    private String description;
    private Integer quantity;
    private double price;
    private double discount;
    private String manufacturer;

	@Override
	public int hashCode() {
		return Objects.hash(description, discount, manufacturer, name, price, productId, quantity);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof CartProductsDetail)) {
			return false;
		}
		CartProductsDetail other = (CartProductsDetail) obj;
		return Objects.equals(description, other.description)
				&& Double.doubleToLongBits(discount) == Double.doubleToLongBits(other.discount)
				&& Objects.equals(manufacturer, other.manufacturer) && Objects.equals(name, other.name)
				&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price)
				&& Objects.equals(productId, other.productId) && Objects.equals(quantity, other.quantity);
	}

    

}
