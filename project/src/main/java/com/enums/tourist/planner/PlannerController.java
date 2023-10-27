package com.enums.tourist.planner;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/planner")
public class PlannerController {

   @GetMapping
   public String createPage(){
      return "/planner/createPlan";
   }

   @GetMapping("/{plannerId}")
   public String wirtePage(@PathVariable Long plannerId){
      return "/planner/wirtePlan";
   }

   @ResponseBody
   @PostMapping("/{plannerId}")
   public String wirte(@RequestBody String s, @PathVariable Long plannerId){
      return s;
   }
}
