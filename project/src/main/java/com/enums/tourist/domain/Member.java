package com.enums.tourist.domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import lombok.Getter;
import lombok.Setter;

@Entity 
@Getter @Setter
public class Member {
	
	@Id @GeneratedValue
	private Long Id;
	private String name;
	
	@Embedded
	private Address address;
	
}
