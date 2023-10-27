package com.enums.tourist.planner;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enums.tourist.domain.Memo;
import com.enums.tourist.domain.Planner;

public interface MemoRepository extends JpaRepository<Memo, Long> {
	
	
}
