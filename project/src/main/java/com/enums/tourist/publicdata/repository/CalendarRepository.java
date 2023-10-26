package com.enums.tourist.publicdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.enums.tourist.domain.Calendar;

public interface CalendarRepository extends JpaRepository<Calendar, Long>{

	@Query("SELECT c FROM Calendar c WHERE c.id = :id")
	public Calendar findCalendarById(@Param("id")Long id);
}
