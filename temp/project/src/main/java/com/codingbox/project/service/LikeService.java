package com.codingbox.project.service;

import org.springframework.stereotype.Service;

import com.codingbox.project.repository.BoardRepository;
import com.codingbox.project.repository.LikeRepository;
import com.codingbox.project.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LikeService {
	private final LikeRepository likeRepository;
	private final UserRepository userRepository;
	private final BoardRepository boardRepository;

}
