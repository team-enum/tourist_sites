package com.enums.tourist.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="planners")
@SequenceGenerator(name = "planner_seq", allocationSize = 1, initialValue = 1)
@Getter @Setter @ToString
public class Planner {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="planner_seq")
	private Long id;
	
	private LocalDateTime createDate;
	private String title;

	@ManyToOne
	@JoinColumn(name = "member_id")
	private Member member;
}
