package com.enums.tourist.publicdata.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.enums.tourist.domain.Area;
import com.enums.tourist.domain.Board;
import com.enums.tourist.domain.Bookmark;
import com.enums.tourist.domain.Comment;
import com.enums.tourist.domain.ContentType;
import com.enums.tourist.domain.Member;
import com.enums.tourist.publicdata.dto.TouristListDTO;
import com.enums.tourist.publicdata.dto.TouristDTO;
import com.enums.tourist.publicdata.dto.TouristItemDTO;
import com.enums.tourist.publicdata.service.BoardService;
import com.enums.tourist.publicdata.service.BookmarkService;
import com.enums.tourist.publicdata.service.CommentService;
import com.enums.tourist.publicdata.service.DataPortalService;
import com.enums.tourist.security.MemberDetails;

import lombok.RequiredArgsConstructor;

@Controller @RequestMapping("/tourist")
@RequiredArgsConstructor
public class TouristController {
   
   private final DataPortalService dataPortalService;
   private final BoardService boardService;
   private final BookmarkService bookmarkService;
   private final CommentService commentService;
   
   @ModelAttribute("areaCodes")
   public List<Area> areaCodes(){
		List<Area> areaCodes = new ArrayList<>();
		areaCodes.add(new Area(1, "서울"));
		areaCodes.add(new Area(2, "인천"));
		areaCodes.add(new Area(3, "대전"));
		areaCodes.add(new Area(4, "대구"));
		areaCodes.add(new Area(5, "광주"));
		areaCodes.add(new Area(6, "부산"));
		areaCodes.add(new Area(7, "울산"));
		areaCodes.add(new Area(8, "세종"));
		return areaCodes;
	}
   
   @ModelAttribute("contentTypes")
	public List<ContentType> contentTypes(){
		List<ContentType> contentTypes = new ArrayList<>();
		contentTypes.add(new ContentType(12, "관광지"));
		contentTypes.add(new ContentType(14, "문화시설"));
		contentTypes.add(new ContentType(15, "축제공연행사"));
		contentTypes.add(new ContentType(25, "여행코스"));
		contentTypes.add(new ContentType(28, "레포츠"));
		contentTypes.add(new ContentType(32, "숙박"));
		contentTypes.add(new ContentType(38, "쇼핑"));
		contentTypes.add(new ContentType(39, "음식점"));
		return contentTypes;
	}
   
   private final int pageSize = 5;
   @GetMapping("/list/{pageNo}")
   public String touristList(
         @RequestParam(required = false) Integer area,
         @RequestParam(required = false) Integer contentTypeId ,
         @RequestParam(required = false) String keyword,
         @PathVariable(required = false) Integer pageNo,
         Model model) throws IOException{
      
      TouristListDTO board = null;
      if(keyword == null || keyword.isBlank()){
         board = dataPortalService.findAll(area, contentTypeId, pageNo);
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
   public String touristDetail(@PathVariable("contentId") Long contentId, Model model,
         @AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : #this") MemberDetails memberDetails)
         throws IOException {
      
      TouristDTO touristDTO = dataPortalService.findOne(contentId);
      Board board = boardService.findOne(contentId, touristDTO);
      long likeCount = boardService.countLike(board);
      touristDTO.setLike(likeCount);

      if(memberDetails != null){
         Bookmark bookmark = bookmarkService.findOne(memberDetails.getMember(), board);
         touristDTO.setBookmark(bookmark != null);
         model.addAttribute("memberId", memberDetails.getMember().getId());
      }

      model.addAttribute("item", touristDTO);
      List<Comment> comments = commentService.getCommentsByContentId(contentId); // 댓글 목록 가져오기
      model.addAttribute("comments", comments);
      return "tourist/touristDetail";
   }

   @ResponseBody
   @GetMapping("/detail/{contentId}/bookmark")
   public boolean bookmark(@PathVariable("contentId") Long contentId, 
         @AuthenticationPrincipal MemberDetails memberDetails) throws IOException{
      Member member = memberDetails.getMember();
      Board board = boardService.findOne(contentId);
      
      return bookmarkService.bookmarking(board, member);
   }

   @ResponseBody
   @GetMapping("/detail/{contentId}/like")
   public long like(@PathVariable("contentId") Long contentId, 
            @AuthenticationPrincipal MemberDetails memberDetails) throws IOException {
      Member member = memberDetails.getMember();
      Board board = boardService.findOne(contentId);
      boardService.like(board, member);
      return boardService.countLike(board);
   }
}
