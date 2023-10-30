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
import org.springframework.web.bind.annotation.ResponseBody;

import com.enums.tourist.domain.Board;
import com.enums.tourist.domain.Comment;
import com.enums.tourist.domain.Member;
import com.enums.tourist.publicdata.dto.TouristListDTO;
import com.enums.tourist.publicdata.dto.TouristDTO;
import com.enums.tourist.publicdata.dto.TouristItemDTO;
import com.enums.tourist.publicdata.service.TouristService;
import com.enums.tourist.publicdata.service.CommentService;
import com.enums.tourist.publicdata.service.DataPortalService;
import com.enums.tourist.security.MemberDetails;

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
         @RequestParam(required = false) String keyword,
         @PathVariable(required = false) Integer pageNo,
         Model model) throws IOException{
      
      TouristListDTO board = null;
      if(keyword == null){
         board = dataPortalService.findAll(area,contentTypeId, pageNo);
      }else {
         board = dataPortalService.findAll(area, contentTypeId, keyword, pageNo);
      }

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
   
   @GetMapping({"/list", "/list/"})
   public String touristList(
         @RequestParam(required = false) Integer area,
         @RequestParam(required = false) Integer contentTypeId,
         @RequestParam(required = false) String keyword,
         Model model) throws IOException{
      return touristList(area, contentTypeId, keyword, 1, model);
   }
   

   @GetMapping("/detail/{contentId}")
   public String touristDetail(@PathVariable("contentId") Long contentId, Model model) throws IOException {
      TouristDTO item = dataPortalService.findOne(contentId);
      model.addAttribute("item", item);

      List<Comment> comments = commentService.getCommentsByContentId(contentId); // 댓글 목록 가져오기
      model.addAttribute("comments", comments);
      return "tourist/touristDetail";
   }

   @ResponseBody
   @GetMapping("/detail/{contentId}/bookmark")
   public Long bookmark(@PathVariable("contentId") Long contentId, 
         @AuthenticationPrincipal MemberDetails memberDetails) throws IOException{
      Member member = memberDetails.getMember();
      Board board = boardService.findOne(contentId);
      boardService.bookmarking(board, member);
      
      return boardService.countBookmark(board);
   }

   @ResponseBody
   @GetMapping("/detail/{contentId}/like")
   public Long like(@PathVariable("contentId") Long contentId, 
           @AuthenticationPrincipal MemberDetails memberDetails) throws IOException {
       Member member = memberDetails.getMember();
       Board board = boardService.findOne(contentId);
       boardService.like(board, member);
       return boardService.countLike(board);
   }

   @GetMapping("/detail/{contentId}/likeCount")
   @ResponseBody
   public int getLikeCount(@PathVariable("contentId") Long contentId) {
       int likeCount = boardService.getLikeCount(contentId);
       return likeCount;
   }


}
