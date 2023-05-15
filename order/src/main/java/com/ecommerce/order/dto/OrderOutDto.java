package com.ecommerce.order.dto;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

import com.ecommerce.order.domain.Product;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderOutDto {

	private String id;
	private Integer userId;
	private Instant createDt;
	private double totalPrice;
	private String status;
	private String cardNo;
	private List<Product> products;

	@Override
	public int hashCode() {
		return Objects.hash(cardNo, createDt, id, products, status, totalPrice, userId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof OrderOutDto)) {
			return false;
		}
		OrderOutDto other = (OrderOutDto) obj;
		return Objects.equals(cardNo, other.cardNo) && Objects.equals(createDt, other.createDt)
				&& Objects.equals(id, other.id) && Objects.equals(products, other.products)
				&& Objects.equals(status, other.status)
				&& Double.doubleToLongBits(totalPrice) == Double.doubleToLongBits(other.totalPrice)
				&& Objects.equals(userId, other.userId);
	}

}
