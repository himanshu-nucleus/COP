package com.ecommerce.user.dto;

import java.util.Objects;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SignupOutDto {
	
	private Long userId;
	private String firstName;
	private String gender;
	private String lastName;
	private String phone;
	private String countryCode;
	private String email;
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String state;
	private String country;
	private String postalCode;
	private String role;

	@Override
	public int hashCode() {
		return Objects.hash(addressLine1, addressLine2, city, country, countryCode, email, firstName, gender,
				lastName, phone, postalCode, role, state, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof SignupOutDto)) {
			return false;
		}
		SignupOutDto other = (SignupOutDto) obj;
		return Objects.equals(addressLine1, other.addressLine1) && Objects.equals(addressLine2, other.addressLine2)
				&& Objects.equals(city, other.city) && Objects.equals(country, other.country)
				&& Objects.equals(countryCode, other.countryCode) && Objects.equals(email, other.email)
				&& Objects.equals(firstName, other.firstName) && Objects.equals(gender, other.gender)
				&& Objects.equals(lastName, other.lastName) && Objects.equals(phone, other.phone)
				&& Objects.equals(postalCode, other.postalCode) && Objects.equals(role, other.role)
				&& Objects.equals(state, other.state) && Objects.equals(userId, other.userId);
	}

	
}
