package com.enums.tourist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {

	//Logger log = LoggerFactory.getLogger(getClass());
	
	@RequestMapping("/")
	public String home() {
		log.info("home Controller");
		return "home";
	}
	
	
	
}











