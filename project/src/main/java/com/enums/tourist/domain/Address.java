package com.enums.tourist.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Address {
	
	private String city;

	public Address(String city) {
		super();
		this.city = city;
	}
	
	protected Address() {}
	
}
