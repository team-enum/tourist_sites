package com.codingbox.project.service;

import org.springframework.stereotype.Service;

import com.codingbox.project.repository.BoardRepository;
import com.codingbox.project.repository.CommentRepository;
import com.codingbox.project.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {
	private final CommentRepository commentRepository;
	private final BoardRepository boardRepository;
	private final UserRepository userRepository;

}
