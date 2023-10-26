package com.enums.tourist.domain;


import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="calendars")
@SequenceGenerator(name = "calendar_seq", allocationSize = 1, initialValue = 1)
@Getter @Setter
public class Calendar {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="calendar_seq")
	private Long id;
//	private String memo;
	private LocalDateTime cdate;
	private String title;
	
	@OneToMany(mappedBy = "calendar")
	private List<Memo> memos;
	
	
}
