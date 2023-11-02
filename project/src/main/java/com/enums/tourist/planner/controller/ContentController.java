package com.enums.tourist.planner.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.enums.tourist.domain.Content;
import com.enums.tourist.domain.Planner;
import com.enums.tourist.planner.service.ContentService;
import com.enums.tourist.planner.service.PlannerService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/planner/{plannerId}/content")
@RequiredArgsConstructor
public class ContentController {

   private final PlannerService plannerService;
   private final ContentService contentService;

   @ResponseBody
   @PostMapping
   public Content addContent(@PathVariable Long plannerId, @RequestBody Content content) {
      Planner planner = plannerService.findById(plannerId);
      return contentService.save(content, planner);
   }

   @ResponseBody
   @PostMapping("/{placeId}")
   public Content updateContent(@PathVariable Long placeId, 
         @RequestParam String memo, @RequestParam String date) {
      return contentService.update(placeId, memo, date);
   }

   @ResponseBody
   @DeleteMapping("/{placeId}")
   public boolean deleteContent(@PathVariable Long placeId) {
      return contentService.delete(placeId);
   }

   @ResponseBody
   @GetMapping
   public List<Content> getContentList(@PathVariable Long plannerId) {
      Planner planner = plannerService.findById(plannerId);
      return contentService.findAll(planner);
   }
}
