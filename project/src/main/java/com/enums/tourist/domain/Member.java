package com.enums.tourist.domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity 
@Getter @Setter
public class Member {
	
	@Id
	private Long Id;
	private String name;
	
	@Embedded
	private Address address;
	
}
