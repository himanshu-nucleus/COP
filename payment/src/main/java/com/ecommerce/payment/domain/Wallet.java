package com.ecommerce.payment.domain;

import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Document(collection = "payment")
public class Wallet {
	
	@Id
	private String id;
    private String userId;
    private String cardNo;
    private double balance;
    private Boolean isDefault;

	@Override
	public int hashCode() {
		return Objects.hash(balance, cardNo, id, isDefault, userId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Wallet)) {
			return false;
		}
		Wallet other = (Wallet) obj;
		return Double.doubleToLongBits(balance) == Double.doubleToLongBits(other.balance)
				&& Objects.equals(cardNo, other.cardNo) && Objects.equals(id, other.id) && isDefault == other.isDefault
				&& Objects.equals(userId, other.userId);
	}

    
}
