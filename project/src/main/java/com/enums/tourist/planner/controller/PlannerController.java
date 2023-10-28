package com.enums.tourist.planner.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.enums.tourist.domain.Member;
import com.enums.tourist.domain.Planner;
import com.enums.tourist.planner.service.PlannerService;
import com.enums.tourist.security.MemberDetails;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/planner")
@RequiredArgsConstructor
public class PlannerController {

   private final PlannerService plannerService;

   @GetMapping
   public String createPage(@AuthenticationPrincipal MemberDetails memberDetails, Model model){
      Member member = memberDetails.getMember();
      model.addAttribute("plannerList", plannerService.findAllByMember(member));
      return "/planner/createPlan";
   }

   @PostMapping
   public String create(@RequestParam String title, @AuthenticationPrincipal MemberDetails memberDetails){
      Member member = memberDetails.getMember();
      Planner planner = plannerService.save(title, member);
      
      return "redirect:/planner/" + planner.getId();
   }

   @GetMapping("/{plannerId}")
   public String wirtePage(@PathVariable Long plannerId){
      return "/planner/wirtePlan";
   }
}
