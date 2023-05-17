package com.ecommerce.payment.dto;

import java.util.Objects;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UpdateDefaultWalletInDto {

	private Long userId;
    private Boolean isDefault;

	@Override
	public int hashCode() {
		return Objects.hash(isDefault, userId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof UpdateDefaultWalletInDto)) {
			return false;
		}
		UpdateDefaultWalletInDto other = (UpdateDefaultWalletInDto) obj;
		return Objects.equals(isDefault, other.isDefault) && Objects.equals(userId, other.userId);
	}
	
    
}
