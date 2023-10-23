package com.enums.tourist.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;



import com.enums.tourist.domain.Address;
import com.enums.tourist.domain.Member;

import com.enums.tourist.dto.MemberForm;
import com.enums.tourist.repository.MemberRepository;
import com.enums.tourist.service.MemberService;


import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberService;
	private final MemberRepository memberRepository;
	
	@GetMapping("/members/new")
	public String createForm(Model model) {
		model.addAttribute("memberForm", new MemberForm());
		return "members/createMemberForm";
	}
	@PostMapping("/members/new")
	public String create(@Validated MemberForm form, BindingResult result) 
			throws IllegalAccessException {
		
		if( result.hasErrors() ) {
			return "members/createMemberForm";
		}
		Address address = 
				new Address(form.getCity());
		Member member = new Member();
		member.setName(form.getName());
		member.setPassword(form.getPassword());
		member.setAge(form.getAge());
		member.setIdd(form.getIdd());
		member.setAddress(address);
		
		
		memberService.join(member);
		return "redirect:/";
	}
	@GetMapping("/members")
	public String list(Model model) {
		List<Member> members = memberService.findMember();
		model.addAttribute("members",members);
		return "members/memberList";
	}
	@GetMapping("/new")
	public String addForm(@ModelAttribute("member")Member member) {
		return "members/memberList";
	}
	@PostMapping("/new")
	public String save1(@ModelAttribute Member member) {
		memberRepository.save2(member);
		return "redirect:/";
	}
	
	
	
	
	
}
