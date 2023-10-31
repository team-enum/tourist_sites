package com.enums.tourist.member;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.enums.tourist.domain.Member;
import com.enums.tourist.security.MemberDetails;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/member/mypage")
@RequiredArgsConstructor
public class MypageController {

   @GetMapping
   public String mypageView(@AuthenticationPrincipal MemberDetails memberDetails, Model model){
      Member member = memberDetails.getMember();
      model.addAttribute("member", member);
      return "mypage/memberInfo";
   }
}
