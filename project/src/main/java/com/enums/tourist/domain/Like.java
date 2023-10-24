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

@Entity(name = "likes")
@SequenceGenerator(name = "like_seq_gen", sequenceName = "like_seq", 
   allocationSize = 1, initialValue = 1)
@Getter @Setter
public class Like {
   @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "like_seq_gen")
	@Column(name = "like_id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "member_id")
	private Member member;

	@ManyToOne
	@JoinColumn(name = "board_id")
	private Board board;

	public Like(Member member, Board board) {
        this.member = member;
        this.board = board;
    }

	public Like() {
	}
}
