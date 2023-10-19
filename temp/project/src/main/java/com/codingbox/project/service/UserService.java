package com.codingbox.project.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.codingbox.project.repository.CommentRepository;
import com.codingbox.project.repository.LikeRepository;
import com.codingbox.project.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final LikeRepository likeRepository;
	private final CommentRepository commentRepository;

}
