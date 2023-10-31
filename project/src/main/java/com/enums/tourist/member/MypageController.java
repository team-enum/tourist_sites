package com.enums.tourist.member;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.enums.tourist.domain.Member;
import com.enums.tourist.security.MemberDetails;

import lombok.RequiredArgsConstructor;


@Controller
@RequestMapping
@RequiredArgsConstructor
public class MypageController {
	
	private final PasswordEncoder passwordEncoder;
					
	@GetMapping("/mypage/myInfoModify")
	public String myInfoModify(@AuthenticationPrincipal MemberDetails memberDetails, 
			MemberDTO memberdto, Model model) {
		Member member = memberDetails.getMember();
		String username = member.getUsername();
		String realname = member.getRealname();
		model.addAttribute("username", username);
		model.addAttribute("realname", realname);
		return "/mypage/myInfoModify";
	}
	
	@GetMapping("/mypage/memberInfo")
	public String mypageView(@AuthenticationPrincipal MemberDetails memberDetails, 
			MemberDTO memberdto, Model model) {
		Member member = memberDetails.getMember();
		String username = member.getUsername();
		String realname = member.getRealname();
		model.addAttribute("username", username);
		model.addAttribute("realname", realname);
		return "/mypage/memberInfo";
	}

	@PostMapping("/changePw")
	public String changePassword(@AuthenticationPrincipal MemberDetails memberDetails,
										@RequestParam String currentPassword,
										@RequestParam String newPassword,
										@RequestParam String confirmPassword,
										Model model) {
		Member member = memberDetails.getMember();
		String encodedCurrentPassword = member.getPassword();

		if (passwordEncoder.matches(currentPassword, encodedCurrentPassword)) {
			if (newPassword.equals(confirmPassword)) {
				String encodedNewPassword = passwordEncoder.encode(newPassword);
				member.setPassword(encodedNewPassword);

				return "redirect:/member/mypage";
			} else {
				model.addAttribute("error", true);
				return "redirect:/mypage/myInfoModify";
			}
		} else {
			model.addAttribute("error", true);
			return "redirect:/mypage/myInfoModify";
		}
	}

	@GetMapping("/mypage/bookMarkList")
	public String bookMarkListPage() {
		return "/mypage/bookMarkList"; 
	}
	
	@GetMapping("/mypage/myLike")
	public String myLikePage() {
		return "/mypage/myLike";
	}

}
