package com.enums.tourist.domain;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
@Table(name="planners")
@SequenceGenerator(name = "planner_seq", allocationSize = 1, initialValue = 1)
@Getter @Setter
public class Planner {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="planner_seq")
	private Long id;
	
	private LocalDateTime createDate;
	private String title;

	 @OneToMany(mappedBy = "planner")
	 private List<Memo> memos = new ArrayList<>();

}
