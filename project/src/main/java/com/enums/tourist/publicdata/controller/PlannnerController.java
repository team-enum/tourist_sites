package com.enums.tourist.publicdata.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.enums.tourist.publicdata.service.MemoService;
import com.enums.tourist.publicdata.service.PlannerService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/planner")
@RequiredArgsConstructor
public class PlannnerController {
	private final PlannerService plannerService;
	private final MemoService memoService;
}