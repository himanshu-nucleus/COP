package com.ecommerce.order.dto;

import java.time.Instant;
import java.util.Objects;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderOutDto {

	private String id;
	private Instant createDt;
	private double totalPrice;
	private String status;
	private String cardNo;

	@Override
	public int hashCode() {
		return Objects.hash(cardNo, createDt, id, status, totalPrice);
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
				&& Objects.equals(id, other.id) && Objects.equals(status, other.status)
				&& Double.doubleToLongBits(totalPrice) == Double.doubleToLongBits(other.totalPrice);
	}


}
