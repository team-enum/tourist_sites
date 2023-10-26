package com.enums.tourist.publicdata.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.enums.tourist.domain.Board;
import com.enums.tourist.domain.Comment;
import com.enums.tourist.domain.Member;
import com.enums.tourist.publicdata.dto.TouristListDTO;
import com.enums.tourist.publicdata.dto.TouristDTO;
import com.enums.tourist.publicdata.dto.TouristItemDTO;
import com.enums.tourist.publicdata.service.TouristService;
import com.enums.tourist.publicdata.service.DataPortalService;
import com.enums.tourist.security.MemberDetails;
import com.enums.tourist.service.CommentService;

import lombok.RequiredArgsConstructor;

@Controller @RequestMapping("/tourist")
@RequiredArgsConstructor
public class TouristController {
   
   private final DataPortalService dataPortalService;
   private final TouristService boardService;
   private final CommentService commentService;

   private final int pageSize = 5;
   @GetMapping("/list/{pageNo}")
   public String touristList(
         @RequestParam(required = false) Integer area,
         @RequestParam(required = false) Integer contentTypeId ,
         @PathVariable(required = false) Integer pageNo,
         Model model) throws IOException{
      
      TouristListDTO board = dataPortalService.findAll(area,contentTypeId, pageNo);
      
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
         @RequestParam(required = false) Integer area,
         @RequestParam(required = false) Integer contentTypeId,
         Model model) throws IOException{
      return touristList(area, contentTypeId, 1, model);
   }
   
   

   @GetMapping("/detail/{contentId}")
   public String touristDetail(@PathVariable("contentId") Long contentId, Model model) throws IOException {
      TouristDTO item = dataPortalService.findOne(contentId);
      model.addAttribute("item", item);

      List<Comment> comments = commentService.getCommentsByContentId(contentId); // 댓글 목록 가져오기
      model.addAttribute("comments", comments);
      return "tourist/touristDetail";
   }

   @GetMapping("/detail/{contentId}/bookmark")
   public String bookmark(@PathVariable("contentId") Long contentId, 
         @AuthenticationPrincipal MemberDetails memberDetails) throws IOException{
      Member member = memberDetails.getMember();
      Board board = boardService.findOne(contentId);
      boardService.bookmarking(board, member);
      
      return "redirect:/tourist/detail/" + contentId;
   }

   @GetMapping("/detail/{contentId}/like")
   public String like(@PathVariable("contentId") Long contentId, 
         @AuthenticationPrincipal MemberDetails memberDetails) throws IOException{
      Member member = memberDetails.getMember();
      Board board = boardService.findOne(contentId);
      boardService.like(board, member);
      
      return "redirect:/tourist/detail/" + contentId;
   }
   
   
   
   

}