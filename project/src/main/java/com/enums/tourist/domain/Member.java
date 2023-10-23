package com.enums.tourist.domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity 
@Getter @Setter
//@Data
public class Member {
	
	@Id @GeneratedValue
	private Long Id;
	private String name;
	private Integer age;
	private String password;
	private String idd;
	
	@Embedded
	private Address address;
	
}
