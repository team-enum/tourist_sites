package com.enums.tourist.planner;

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
      model.addAttribute("plannerList", plannerService.findByMember(member));
      return "/planner/createPlan";
   }

   @PostMapping
   public String create(@RequestParam String title, @AuthenticationPrincipal MemberDetails memberDetails){
      Member member = memberDetails.getMember();
      plannerService.save(title, member);
      return "/planner/createPlan";
   }

   @GetMapping("/{plannerId}")
   public String wirtePage(@PathVariable Long plannerId){
      return "/planner/wirtePlan";
   }

   @ResponseBody
   @PostMapping("/{plannerId}")
   public String wirte(@RequestBody PlannerDTO plannerDTO, @PathVariable Long plannerId){
      System.out.println(plannerDTO.toString());
      plannerService.addMemo(plannerDTO, plannerId);
      return plannerDTO.toString();
   }

   @ResponseBody
   @GetMapping("/{plannerId}/read")
   public ResponseEntity<List<Memo>> read(@PathVariable Long plannerId){
      List<Memo> memos = plannerService.memoList(plannerId);
      return new ResponseEntity<List<Memo>>(memos, HttpStatus.OK);
   }
}
