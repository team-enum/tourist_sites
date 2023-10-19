package com.enums.tourist.publicdata.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.enums.tourist.publicdata.dto.TouristBoardDTO;
import com.enums.tourist.publicdata.dto.TouristDTO;
import com.enums.tourist.publicdata.dto.TouristItemDTO;
import com.enums.tourist.publicdata.service.DataPortalService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/tourist")
public class DataPortalController {
   
   private final DataPortalService dataPortalService;
   private final int pageSize = 5;
   @GetMapping("/list/{pageNo}")
   public String touristList(
         @RequestParam(required = false, defaultValue = "") String keyword,
         @PathVariable(required = false) Integer pageNo,
         Model model) throws IOException{
      int numOfRows = dataPortalService.numOfRows;
      TouristBoardDTO board = dataPortalService.findAll(keyword, pageNo, numOfRows);

      List<TouristItemDTO> items = board.getList();
      int totalCount = board.getTotalCount();
      int totalPage  = totalCount - 1 / pageSize + 1;
      int startPage = (pageNo - 1) / pageSize * pageSize + 1;
      int endPage = startPage + pageSize - 1;
      endPage = endPage < totalPage ? endPage : totalPage;

      model.addAttribute("items", items);
      model.addAttribute("startPage", startPage);
      model.addAttribute("nowPage", pageNo);
      model.addAttribute("endPage", endPage);
      model.addAttribute("totalPage", totalPage);
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