package com.enums.tourist.publicdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.enums.tourist.domain.Planner;

public interface PlannerRepository extends JpaRepository<Planner, Long>{

	@Query("SELECT p FROM Planner p WHERE p.id = :id")
	public Planner findPlanner(@Param("id")Long id);
}
