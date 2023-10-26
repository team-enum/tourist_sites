package com.enums.tourist.publicdata.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enums.tourist.domain.Calendar;
import com.enums.tourist.publicdata.repository.CalendarRepository;

@Service
public class CalendarService {
	@Autowired
	private CalendarRepository CeventRepository;
	
	public List<Calendar> getAllEvents(){
		return CeventRepository.findAll();
		
	}
	public Calendar createEvents(Calendar event) {
		return CeventRepository.save(event);
	}
}
