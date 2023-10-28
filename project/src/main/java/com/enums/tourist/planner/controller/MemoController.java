package com.enums.tourist.planner.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.enums.tourist.domain.Memo;
import com.enums.tourist.domain.Planner;
import com.enums.tourist.planner.PlannerDTO;
import com.enums.tourist.planner.service.MemoService;
import com.enums.tourist.planner.service.PlannerService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/planner/{plannerId}")
@RequiredArgsConstructor
public class MemoController {

   private final PlannerService plannerService;
   private final MemoService memoService;

   @ResponseBody
   @PostMapping
   public String wirte(@RequestBody PlannerDTO plannerDTO, @PathVariable Long plannerId){
      System.out.println(plannerDTO.toString());
      Planner planner = plannerService.findById(plannerId);
      memoService.addMemo(plannerDTO, planner);
      return plannerDTO.toString();
   }

   @ResponseBody
   @GetMapping("/read")
   public ResponseEntity<List<Memo>> read(@PathVariable Long plannerId){
      Planner planner = plannerService.findById(plannerId);
      List<Memo> memos = memoService.memoList(planner);
      return new ResponseEntity<List<Memo>>(memos, HttpStatus.OK);
   }
}
