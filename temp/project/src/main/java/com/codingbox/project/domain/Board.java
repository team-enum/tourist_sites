package com.codingbox.project.domain;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq")
    @SequenceGenerator(name = "member_seq", sequenceName = "Member_SEQ", allocationSize = 1)
	private Long id;

	private String title;	// 제목
	private String body;	// 내용

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany(mappedBy = "board")
	private List<Like> likes;
	private Integer likeCnt;		// 좋아요 수

	@OneToMany(mappedBy = "board")
	private List<Comment> comments;
	private Integer commentCnt;		// 댓글 수

}
