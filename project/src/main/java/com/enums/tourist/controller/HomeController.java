package com.enums.tourist.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.enums.tourist.domain.Member;
import com.enums.tourist.repository.MemberRepository;
import com.enums.tourist.web.session.SessionConst;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {

	//Logger log = LoggerFactory.getLogger(getClass());
	// private final MemberRepository memberRepository;
	
//	@RequestMapping("/")
//	public String home() {
//		log.info("home Controller");
//		return "home";
//	}
	@GetMapping("/")
	public String homeLoginV3(
			@SessionAttribute(name = SessionConst.LOGIN_MEMBER,required = false)Member loginMember,
			Model model
			) {
		if(loginMember == null) {
			return "home";
		}
		model.addAttribute("member",loginMember);
		return "loginHome";
	}
	
	
}











