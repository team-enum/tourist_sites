package com.enums.tourist.publicdata.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.enums.tourist.publicdata.dto.TouristDTO;
import com.enums.tourist.publicdata.dto.TouristItemDTO;
import com.enums.tourist.publicdata.service.DataPortalService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/tourist")
public class DataPortalController {
   
   private final DataPortalService dataPortalService;

   @GetMapping("/list/{pageNo}")
   public String touristList(
         @RequestParam(required = false, defaultValue = "") String keyword,
         @PathVariable(required = false) Integer pageNo,
         Model model) throws IOException{

      List<TouristItemDTO> items = dataPortalService.findAll(keyword, pageNo);
      model.addAttribute("items", items);
      return "tourist/touristList";
   }

   @GetMapping("/list")
   public String touristList(
         @RequestParam(required = false) String keyword,
         Model model) throws IOException{
      return touristList(keyword, 1, model);
   }

   @GetMapping("/detail/{contentId}")
   public String touristDetail(
         @PathVariable Long contentId,
         Model model) throws IOException{
      TouristDTO item = dataPortalService.findOne(contentId);
      model.addAttribute("item", item);
      return "tourist/touristDetail";
   }
}
