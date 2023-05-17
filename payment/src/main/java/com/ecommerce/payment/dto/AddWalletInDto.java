package com.ecommerce.payment.dto;

import java.util.Objects;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AddWalletInDto {

    private Long userId;
    private String cardNo;
    private double balance;
    private Boolean isDefault;
	@Override
	public int hashCode() {
		return Objects.hash(balance, cardNo, isDefault, userId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof AddWalletInDto)) {
			return false;
		}
		AddWalletInDto other = (AddWalletInDto) obj;
		return Double.doubleToLongBits(balance) == Double.doubleToLongBits(other.balance)
				&& Objects.equals(cardNo, other.cardNo) && Objects.equals(isDefault, other.isDefault)
				&& Objects.equals(userId, other.userId);
	}
    
    
}
