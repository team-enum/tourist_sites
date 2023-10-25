package com.enums.tourist.domain;

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
@SequenceGenerator(name = "bookmark_seq", allocationSize = 1, initialValue = 1)
@Getter @Setter @ToString
public class Bookmark {
   
   @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bookmark_seq")
   private Long id;

   @ManyToOne
	@JoinColumn(name = "member_id")
   private Member member;

   @ManyToOne
	@JoinColumn(name = "board_id")
   private Board board;

}
