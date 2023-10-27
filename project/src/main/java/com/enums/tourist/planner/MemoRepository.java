package com.enums.tourist.planner;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enums.tourist.domain.Memo;

public interface MemoRepository extends JpaRepository<Memo, Long> {
	
	
}
