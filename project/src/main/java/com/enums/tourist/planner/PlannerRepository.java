package com.enums.tourist.planner;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.enums.tourist.domain.Planner;

public interface PlannerRepository extends JpaRepository<Planner, Long>{

	
}
