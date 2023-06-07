package com.ecommerce.payment.dto;

import java.util.Objects;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WalletOutDto {

	private String id;
    private Long userId;
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
		if (!(obj instanceof WalletOutDto)) {
			return false;
		}
		WalletOutDto other = (WalletOutDto) obj;
		return Double.doubleToLongBits(balance) == Double.doubleToLongBits(other.balance)
				&& Objects.equals(cardNo, other.cardNo) && Objects.equals(id, other.id) && isDefault == other.isDefault
				&& Objects.equals(userId, other.userId);
	}
	
}
