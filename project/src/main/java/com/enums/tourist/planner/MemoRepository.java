package com.enums.tourist.planner;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enums.tourist.domain.Memo;
import com.enums.tourist.domain.Planner;

import java.util.List;


public interface MemoRepository extends JpaRepository<Memo, Long> {
	
   List<Memo> findByPlanner(Planner planner);
}
