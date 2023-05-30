package com.ecommerce.product.domain;

import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Document(collection = "products")
public class Product {
	
	@Id
	private String id;
	private Long userId;
    private String name;
    private String description;
    private Integer quantity;
    private double price;
    private double discount;
    private String manufacturer;
   
	@Override
	public int hashCode() {
		return Objects.hash(description, discount, id, manufacturer, name, price, quantity, userId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Product)) {
			return false;
		}
		Product other = (Product) obj;
		return Objects.equals(description, other.description)
		        && Double.doubleToLongBits(discount) == Double.doubleToLongBits(other.discount)
		        && Objects.equals(id, other.id) && Objects.equals(manufacturer, other.manufacturer)
		        && Objects.equals(name, other.name)
		        && Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price)
		        && Objects.equals(quantity, other.quantity) && Objects.equals(userId, other.userId);
	}
   
	    
}
