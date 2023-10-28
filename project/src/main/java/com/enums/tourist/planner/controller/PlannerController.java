package com.enums.tourist.planner.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.enums.tourist.domain.Member;
import com.enums.tourist.domain.Memo;
import com.enums.tourist.domain.Planner;
import com.enums.tourist.planner.PlannerDTO;
import com.enums.tourist.planner.service.MemoService;
import com.enums.tourist.planner.service.PlannerService;
import com.enums.tourist.security.MemberDetails;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/planner")
@RequiredArgsConstructor
public class PlannerController {

   private final PlannerService plannerService;
   private final MemoService memoService;

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

   @ResponseBody
   @PostMapping("/{plannerId}")
   public String wirte(@RequestBody PlannerDTO plannerDTO, @PathVariable Long plannerId){
      System.out.println(plannerDTO.toString());
      Planner planner = plannerService.findById(plannerId);
      memoService.addMemo(plannerDTO, planner);
      return plannerDTO.toString();
   }

   @ResponseBody
   @GetMapping("/{plannerId}/read")
   public ResponseEntity<List<Memo>> read(@PathVariable Long plannerId){
      Planner planner = plannerService.findById(plannerId);
      List<Memo> memos = memoService.memoList(planner);
      return new ResponseEntity<List<Memo>>(memos, HttpStatus.OK);
   }
}
