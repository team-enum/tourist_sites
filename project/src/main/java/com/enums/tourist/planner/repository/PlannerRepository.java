package com.enums.tourist.planner.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.enums.tourist.domain.Member;
import com.enums.tourist.domain.Planner;

public interface PlannerRepository extends JpaRepository<Planner, Long>{

	List<Planner> findByMember(Member member);
	List<Planner> findByMember(Member member, Sort sort);
}
