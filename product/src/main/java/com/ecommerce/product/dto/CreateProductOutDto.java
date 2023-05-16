package com.ecommerce.product.dto;

import java.util.Objects;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateProductOutDto {

	private String id;
    private String name;
    private String description;
    private Integer quantity;
    private double price;
    private double discount;
    private String manufacturer;
    private Long userId;

	@Override
	public int hashCode() {
		return Objects.hash(description, discount, id, manufacturer, name, price, quantity, userId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof CreateProductOutDto)) {
			return false;
		}
		CreateProductOutDto other = (CreateProductOutDto) obj;
		return Objects.equals(description, other.description)
				&& Double.doubleToLongBits(discount) == Double.doubleToLongBits(other.discount)
				&& Objects.equals(id, other.id) && Objects.equals(manufacturer, other.manufacturer)
				&& Objects.equals(name, other.name)
				&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price)
				&& Objects.equals(quantity, other.quantity) && Objects.equals(userId, other.userId);
	}

	
}
