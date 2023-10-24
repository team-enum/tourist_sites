package com.enums.tourist.publicdata.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.enums.tourist.publicdata.repository.BoarderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {
   private final BoarderRepository boarderRepository;
}
