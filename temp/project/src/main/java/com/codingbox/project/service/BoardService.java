package com.codingbox.project.service;

import org.springframework.stereotype.Service;

import com.codingbox.project.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	private final BoardRepository boardRepository;

}
