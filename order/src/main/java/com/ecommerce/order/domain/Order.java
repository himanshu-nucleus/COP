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
	private Long userId;
	private Instant createDt;
	private double totalAmount;
	private String status;
	private String cardNo;
	private List<Product> products;

    @Override
    public int hashCode() {
        return Objects.hash(cardNo, createDt, id, products, status, totalAmount, userId);
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
        Order other = (Order) obj;
        return Objects.equals(cardNo, other.cardNo) && Objects.equals(createDt, other.createDt)
                && Objects.equals(id, other.id) && Objects.equals(products, other.products)
                && Objects.equals(status, other.status)
                && Double.doubleToLongBits(totalAmount) == Double.doubleToLongBits(other.totalAmount)
                && Objects.equals(userId, other.userId);
    }

	
}
