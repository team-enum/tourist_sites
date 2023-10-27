package com.enums.tourist.publicdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.enums.tourist.domain.Memo;
import com.enums.tourist.domain.Planner;

public interface MemoRepository extends JpaRepository<Memo, Long> {
	
	Memo findByPlannerid(Planner planner, Memo memo);
}
