package com.enums.tourist.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.enums.tourist.domain.Member;
import com.enums.tourist.dto.MemberForm;
import com.enums.tourist.login.LoginForm;
import com.enums.tourist.service.MemberService;
import com.enums.tourist.session.SessionConst;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService; 
	
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
		
		Member member = new Member();
		member.setName(form.getName());
		
		memberService.join(member);
		return "redirect:/";
	}
	
	
	@GetMapping("/members")
	public String list(Model model) {
		List<Member> members = memberService.findMembers();
		model.addAttribute("members", members);
		return "members/memberList";
	}
	
	@PostMapping("/login")
	public String loginV3(@ModelAttribute 
			LoginForm form,
			Model model, 
			RedirectAttributes redirectAttrs, 
			HttpServletRequest request,
			@RequestParam(defaultValue = "/")String redirectURL) {
			
		// login이 가능한 사람인지 체크	-> loginService
		// 로그인 실패 -> login/loginForm
		// 로그인 성공 -> /
		
		Member loginMember
		= memberService.login(form.getLoginId(), form.getPassword());
		if( loginMember == null ) {
			// 로그인 실패시
			model.addAttribute("msg", "로그인 실패");
			return "login/loginForm";
		}else {
			// 로그인 성공시
			// 세션이 있으면 세션 반환, 없으면 신규 세션 생성
			HttpSession session = request.getSession();
			// 세션에 로그인 회원 정보 보관
			session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);
			
			redirectAttrs.addFlashAttribute("msg", "로그인 성공");
			return "redirect:" + redirectURL;
		}
	}
}
