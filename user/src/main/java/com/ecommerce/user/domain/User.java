package com.ecommerce.user.domain;

import java.time.Instant;
import java.util.Arrays;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long userId;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "gender")
	private String gender;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "phone")
	private String phone;

	@Column(name = "country_code")
	private String countryCode;

	@Column(name = "email", unique = true)
	private String email;

	@Column(name = "created_dt")
	private Instant createdDt;

	@Column(name = "add_line1")
	private String addressLine1;

	@Column(name = "add_line2")
	private String addressLine2;

	@Column(name = "city")
	private String city;

	@Column(name = "state")
	private String state;

	@Column(name = "country")
	private String country;

	@Column(name = "postal_code")
	private String postalCode;

	@Column(name = "role")
	private String role;

	@Column(name = "password")
	private char[] password;

	@Column(name = "updated_dt")
	private Instant updatedDate;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(password);
		result = prime * result + Objects.hash(addressLine1, addressLine2, city, country, countryCode, createdDt, email,
				firstName, gender, lastName, phone, postalCode, role, state, updatedDate, userId);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof User)) {
			return false;
		}
		User other = (User) obj;
		return Objects.equals(addressLine1, other.addressLine1) && Objects.equals(addressLine2, other.addressLine2)
				&& Objects.equals(city, other.city) && Objects.equals(country, other.country)
				&& Objects.equals(countryCode, other.countryCode) && Objects.equals(createdDt, other.createdDt)
				&& Objects.equals(email, other.email) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(gender, other.gender) && Objects.equals(lastName, other.lastName)
				&& Arrays.equals(password, other.password) && Objects.equals(phone, other.phone)
				&& Objects.equals(postalCode, other.postalCode) && Objects.equals(role, other.role)
				&& Objects.equals(state, other.state) && Objects.equals(updatedDate, other.updatedDate)
				&& Objects.equals(userId, other.userId);
	}


}
