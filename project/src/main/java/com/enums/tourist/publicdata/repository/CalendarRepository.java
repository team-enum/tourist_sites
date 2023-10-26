package com.enums.tourist.publicdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enums.tourist.domain.Calendar;

public interface CalendarRepository extends JpaRepository<Calendar, Long>{

}
