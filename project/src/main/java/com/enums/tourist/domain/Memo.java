package com.enums.tourist.domain;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@SequenceGenerator(name = "memo_seq", allocationSize = 1, initialValue = 1)
@Getter @Setter
public class Memo {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="memo_seq")
	private Long id;
	private LocalDateTime startdate;
	private String content;
	
	@Embedded
	private Places places;
	
	// @ManyToOne
	// @JoinColumn(name = "planner_id")
	// private Planner planner;
}
