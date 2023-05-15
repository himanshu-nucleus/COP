package com.ecommerce.order.domain;

import java.time.Instant;
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
@Document(collection = "order")
public class Order {
	
	@Id
	private String id;
	private Integer userId;
	private Instant createDt;
	private double totalPrice;
	private String status;
	private String cardNo;
	private List<Product> products;

	@Override
	public int hashCode() {
		return Objects.hash(createDt, id, products, status, totalPrice, userId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Order)) {
			return false;
		}
		Order other = (Order) obj;
		return Objects.equals(createDt, other.createDt) && Objects.equals(id, other.id)
				&& Objects.equals(products, other.products) && Objects.equals(status, other.status)
				&& Double.doubleToLongBits(totalPrice) == Double.doubleToLongBits(other.totalPrice)
				&& Objects.equals(userId, other.userId);
	}
}
