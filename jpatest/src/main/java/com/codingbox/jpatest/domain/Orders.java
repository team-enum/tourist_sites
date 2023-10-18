package com.codingbox.jpatest.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Orders {

	@Id
	@GeneratedValue
	@Column(name="ORDER_ID")
	private Long id;
	
	private LocalDateTime orderDate;
	private String status;
	
	@ManyToOne
	@JoinColumn(name="MEMBER_ID")
	@Setter(value=AccessLevel.NONE)
	private Member member;
	
	public void changeMember(Member member) {
		this.member = member;
		member.getOrders().add(this);
	}
	public void setMember(Member member) {
		this.member=member;
	}
}
