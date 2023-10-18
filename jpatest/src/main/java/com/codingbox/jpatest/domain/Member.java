package com.codingbox.jpatest.domain;

import java.util.ArrayList;
import java.util.List;

import com.codingbox.jpatest.embeded.Address;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Member {
	
	@Id
	@GeneratedValue
	@Column(name = "MEMBER_ID")
	private Long id;
	private String name;
//	private String city;
//	private String street;
//	private String zipcode;
	@Embedded
	private Address address;
	
	@OneToMany(mappedBy ="member")
	private List<Orders> orders = new ArrayList<>();
	
	public void addOrders(Orders order) {
		order.setMember(this);
		this.orders.add(order);
	}
	
	
}
