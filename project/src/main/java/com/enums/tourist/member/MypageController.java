package com.enums.tourist.member;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.enums.tourist.domain.Bookmark;
import com.enums.tourist.domain.Member;
import com.enums.tourist.publicdata.service.BookmarkService;
import com.enums.tourist.security.MemberDetails;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MypageController {

   private final PasswordEncoder passwordEncoder;
   private final MemberService memberService;
   private final BookmarkService bookmarkService;
   

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
         @AuthenticationPrincipal MemberDetails memberDetails,
         Model model){

      Member member = memberDetails.getMember();

      if(passwordEncoder.matches(password, member.getPassword())){
         MemberUpdateDTO memberDTO = new MemberUpdateDTO();
         memberDTO.setRealname(member.getRealname());
         memberDTO.setEmail(member.getEmail());
         log.info(">>>" + memberDTO.toString());
         model.addAttribute("memberDTO", memberDTO);
         return "mypage/mypageModify";
      }
      return "mypage/mypageCheck";
   }

   @PostMapping("/info/edit")
   public String memberInfoUpdate(@Valid MemberUpdateDTO memberDTO, 
         @AuthenticationPrincipal MemberDetails memberDetails) {
      
      if(memberDTO.passwordMatch()){
         memberService.update(memberDetails.getMember(), memberDTO);
         return "redirect:/member/mypage";
      }

      return "redirect:/member/info";
   }

   @GetMapping("/bookmark")
   public String bookmarkListPage(@AuthenticationPrincipal MemberDetails memberDetails, Model model){
      Member member = memberDetails.getMember();
      List<Bookmark> bookmarks = bookmarkService.findAll(member);
      System.out.println(bookmarks.toString());
      model.addAttribute("bookmarks", bookmarks);
      return "mypage/bookmarkList";
   }
}
