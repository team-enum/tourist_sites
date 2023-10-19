package com.enums.tourist.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter @Setter
@SequenceGenerator(name = "member_seq_gen", sequenceName = "member_seq", 
	allocationSize = 1, initialValue = 1)
public class Member {
	
	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq_gen")
	@Column(name = "member_id")
	private Long id;
	
	private String name;
	private Integer age;
	private String password;
	
	@Embedded
	private Address address;
	
}
