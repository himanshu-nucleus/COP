package com.ecommerce.order.dto;

import java.util.Objects;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResponseOutDto {

	String message;

	@Override
	public int hashCode() {
		return Objects.hash(message);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof ResponseOutDto)) {
			return false;
		}
		ResponseOutDto other = (ResponseOutDto) obj;
		return Objects.equals(message, other.message);
	}
	
}
