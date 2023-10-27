package com.enums.tourist.publicdata.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.enums.tourist.domain.Calendar;
import com.enums.tourist.domain.Memo;
import com.enums.tourist.publicdata.repository.CalendarRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CalendarService {
	//@Autowired
	private final CalendarRepository CeventRepository;
	
	public List<Calendar> getAllEvents(){
		return CeventRepository.findAll();
		
	}
	public Calendar createEvents(Calendar event) {
		return CeventRepository.save(event);
	}
	public Calendar findCalendarById(Long id) {
		return CeventRepository.findCalendarById(id);
	}
	public void addMemo(@RequestParam String memo, @RequestParam String adate) {
		Calendar calendar = new Calendar();
		Memo newMemo = new Memo();
		newMemo.setContent(memo);
		calendar.addMemo(newMemo);
	}
	public List<Memo> getMemoByCalendarId(Long id){
		Calendar calendar = CeventRepository.findById(id).orElse(null);
		if(calendar != null) {
			return calendar.getMemos();
		}
		return Collections.emptyList();
	}
}
