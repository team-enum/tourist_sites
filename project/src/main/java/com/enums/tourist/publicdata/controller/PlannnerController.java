package com.enums.tourist.publicdata.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.enums.tourist.domain.Calendar;
import com.enums.tourist.domain.Memo;
import com.enums.tourist.publicdata.service.CalendarService;

@Controller
public class CalendarController {
	@Autowired
	private CalendarService calendarService;
	
	@ResponseBody
	@GetMapping("/calendar")
	public String getAllEvents(Model model) {
		List<Calendar> events = calendarService.getAllEvents();
		model.addAttribute("events",events);
		return "eventList";
	}
//	@PostMapping("/calendar")
//	public String calendar(@RequestParam String memo, @RequestParam String date) {
//		
//		Calendar calendar = new Calendar();
//		calendar.setMemo(memo);
//		calendar.setCdate(date);
//		calendarService.createEvents(calendar);
//		return "redirect:/calendar";
//	}

	@PostMapping("/calendar")
	public String addMemo(@RequestParam String memo, @RequestParam String adate) {
		Calendar calendar = new Calendar();

		
		Memo newMemo = new Memo();
		newMemo.setContent(memo);
		newMemo.setCalendar(calendar);
		
		calendar.getMemos().add(newMemo);
		
		calendarService.createEvents(calendar);
		
		return "redirect:/calendar";
	}
	@GetMapping("/calendar/{calendarId}/memos")
	public String getMemoByCalendar(@PathVariable Long id, Model model) {
		List<Memo> memos = calendarService.getMemoByCalendarId(id);
		model.addAttribute("memos",memos);
		return "memoList";
				//html만들어지면 추가할 부분		
	}
	
}
