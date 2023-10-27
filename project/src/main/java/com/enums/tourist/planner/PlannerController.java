package com.enums.tourist.planner;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;

import com.enums.tourist.domain.Planner;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/planner")
@RequiredArgsConstructor
public class PlannerController {

   private final PlannerService plannerService;

   @GetMapping
   public String createPage(@PathVariable Long plannerId){
	   
      return "/planner/createPlan";
   }
   
   @ResponseBody
   @PostMapping
   public String create(@RequestBody Planner planner, @PathVariable String title){
	   System.out.println(title);
	   plannerService.addPlan(planner, title);
	   return "/planner/createPlan";
   }

   @GetMapping("/{plannerId}")
   public String writePage(@PathVariable Long plannerId){
      return "/planner/writePlan";
   }

   @ResponseBody
   @PostMapping("/{plannerId}")
   public String wirte(@RequestBody PlannerDTO plannerDTO, @PathVariable Long plannerId){
      System.out.println(plannerDTO.toString());
      plannerService.addMemo(plannerDTO, plannerId);
      return plannerDTO.toString();
   }
}
