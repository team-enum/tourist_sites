package com.enums.tourist.planner.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.enums.tourist.domain.Planner;
import com.enums.tourist.planner.repository.PlannerRepository;

import com.enums.tourist.domain.Member;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlannerService {
	private final PlannerRepository plannerRepository;

	public Planner findById(Long id){
		Optional<Planner> findPlanner = plannerRepository.findById(id);
		if(!findPlanner.isPresent()){
			throw new NullPointerException("Planner가 존재하지 않음.");
		}
		return findPlanner.get();
	}

	public List<Planner> findAllByMember(Member member){
		return plannerRepository.findByMember(member, Sort.by(Sort.Direction.DESC, "id"));
	}

	@Transactional
	public Planner save(String title, Member member){
		Planner planner = new Planner();
		planner.setMember(member);
		planner.setTitle(title);
		planner.setCreateDate(LocalDateTime.now());
		return plannerRepository.save(planner);
	}

}
