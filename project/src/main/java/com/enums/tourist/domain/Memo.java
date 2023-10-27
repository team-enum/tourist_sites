package com.enums.tourist.domain;

import java.util.List;

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
	private String startdate;
	private String content;
	
	@JoinColumn
	@ManyToOne
	private Planner planner;
}
