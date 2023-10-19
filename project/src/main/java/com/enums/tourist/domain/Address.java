package com.enums.tourist.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter @NoArgsConstructor @AllArgsConstructor
public class Address {
	
	private String city;
	
}
