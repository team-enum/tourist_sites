package com.enums.tourist.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "comments")
@SequenceGenerator(name = "comment_seq_gen", sequenceName = "comment_seq", 
   allocationSize = 1, initialValue = 1)
@Getter @Setter
public class Comment {
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_seq_gen")
	@Column(name = "comment_id")
   private Long id;
   
	private Long contentId;
   private String commentText;

	@ManyToOne
	@JoinColumn(name = "member_id")
	private Member member;

	@ManyToOne
	@JoinColumn(name = "board_id")
	private Board board;
}
