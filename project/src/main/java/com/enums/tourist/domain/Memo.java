package com.enums.tourist.domain;

import com.enums.tourist.domain.embedd.Places;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
@SequenceGenerator(name = "memo_seq", allocationSize = 1, initialValue = 1)
@Getter @Setter @ToString(exclude = "planner")
public class Memo {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="memo_seq")
	private Long id;
	private String startdate;
	private String content;
	
	@Embedded
	private Places places;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "planner_id")
	private Planner planner;
}
