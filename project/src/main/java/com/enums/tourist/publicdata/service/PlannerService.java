package com.enums.tourist.publicdata.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.enums.tourist.domain.Planner;
import com.enums.tourist.domain.Memo;
import com.enums.tourist.publicdata.repository.PlannerRepository;
import com.enums.tourist.publicdata.repository.MemoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlannerService {
	private final PlannerRepository plannerRepository;
	private final MemoRepository memoRepository;
	
	public Optional<Planner> findOne(Long id) {
		return plannerRepository.findById(id);
	}
	
	public void addMemo(Planner planner, Memo memo, String createDate) {
		
		Memo mmemo = memoRepository.findByPlannerid(planner, memo);
		mmemo = new Memo();
		mmemo.setContent(null);
		mmemo.setPlanner(planner);
		memoRepository.save(mmemo);
	}
}
