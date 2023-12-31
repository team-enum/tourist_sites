package com.enums.tourist.member;

import java.util.Map;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.enums.tourist.domain.Member;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
   
   private final MemberService memberService;

   // 회원가입
   @GetMapping("/join")
   public String joinView(@ModelAttribute MemberDTO memberDTO){
      return "member/join";
   }

   @PostMapping("/join")
   public String joinMember(@Valid MemberDTO memberDTO, BindingResult bindingResult, Errors errors, Model model) {
      Member member = new Member();
      member.setUsername(memberDTO.getUsername());
      member.setPassword(memberDTO.getPassword());
      member.setRealname(memberDTO.getRealname());
      member.setEmail(memberDTO.getEmail());
      
      if (errors.hasErrors()) {
          // 회원가입 실패시, 입력 데이터를 유지
         model.addAttribute("memberDTO", memberDTO);

          // 유효성 통과 못한 필드와 메시지를 핸들링
         Map<String, String> validatorResult = memberService.validateHandling(errors);
         for (String key : validatorResult.keySet()) {
            model.addAttribute(key, validatorResult.get(key));
         }

         return "/member/join";
      }
      
      // 아이디 중복 체크
      try {
         memberService.join(member);
      } catch (BadCredentialsException e){
         e.printStackTrace();
         model.addAttribute("valid_username", e.getMessage());
         return "/member/join";
      }

      return "redirect:/member/login";
   }
   
   

   // 로그인
   @GetMapping("/login")
   public String loginView(){
      return "/member/login";
   }
}
