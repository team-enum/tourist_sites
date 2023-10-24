package com.codingbox.project.domain;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue
	private Long id;

	private String loginId;
	private String password;
	private String name;

	@OneToMany(mappedBy = "user")
	private List<Board> boards;

	@OneToMany(mappedBy = "user")
	private List<Like> likes;			// 누른 좋아요

	@OneToMany(mappedBy = "user")
	private List<Comment> comments;		// 댓글
}
