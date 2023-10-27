package com.enums.tourist.publicdata.controller;

import java.io.IOException;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.enums.tourist.domain.Planner;
import com.enums.tourist.publicdata.repository.MemoRepository;
import com.enums.tourist.publicdata.repository.PlannerRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/planner")
@RequiredArgsConstructor
public class PlannnerController {
	
	
	@GetMapping("/{id}")
	public String planner(@PathVariable("id")Long id)throws IOException {
		Planner planner = 
	}
	
}