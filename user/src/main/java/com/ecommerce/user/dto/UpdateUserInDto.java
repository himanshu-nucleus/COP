package com.ecommerce.user.dto;

import java.util.Objects;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UpdateUserInDto {
	
    @NotNull
	private String firstName;
    
    @NotNull
	private String gender;
    
    @NotNull
	private String lastName;
    
    @NotNull
	private String phone;
	
	@NotNull
	private String countryCode;
	
	@NotNull
	private String email;
	
	@NotNull
	private String addressLine1;
	
	@NotNull
	private String addressLine2;
	
	@NotNull
	private String city;
	
	@NotNull
	private String state;
	
	@NotNull
	private String country;
	
	@NotNull
	private String postalCode;
	
	@NotNull
	private String role;

	@Override
	public int hashCode() {
		return Objects.hash(addressLine1, addressLine2, city, country, countryCode, email, firstName, gender,
				lastName, phone, postalCode, role, state);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof UpdateUserInDto)) {
			return false;
		}
		UpdateUserInDto other = (UpdateUserInDto) obj;
		return Objects.equals(addressLine1, other.addressLine1) && Objects.equals(addressLine2, other.addressLine2)
				&& Objects.equals(city, other.city) && Objects.equals(country, other.country)
				&& Objects.equals(countryCode, other.countryCode) && Objects.equals(email, other.email)
				&& Objects.equals(firstName, other.firstName) && Objects.equals(gender, other.gender)
				&& Objects.equals(lastName, other.lastName) && Objects.equals(phone, other.phone)
				&& Objects.equals(postalCode, other.postalCode) && Objects.equals(role, other.role)
				&& Objects.equals(state, other.state);
	}
	
	

}
