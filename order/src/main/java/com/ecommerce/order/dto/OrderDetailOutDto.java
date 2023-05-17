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
public class OrderDetailOutDto {

	private String id;
	private Instant createDt;
	private double totalAmount;
	private String status;
	private String cardNo;
	private List<Product> products;

	@Override
	public int hashCode() {
		return Objects.hash(cardNo, createDt, id, products, status, totalAmount);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof OrderDetailOutDto)) {
			return false;
		}
		OrderDetailOutDto other = (OrderDetailOutDto) obj;
		return Objects.equals(cardNo, other.cardNo) && Objects.equals(createDt, other.createDt)
				&& Objects.equals(id, other.id) && Objects.equals(products, other.products)
				&& Objects.equals(status, other.status)
				&& Double.doubleToLongBits(totalAmount) == Double.doubleToLongBits(other.totalAmount);
	}

}
