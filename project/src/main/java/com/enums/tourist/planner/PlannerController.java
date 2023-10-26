package com.enums.tourist.planner;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/planner")
public class PlannerController {
   
   @GetMapping
   public String createPage(){
      return "/planner/createPlan";
   }
}
