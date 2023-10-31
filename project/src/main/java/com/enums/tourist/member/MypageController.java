package com.enums.tourist.member;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.enums.tourist.domain.Member;
import com.enums.tourist.security.MemberDetails;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MypageController {

   private final PasswordEncoder passwordEncoder;
   private final MemberRepository memberRepository;

   @GetMapping("/mypage")
   public String mypageView(@AuthenticationPrincipal MemberDetails memberDetails, Model model){
      Member member = memberDetails.getMember();
      model.addAttribute("member", member);
      return "mypage/mypage";
   }

   @GetMapping("/info")
   public String checkView(){
      return "mypage/mypageCheck";
   }

   @PostMapping("/info")
   public String check(@RequestParam String password, 
         @AuthenticationPrincipal MemberDetails memberDetails, Model model){
      Member member = memberDetails.getMember();
      if(passwordEncoder.matches(password, member.getPassword())){
         model.addAttribute("member", memberDetails.getMember());
         model.addAttribute("memberDTO", new MemberDTO());
         return "mypage/mypageModify";
      }
      return "mypage/mypageCheck";
   }
}
