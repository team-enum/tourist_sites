package com.enums.tourist.planner.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enums.tourist.domain.Content;
import com.enums.tourist.domain.Planner;

import java.util.List;


public interface ContentRepository extends JpaRepository<Content, Long> {
	
   List<Content> findByPlanner(Planner planner);
}
