package com.enums.tourist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.enums.tourist.domain.Member;
import com.enums.tourist.dto.LoginForm;
import com.enums.tourist.service.LoginService;
import com.enums.tourist.web.session.SessionConst;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {
	
	private final LoginService loginService;
	
	@GetMapping("/login")
	public String loginForm(@ModelAttribute("loginForm")LoginForm form) {
		return "login/loginForm";
	}
	@PostMapping("/login")
	public String login(@ModelAttribute LoginForm form,
							Model model,RedirectAttributes redirectAttrs,
							HttpServletRequest request,@RequestParam(defaultValue = "/")String redirectURL) {
		Member loginMember = loginService.login(form.getLoginId(),form.getPassword());
		
		if(loginMember == null) {
			model.addAttribute("msg","로그인 실패");
			return "login/loginForm";
		}else {
			HttpSession session = request.getSession();
			session.setAttribute(SessionConst.LOGIN_MEMBER,loginMember);
			redirectAttrs.addAttribute("msg","로그인 성공");
			return "redirect: " +redirectURL;
		}
		
	}
	
	@PostMapping("/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if(session != null) {
			session.invalidate();
		}
		return "redirect:/";
	}
	
	private void expireCookie(HttpServletResponse response, String cooKieName) {
		Cookie cookie = new Cookie(cooKieName,null);
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}
	
	
	
}
