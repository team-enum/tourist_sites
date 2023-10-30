package com.enums.tourist.domain;

import com.enums.tourist.domain.embedd.Place;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@SequenceGenerator(name = "content_seq", allocationSize = 1, initialValue = 1)
@Getter @Setter @ToString(exclude = "planner")
public class Content {
	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="content_seq")
	private Long id;
	
	@Column(name = "touristDate")
	private String date;
	private String memo;
	
	@Embedded
	private Place place;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "planner_id")
	private Planner planner;
	
	
}
