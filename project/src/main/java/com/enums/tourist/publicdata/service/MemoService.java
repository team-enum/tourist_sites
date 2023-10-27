package com.enums.tourist.publicdata.service;

import org.springframework.stereotype.Service;

import com.enums.tourist.publicdata.repository.MemoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemoService {
	private final MemoRepository memoRepository;
	
}
