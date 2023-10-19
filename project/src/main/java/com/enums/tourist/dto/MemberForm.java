package com.enums.tourist.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberForm {
	
	private Long id;
	private String name;
	private String password;
	
	private String LoginId;
	private String city;
	private Integer age;
	
}
